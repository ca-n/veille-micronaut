import React, { useContext, useState, useEffect, useRef } from 'react'
import { UserInfoContext } from '../../../contexts/UserInfo'
import UserService from '../../../services/UserService'
import Offres from '../../Offres/Offres'
import Entrevue from './Entrevue'
import FormEntrevue from './FormEntrevue'
import './MoniteurDashboard.css'

const MoniteurDashboard = () => {
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
    const [listGestionnaires, setListGestionnaires] = useState([])
    const [reloadList, setReloadList] = useState(0)
    const handleReloadList = () => {
        setReloadList(reloadList + 1)
    }



    useEffect(() => {
        if (loggedUser.isLoggedIn) {
            fetch(`http://localhost:9191/user/${loggedUser.courriel}`)
                .then(res => {
                    return res.json();
                })
                .then(data => {
                    console.log(data, "data")
                    setFullUser(data)
                    getGestionnaires()
                })

        }
    }, []);

    const getGestionnaires = async () => {
        const gestionnaires = await UserService.getGestionnaires()
        setListGestionnaires(gestionnaires)
    }

    const gestionnairesList = listGestionnaires.map((gestionnaire) =>
        <tr key={gestionnaire.id.toString()}>
            <td>{gestionnaire.prenom} {gestionnaire.nom}</td>
            <td>{gestionnaire.courriel}</td>
        </tr>);

    return (
        <>
            <div>
                <h1>Bonjour {fullUser.prenom} {fullUser.nom}</h1>
            </div>

            <div>
                <h1>Contact Gestionnaire</h1>
                <table>
                    <tr>
                        <th>Nom</th>
                        <th>Courriel</th>
                    </tr>
                    {gestionnairesList}
                </table>
            </div>
            <Offres />
            <FormEntrevue handleReloadList={handleReloadList} />
            <Entrevue reloadList={reloadList} />
        </>
    )
}

export default MoniteurDashboard