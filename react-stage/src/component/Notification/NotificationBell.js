import React, { useState, useContext, useEffect, useRef } from 'react'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faBell } from '@fortawesome/free-solid-svg-icons'
import { UserInfoContext } from '../../contexts/UserInfo'
import UserService from '../../services/UserService'
import NotificationService from '../../services/NotificationService'
import { Link } from 'react-router-dom';
import Swal from "sweetalert2";



const NotificationBell = () => {
    const [listNotifs, setListNotifs] = useState([])
    const [listUnchecked, setListUnchecked] = useState([])
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const [reload, setReload] = useState(false)
    const firstRender = useRef(true)

    useEffect(async () => {
        if (reload) {
            await getNotifications()
            // console.log(listNotifs, "listNotifs")
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
        firstRender.current = false
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
                        toastNewNotifications(listNotifs, notifications)
                        setListNotifs(notifications)
                        // console.log(notifications, "setting notifs etudiant")
                        break
                    case "SUPERVISEUR":
                        notifications = await NotificationService.getAllNotificationBySuperviseur(fullUser.id)
                        toastNewNotifications(listNotifs, notifications)
                        setListNotifs(notifications)
                        // console.log(notifications, "setting notifs superv")
                        break
                    case "MONITEUR":
                        notifications = await NotificationService.getAllNotificationByMoniteur(fullUser.id)
                        toastNewNotifications(listNotifs, notifications)
                        setListNotifs(notifications)
                        // console.log(notifications, "setting notifs Moniteur")
                        break
                    case "GESTIONNAIRE":
                        notifications = await NotificationService.getAllNotificationGestionnaire()
                        toastNewNotifications(listNotifs, notifications)
                        setListNotifs(notifications)
                        // console.log(notifications, "setting notifs gestionnaire")
                        break
                }
            }
        }
    }

    const toastNewNotifications = (previousNotificationsList, newNotificationsList) => {
        // console.log(previousNotificationsList, "previousssssSSSSSSSSSS")
        // console.log(newNotificationsList, "NEWWWWWWWWWWWWWWW")

        const newNotifications = newNotificationsList.filter(
            (newNotification) =>
                !previousNotificationsList.some(
                    (previousNotification) =>
                        newNotification.id === previousNotification.id
                )
        )
        if (!firstRender) {
            // console.log(newNotifications, "DIFFERENCEEE")
            newNotifications.forEach(notification => {
                toastNotification(notification)
            })
        }


    }

    const toastNotification = (notification) => {
        if (reload) {
            let iconType = "info"
            if (notification.status == "URGENT") {
                iconType = "warning"
            }
            Swal.fire({
                toast: true,
                icon: iconType,
                title: notification.content,
                animation: false,
                position: 'top-right',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.addEventListener('mouseenter', Swal.stopTimer)
                    toast.addEventListener('mouseleave', Swal.resumeTimer)
                }
            })
        }
    }


    const getUncheckedNotifs = (listNotifs) => {
        if (listNotifs.length != 0) {
            const uncheckedNotifs = listNotifs.filter((notif) => {
                if (!notif.checked) {
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
