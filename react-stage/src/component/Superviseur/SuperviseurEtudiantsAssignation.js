import React from 'react'
import { useState, useEffect, useContext, useRef } from 'react'
import { useHistory } from 'react-router-dom'
import { AiOutlineCheckCircle, AiOutlineCloseCircle, AiOutlineClose } from 'react-icons/ai'
import { UserInfoContext } from '../../contexts/UserInfo'
import ReactModal from 'react-modal'
import './PickList.css'
import OffreService from "../../services/OffreService.js"
import UserService from "../../services/UserService.js"
import { MultiSelect } from "react-multi-select-component"


const SuperviseurEtudiantsAssignation = () => {
    const history = useHistory()
    const [listSuperviseurs, setListSuperviseurs] = useState([])
    const [showModal, setShowModal] = useState(false)
    const [currentSuperviseur, setCurrentSuperviseur] = useState({
        id: Number,
        prenom: String,
        nom: String,
        courriel: String,
        numTelephone: String,
        departement: String,
        specialite: String
    })

    const [listAllEtudiant, setListAllEtudiant] = useState([])

    const [listSelectedEtudiant, setListSelectedEtudiant] = useState([])

    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)


    useEffect(() => {
        // if (!loggedUser.isLoggedIn || (loggedUser.role !== "GESTIONNAIRE" || loggedUser.role !== "ETUDIANT")) history.push("/login")
        const getSuperviseurs = async () => {
            const dbSuperviseurs = await UserService.getListAllSuperviseurs()
            console.log(dbSuperviseurs, "dbSuperviseurs")
            setListSuperviseurs(dbSuperviseurs)
        }
        getSuperviseurs()
    }, [])

    const getListAllEtudiants = async (listSelectedEtudiant) => {
        const allEtudiantsNoSuperviseur = await UserService.getListEtudiantWithoutSuperviseur()
        console.log(allEtudiantsNoSuperviseur, "fetch no superviseur")
        setListAllEtudiant(getOptionsEtudiant(allEtudiantsNoSuperviseur.concat(listSelectedEtudiant)))
    }



    useEffect(() => {
        console.log("-----UseEffect Logging--------")
        console.log(listAllEtudiant, "list all etudiant --- listAllEtudiant")
        console.log(listSelectedEtudiant, "list selected etudiant --- listSelectedEtudiant ")
        console.log(listSuperviseurs, "list all superviseurs  ------------ listSuperviseurs")

        // console.log(getOptionsEtudiant(listAllEtudiant), "filtered list etudiant ===================================")


    }, [listAllEtudiant, listSelectedEtudiant, listSuperviseurs])

    const getOptionsEtudiant = (listEtudiant) => {
        return listEtudiant.map(etudiant => {
            // console.log(etudiant)
            let etudiantOption = {}
            etudiantOption.label = etudiant.prenom + " " + etudiant.nom
            etudiantOption.value = etudiant
            return etudiantOption
        })
    }

    const getListEtudiantFromOptions = (listSelectedOptions) => {

        return listSelectedOptions.map(option => {
            let etudiant = {}
            etudiant = option.value
            return etudiant
        })
    }


    const onClickSuperviseur = async (superviseur) => {
        setCurrentSuperviseur(superviseur)
        const listEtudiantSupervise = await UserService.getListEtudiantSuperviseur(superviseur.id)
        console.log(listEtudiantSupervise)
        setListSelectedEtudiant(getOptionsEtudiant(listEtudiantSupervise))
        getListAllEtudiants(listEtudiantSupervise)
        console.log("______---------------__________", superviseur)

        setShowModal(true)
    }


    const onClickClose = () => {
        setCurrentSuperviseur({})
        setShowModal(false)
    }



    useEffect(() => {
        console.log(currentSuperviseur, "CURRENT SUPERVISEUR")
    }, [currentSuperviseur])

    const onClickSave = async () => {
        console.log("before conversion ||||||||||||", listSelectedEtudiant)
        const selectedEtudiantsObj = getListEtudiantFromOptions(listSelectedEtudiant)
        console.log("after conversion ||||||||||||", selectedEtudiantsObj)

        console.log(currentSuperviseur, "current before save|||||||")
        saveSuperviseur(selectedEtudiantsObj, currentSuperviseur.id)
        console.log(listSuperviseurs, "list offres as save")
        onClickClose()
    }


    const saveSuperviseur = async (selectedEtudiants, idSuperviseur) => {
        const res = await fetch(`http://localhost:9191/user/superviseur/${idSuperviseur}/etudiants`,
            {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json',
                },
                body: JSON.stringify(selectedEtudiants)
            })
        await res.json()
        updateSuperviseurs()
    }

    const updateSuperviseurs = async () => {
        const dbSuperviseurs = await UserService.getListAllSuperviseurs()
        setListSuperviseurs(dbSuperviseurs)
    }


    return (
        <div className="container" style={{ textAlign: 'center' }}>
            <h1>Superviseurs</h1>
            <table className="table border">
                <thead>
                    <tr>
                        <th colSpan='3' style={{ color: "black" }}>Prenom/Nom</th>
                        <th colSpan='3' style={{ color: "black" }}>Departement</th>
                        <th colSpan='3' style={{ color: "black" }}>Specialite</th>
                        <th colSpan='3'></th>
                    </tr>
                </thead>
                <tbody>
                    {listSuperviseurs.map((superviseur) =>
                        <tr key={superviseur.id.toString()}>
                            <td colSpan='3'>{superviseur.prenom} {superviseur.nom}</td>
                            <td colSpan='3'>{superviseur.departement}</td>
                            <td colSpan='3'>{superviseur.specialite}</td>
                            <td colSpan='3'><input type='button' onClick={() => onClickSuperviseur(superviseur)} value='Détails' className='p-1 btn-secondary' /></td>
                        </tr>)
                    }



                </tbody>
            </table>
            <ReactModal isOpen={showModal} ariaHideApp={false}>
                <div className="container text-center">
                    <AiOutlineClose color='red' size='24px' onClick={onClickClose} />
                    <div className="row">
                        <div className="col-2" >Prenom/Nom</div>
                        <div className="col-2" >Departement</div>
                        <div className="col-2" ># Telephone</div>
                        <div className="col-2" >Courriel</div>
                        <div className="col-2" >Specialite</div>
                        <div className="col-2"></div>
                    </div>

                    <div className="row mt-4">
                        <div className="col-2">{currentSuperviseur.prenom} {currentSuperviseur.nom}</div>
                        <div className="col-2">{currentSuperviseur.departement}</div>
                        <div className="col-2">{currentSuperviseur.numTelephone}</div>
                        <div className="col-2">{currentSuperviseur.courriel}</div>
                        <div className="col-2">{currentSuperviseur.specialite}</div>
                        <div className="col-2"></div>
                    </div>
                    <br />
                    <br />
                    <br />


                    {loggedUser.role == "GESTIONNAIRE" &&
                        <div className="mt-4">
                            <div className="row">
                                <div className="col-6">
                                    <h1>Select Etudiants</h1>
                                    <MultiSelect
                                        options={listAllEtudiant}
                                        value={listSelectedEtudiant}
                                        onChange={setListSelectedEtudiant}
                                        labelledBy="Select"
                                    />
                                </div>
                                <div className="col-6">
                                    <h1>Whitelisted Etudiants</h1>
                                    {listSelectedEtudiant.map((etudiant, index) =>
                                        <li key={index}>{etudiant.label}</li>
                                    )}
                                </div>

                            </div>
                            <div className="row text-center">
                                <input type='button' value='Save' onClick={onClickSave}></input>
                            </div>
                        </div>
                    }

                </div>
            </ReactModal>
        </div>
    )
}

export default SuperviseurEtudiantsAssignation
