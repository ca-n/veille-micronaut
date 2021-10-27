import React, { useContext, useState, useEffect } from 'react'
import { UserInfoContext } from '../../contexts/UserInfo'
import EtudiantDashboard from '../etudiantDashboard/EtudiantDashboard'


const Dashboard = () => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    console.log(loggedUser)
    return (
        <>
            {
                loggedUser.role === "ETUDIANT" ?
                    < EtudiantDashboard />
                    :
                    null
            }
        </>
    )
}

export default Dashboard