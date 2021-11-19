import React, { useState, useContext, useEffect } from 'react'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faBell } from '@fortawesome/free-solid-svg-icons'
import { UserInfoContext } from '../../contexts/UserInfo'
import UserService from '../../services/UserService'
import NotificationService from '../../services/NotificationService'
import { Link } from 'react-router-dom';



const NotificationBell = () => {
    const [listNotifs, setListNotifs] = useState([])
    const [listUnchecked, setListUnchecked] = useState([])
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const [reload, setReload] = useState(false)

    useEffect(async () => {
        if (reload) {
            await getNotifications()
            console.log(listNotifs, "listNotifs")
            getUncheckedNotifs(listNotifs)
            setReload(false)
        }
    })

    useEffect(() => {
        setListNotifs([])
        setListUnchecked([])
        setReload(true)
    }, [loggedUser])


    useEffect(async () => {
        let interval = setInterval(() => setReload(true), 10000)
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
                        setListNotifs(notifications)
                        console.log(notifications, "setting notifs etudiant")
                        break
                    case "SUPERVISEUR":
                        notifications = await NotificationService.getAllNotificationBySuperviseur(fullUser.id)
                        setListNotifs(notifications)
                        console.log(notifications, "setting notifs superv")
                        break
                    case "MONITEUR":
                        notifications = await NotificationService.getAllNotificationByMoniteur(fullUser.id)
                        setListNotifs(notifications)
                        console.log(notifications, "setting notifs Moniteur")
                        break
                    case "GESTIONNAIRE":
                        notifications = await NotificationService.getAllNotificationGestionnaire()
                        setListNotifs(notifications)
                        console.log(notifications, "setting notifs gestionnaire")
                        break
                }
            }
        }
    }

    const getUncheckedNotifs = (listNotifs) => {
        if (listNotifs.length != 0) {
            const uncheckedNotifs = listNotifs.filter((notif) => {
                if (!notif.isChecked) {
                    return notif
                }
            })
            console.log(uncheckedNotifs, "uncheckednotifs")
            setListUnchecked(uncheckedNotifs)
            return
        }
        setListUnchecked([])
        return
    }

    const getFullUser = async () => {
        return await UserService.getUserByEmail(loggedUser.courriel)
    }

    return (
        <div>
            {/* <span class="fa-stack fa-5x has-badge" data-count="8,888,888">
                <i class="fa fa-circle fa-stack-2x"></i>
                <i class="fa fa-bell fa-stack-1x fa-inverse"></i>
            </span> */}
            {listUnchecked.length != 0 ?

                <Link to="/notification">
                    <FontAwesomeIcon className="text-warning" icon={faBell} size="2x" />
                    <span>{listUnchecked.length}</span>
                </Link>
                :
                <Link to="/notification">
                    <FontAwesomeIcon icon={faBell} size="2x" />
                </Link>
            }

        </div>
    )
}

export default NotificationBell
