import React, { useState, useEffect, useContext } from 'react'
import { UserInfoContext } from '../../../contexts/UserInfo';

const UserAfficherContrat = () => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)

    return (
        <>
            <p>UserAfficherContrat</p>
        </>
    )
}

export default UserAfficherContrat
