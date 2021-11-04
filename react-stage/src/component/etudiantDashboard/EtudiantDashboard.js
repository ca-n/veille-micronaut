import React, { useContext, useState, useEffect } from 'react'
import { UserInfoContext } from '../../contexts/UserInfo'
import Offres from '../Offres/Offres'
import Contrat from '../contrat/Contrat'
import VoirCVState from './VoirCVState'
import './EtudiantDashboard.css'
import UserService from '../../services/UserService'
import ContratService from '../../services/ContratService'


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

    useEffect(() => {
        if (loggedUser.isLoggedIn) {
            fetch(`http://localhost:9191/user/${loggedUser.courriel}`)
                .then(res => {
                    return res.json();
                })
                .then(data => {
                    console.log(data, "data")
                    setFullUser(data)
                    setSuperviseur(data.superviseur)
                    getMoniteur(data.id)
                    getContrat(data.id)
                })

        }
    }, []);

    const getMoniteur = async (id) => {
        const moniteur = await UserService.getMoniteur(id)
        setMoniteur(moniteur)
    }

    const getContrat = async (id) => {
        const dbContrat = await ContratService.getContrat(id)
        console.log(dbContrat, "dbContrat")
        setContrat(dbContrat)
    }

    console.log(loggedUser)
    return (
        <>
            <div>
                <h1>Bonjour {fullUser.prenom} {fullUser.nom}</h1>
            </div>
            <div>
                <h1>CV state</h1>
                <VoirCVState />
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