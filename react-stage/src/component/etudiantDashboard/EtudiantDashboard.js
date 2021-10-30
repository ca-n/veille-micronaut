import React, { useContext, useState, useEffect } from 'react'
import { UserInfoContext } from '../../contexts/UserInfo'
import Offres from '../Offres/Offres'
import ContratService from '../../services/ContratService'
import UserService from '../../services/UserService'
import Contrat from '../contrat/Contrat'
import CVService from '../../services/CVService.js'


const EtudiantDashboard = () => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const [fullUser, setFullUser] = useState({
        id: Number,
        prenom: String,
        nom: String,
        courriel: String,
        password: String,
        numTelephone: String,
        role: String,
        programme: String,
        adresse: String,
        numMatricule: String,
        hasLicense: Boolean,
        hasVoiture: Boolean,
        departement: String,
        nomEntreprise: String,
        adresseEntreprise: String,
        specialite: String
    })
    const [contrat, setContrat] = useState()
    const [superviseur, setSuperviseur] = useState({
        prenom: String,
        nom: String,
        courriel: String
    })
    const [moniteur, setMoniteur] = useState({
        prenom: String,
        nom: String,
        courriel: String
    })
    const [cv, setCV] = useState()

    useEffect(() => {
        if (loggedUser.isLoggedIn) {
            fetch(`http://localhost:9191/user/${loggedUser.courriel}`)
                .then(res => {
                    return res.json();
                })
                .then(data => {
                    console.log(data, "data")
                    setFullUser(data)
                })

        }
    }, []);

    useEffect(() => {
        const getCV = async () => {
            //const cv = await CVService.getCVByEtudiantId(fullUser.id)
            setCV(cv)
        }
        getCV()
    }, [])

    useEffect(() => {
        const getSuperviseur = async () => {
            const superviseur = await UserService.getSuperviseur(fullUser.id)
            console.log(superviseur, "superviseur")
            setSuperviseur(superviseur)
        }
        getSuperviseur()
    }, [])

    useEffect(() => {
        const getMoniteur = async () => {
            //const moniteur = await UserService.getMoniteur(fullUser.id)
            setMoniteur(moniteur)
        }
        getMoniteur()
    }, [])

    /*
    useEffect(() => {
        const getContrat = async () => {
            const dbContrat=
                await ContratService.getContrat(loggedUser.courriel)
            console.log(dbContrat, "dbContrat")
            setContrat(dbContrat)
        }
        //getContrat()
    }, [])
    */
    console.log(loggedUser)
    return (
        <>
            <div>
                <h1>Bonjour {fullUser.prenom} {fullUser.nom}</h1>
            </div>
            <div>
                <h1>CV state</h1>
            </div>
            <div>
                <h1>Contact</h1>
                <table>
                    <tr>
                        <th>Role</th>
                        <th>Nom</th>
                        <th>Courriel</th>
                    </tr>
                    <tr>
                        <td>Superviseur</td>
                        <td>{superviseur.prenom} {superviseur.nom}</td>
                        <td>{superviseur.courriel}</td>
                    </tr>
                    {contrat != null ?
                        <tr>
                            <td>Moniteur</td>
                            <td>moniteur.prenom moniteur.nom</td>
                            <td>moniteur.courriel</td>
                        </tr>
                        :
                        null
                    }
                </table>
            </div>
            {contrat != null ?
                <Contrat />
                :
                <Offres />
            }
        </>
    )
}

export default EtudiantDashboard