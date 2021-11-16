import React, { useState, useEffect, useContext } from 'react'
import { UserInfoContext } from './../../../contexts/UserInfo';
import GestionnaireAfficherContrat from './GestionnaireAfficherContrat';
import UserAfficherContrat from './UserAfficherContrat';

const AfficherContrat = () => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)

    return (
        <>
            {loggedUser.role === "ETUDIANT" || loggedUser.role === "MONITEUR" ?
                <UserAfficherContrat />
                :
                null
            }
            {loggedUser.role === "GESTIONNAIRE" ?
                <GestionnaireAfficherContrat />
                :
                null
            }
        </>

    )
}

export default AfficherContrat
