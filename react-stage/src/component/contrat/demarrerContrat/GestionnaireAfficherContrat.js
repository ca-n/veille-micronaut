import React, { useState, useEffect, useContext } from 'react'
import { UserInfoContext } from '../../../contexts/UserInfo';

const GestionnaireAfficherContrat = () => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)

    return (
        <>
            <p>GestionnaireAfficherContrat</p>
        </>
    )
}

export default GestionnaireAfficherContrat
