import React, { useState, useContext, useEffect } from 'react'
import { UserInfoContext } from '../../contexts/UserInfo'
import UserService from '../../services/UserService'
import NotificationService from '../../services/NotificationService'
import Notification from './Notification'

const NotificationViewer = () => {
    const [listNotifs, setListNotifs] = useState([])
    const [listUnchecked, setListUnchecked] = useState([])
    const [listChecked, setListChecked] = useState([])
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const [reload, setReload] = useState(false)
    const [checkedVisible, setCheckedVisible] = useState([])
    const [uncheckedVisible, setUncheckedVisible] = useState([])
    const [pageNumberChecked, setPageNumberChecked] = useState(0);
    const [pageNumberUnchecked, setPageNumberUnchecked] = useState(0);
    const elementsPerPage = 3



    useEffect(async () => {
        if (reload) {
            await getNotifications()
            // console.log(listNotifs, "listNotifs")
            getCheckedNotifs(listNotifs)
            getUncheckedNotifs(listNotifs)
            setReload(false)
        }
    })


    useEffect(async () => {
        await getNotifications()
        getCheckedNotifs(listNotifs)
        getUncheckedNotifs(listNotifs)
        setReload(true)
        let interval = setInterval(() => setReload(true), 60000)
        //destroy interval on unmount
        return () => clearInterval(interval)
    }, [])


    const getNotifications = async () => {
        if (loggedUser.isLoggedIn) {
            const fullUser = await getFullUser()
            if (fullUser != null) {
                let notifications = []
                switch (loggedUser.role) {
                    case "ETUDIANT":
                        notifications = await NotificationService.getAllNotificationByEtudiant(fullUser.id)
                        // console.log(notifications, "PRESORTING")
                        setListNotifs(notifications)
                        // console.log(sortNotifs(notifications), "POST SORTING")
                        break
                    case "SUPERVISEUR":
                        notifications = await NotificationService.getAllNotificationBySuperviseur(fullUser.id)
                        setListNotifs(notifications)
                        // console.log(notifications, "setting notifs superv")
                        break
                    case "MONITEUR":
                        notifications = await NotificationService.getAllNotificationByMoniteur(fullUser.id)
                        setListNotifs(notifications)
                        // console.log(notifications, "setting notifs Moniteur")
                        break
                    case "GESTIONNAIRE":
                        notifications = await NotificationService.getAllNotificationGestionnaire()
                        setListNotifs(notifications)
                        // console.log(notifications, "setting notifs gestionnaire")
                        break
                }
            }
        }
    }

    const sortNotifs = (listNotifications) => {
        return listNotifications.sort((notif1, notif2) => sortingByUrgency(notif1, notif2))
    }

    const sortingByUrgency = (notif1, notif2) => {
        if (notif1.status == "URGENT" && notif2.status == "ALERT") {
            return -1
        }
        else if (notif1.status == "ALERT" && notif2.status == "URGENT") {
            return 1
        }
        else {
            return 0
        }

    }

    const getUncheckedNotifs = (listNotifs) => {
        if (listNotifs.length != 0) {
            const uncheckedNotifs = listNotifs.filter((notif) => {
                if (!notif.checked) {
                    return notif
                }
            })
            // console.log(uncheckedNotifs, "uncheckednotifs")
            setListUnchecked(uncheckedNotifs)
            setUncheckedVisible(sortNotifs(uncheckedNotifs.slice(0, elementsPerPage)))
            return
        }
        setListUnchecked([])
        setUncheckedVisible([])
        return
    }

    const getCheckedNotifs = (listNotifs) => {
        if (listNotifs.length != 0) {
            const checkedNotifs = listNotifs.filter((notif) => {
                if (notif.checked) {
                    return notif
                }
            })
            // console.log(checkedNotifs, "checkedNotifs")
            setListChecked(checkedNotifs)
            setCheckedVisible(sortNotifs(checkedNotifs.slice(0, elementsPerPage)))
            return
        }
        setListChecked([])
        setCheckedVisible([])
        return
    }

    const updateListChecked = (pageNumberChecked) => {
        let offset = elementsPerPage * pageNumberChecked;

        setCheckedVisible(listChecked.slice(0 + offset, elementsPerPage + offset));
    };

    const nextPageChecked = () => {
        if (elementsPerPage * (pageNumberChecked + 1) >= listChecked.length) return;
        updateListChecked(pageNumberChecked + 1);
        setPageNumberChecked(pageNumberChecked + 1);
    };

    const previousPageChecked = () => {
        if (pageNumberUnchecked === 0) return;
        updateListChecked(pageNumberChecked - 1);
        setPageNumberChecked(pageNumberChecked - 1);
    };

    const updateListUnchecked = (pageNumberUnchecked) => {
        let offset = elementsPerPage * pageNumberUnchecked;

        setUncheckedVisible(listUnchecked.slice(0 + offset, elementsPerPage + offset));
    };

    const nextPageUnchecked = () => {
        if (elementsPerPage * (pageNumberUnchecked + 1) >= listUnchecked.length) return;
        updateListUnchecked(pageNumberUnchecked + 1);
        setPageNumberUnchecked(pageNumberUnchecked + 1);
    };

    const previousPageUnchecked = () => {
        if (pageNumberUnchecked === 0) return;
        updateListUnchecked(pageNumberUnchecked - 1);
        setPageNumberUnchecked(pageNumberUnchecked - 1);
    };

    const forceReload = () => {
        setReload(true)
    }

    const getFullUser = async () => {
        return await UserService.getUserByEmail(loggedUser.courriel)
    }
    return (
        <div>
            <h1 className="text-center">Unchecked Notifications</h1>
            {uncheckedVisible.map((notification) =>
                <div>
                    <Notification key={notification.id} notification={notification} forceReload={forceReload} />
                    <br />
                </div>
            )}
            <div className="row">
                <div className="col-3"></div>
                <button onClick={previousPageUnchecked} className="btn bg-secondary col-3 m-1">
                    «
                </button>
                <button onClick={nextPageUnchecked} className="btn bg-secondary col-3 m-1">
                    »
                </button>
                <div className="col-3"></div>
            </div>

            <h1 className="text-center">Checked Notifications</h1>
            {checkedVisible.map((notification) =>
                <div>
                    <Notification key={notification.id} notification={notification} forceReload={forceReload} />
                    <br />
                </div>
            )}
            <div className="row">
                <div className="col-3"></div>
                <button onClick={previousPageChecked} className="btn bg-secondary col-3 m-1">
                    «
                </button>
                <button onClick={nextPageChecked} className="btn bg-secondary col-3 m-1">
                    »
                </button>
                <div className="col-3"></div>
            </div>


        </div>
    )
}

export default NotificationViewer
