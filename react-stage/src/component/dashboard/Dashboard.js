import React from 'react'
import SuperviseurDashboard from '../superviseurDashboard/SuperviseurDashboard'


const Dashboard = () => {
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