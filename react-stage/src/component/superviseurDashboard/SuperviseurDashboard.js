import React, { useContext, useState, useEffect } from 'react'
import { UserInfoContext } from '../../contexts/UserInfo'
import UserService from '../../services/UserService'


const SuperviseurDashboard = () => {
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

    const [listEtudiants, setListEtudiants] = useState([])

    useEffect(() => {
        if (loggedUser.isLoggedIn) {
            fetch(`http://localhost:9191/user/${loggedUser.courriel}`)
                .then(res => {
                    return res.json();
                })
                .then(data => {
                    console.log(data, "data")
                    //could acces superviseur par ici
                    getEtudiants(data.id)
                    setFullUser(data)
                })

        }
    }, []);

    const getEtudiants = async (id) => {
        const dbEtudiants =
            await UserService.getEtudiantsForSuperviseur(id)
        console.log(dbEtudiants, "dbEtudiants")
        setListEtudiants(getOptionsEtudiant(dbEtudiants))
    }

    const getOptionsEtudiant = (listEtudiant) => {
        return listEtudiant.map(etudiant => {
            // console.log(etudiant)
            let etudiantOption = {}
            etudiantOption.label = etudiant.prenom + " " + etudiant.nom
            etudiantOption.value = etudiant
            return etudiantOption
        })
    }

    //SAME BUG
    /*
    useEffect(() => {
        console.log(fullUser.id, "fullUser.id")
        const getEtudiants = async () => {
            const dbEtudiants =
                await UserService.getEtudiantsForSuperviseur(fullUser.id)
            console.log(dbEtudiants, "dbEtudiants")
            setListEtudiants(dbEtudiants)
        }
        getEtudiants()
    }, [])
    */

    /*
        const etudiantsList = listEtudiants.map((etudiant) =>
            <tr key={etudiant.id.toString()}>
                <td>{etudiant.prenom} {etudiant.nom}</td>
                <td>{etudiant.courriel}</td>
            </tr>);
    */
    return (
        <>
            <div>
                <h1>Bonjour {fullUser.prenom} {fullUser.nom}</h1>
            </div>
            {listEtudiants.length > 0 ?
                <div>
                    <h1>List étudiant</h1>
                    <table>
                        <tr>
                            <th>Nom</th>
                            <th>Courriel</th>
                        </tr>

                        {listEtudiants.map((etudiant) =>
                            <tr key={etudiant.id.toString()}>
                                <td>{etudiant.prenom} {etudiant.nom}</td>
                                <td>{etudiant.courriel}</td>
                            </tr>
                        )}
                    </table>
                </div>
                : null}

        </>
    )
}

export default SuperviseurDashboard