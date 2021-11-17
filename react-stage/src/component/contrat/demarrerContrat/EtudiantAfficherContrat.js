import React, { useState, useEffect, useContext } from 'react'
import { UserInfoContext } from '../../../contexts/UserInfo';

const EtudiantAfficherContrat = () => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)

    return (
        <>
            <p>EtudiantAfficherContrat</p>
        </>
    )
}

export default EtudiantAfficherContrat
