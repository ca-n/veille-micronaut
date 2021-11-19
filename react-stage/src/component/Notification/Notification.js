import React, { useEffect } from 'react'
import NotificationService from '../../services/NotificationService'

const Notification = ({ notification, forceReload }) => {


    const checkNotification = async () => {
        notification.checked = true
        console.log(notification, "check before save")
        await NotificationService.saveNotification(notification)
        forceReload()
    }

    useEffect(() => {
        console.log(notification, "in component")
    }, [])
    return (
        <div className="row">
            <div className="col-3"></div>
            <div className="col-6 border border-secondary">
                {notification.status == "ALERT" &&
                    <h4 className="text-warning text-center">ALERT</h4>
                }
                {notification.status == "URGENT" &&
                    <h4 className="text-danger text-center">URGENT</h4>
                }
                <hr className="solid" />
                <h3 className="text-center">{notification.content}</h3>
                <br />
                <div className="row">
                    <div className="col-6">
                        {!notification.checked &&
                            <div>
                                <button onClick={checkNotification} className="btn bg-warning">Rendre la notification checked</button>
                            </div>
                        }
                    </div>
                    <p className="text-right col-6">Session: {notification.session}</p>

                </div>
            </div>
            <div className="col-3"></div>
        </div>
    )
}

export default Notification
