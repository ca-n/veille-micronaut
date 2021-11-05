import React, { useContext, useState, useEffect } from 'react'
import { UserInfoContext } from '../../contexts/UserInfo'
import SuperviseurDashboard from '../superviseurDashboard/SuperviseurDashboard'


const Dashboard = () => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    console.log(loggedUser)

    return (
        <>
            {
                loggedUser.role === "SUPERVISEUR" ?
                    < SuperviseurDashboard />
                    :
                    null
            }
        </>
    )
}

export default Dashboard