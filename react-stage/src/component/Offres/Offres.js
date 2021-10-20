import React from 'react'
import { useState, useEffect, useContext, useRef } from 'react'
import { AiOutlineCheckCircle, AiOutlineCloseCircle, AiOutlineClose } from 'react-icons/ai'
import { UserInfoContext } from '../../contexts/UserInfo'
import ReactModal from 'react-modal'
import './PickList.css'
import OffreService from "../../services/OffreService.js"
import UserService from "../../services/UserService.js"
import { MultiSelect } from "react-multi-select-component"

const Offres = () => {
    const [listOffres, setListOffres] = useState([])
    const [showModal, setShowModal] = useState(false)
    const [currentOffre, setCurrentOffre] = useState({
        titre: String,
        description: String,
        entreprise: String,
        visibiliteEtudiant: Array,
        valid: Boolean
    })
    const offreWhitelist = useRef({
        id: Number,
        whitelistedEtudiant: Array
    })
    const [listAllEtudiant, setListAllEtudiant] = useState([])

    const [listWhitelistedEtudiant, setListWhitelistedEtudiant] = useState([])

    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)


    useEffect(() => {
        const getOffres = async () => {
            const dbOffres = loggedUser.role === "ETUDIANT" ?
                await OffreService.getEtudiantOffres(loggedUser.courriel) :
                await OffreService.getAllOffres()
            setListOffres(dbOffres)
        }
        getOffres()
    }, [])

    useEffect(() => {
        const getListAllEtudiants = async () => {
            const allEtudiants = await UserService.getListAllEtudiants()
            allEtudiants.map((etudiant) =>{
                console.log(etudiant, "logging each etudiant")
            })
            // console.log(JSON.stringify(allEtudiants))
            setListAllEtudiant(allEtudiants)
        }
        getListAllEtudiants()
    }, [])


    useEffect(() => {
        console.log("-----UseEffect Logging--------")
        console.log(offreWhitelist.current, "Offre whitelist obj--- offreWhitelist")
        console.log(listAllEtudiant, "list all etudiant --- listAllEtudiant")
        console.log(listWhitelistedEtudiant, "list whitelisted etudiant --- listWhitelistedEtudiant ")
        console.log(listOffres, "list all offres  ---- listOffres")


    }, [offreWhitelist, listAllEtudiant, listWhitelistedEtudiant, listOffres])

    const getOptionsEtudiant = (listEtudiant, isWhitelist) => {
        if (!isWhitelist) {
            listEtudiant = () => {
                return listEtudiant.filter(listWhitelistedEtudiant)
                // applique enleve les elements deja dans le whitelist de la liste d'etudiant pour ne pas avoir de doublons
            }
        }
        console.log(JSON.stringify(listEtudiant), "++++++++++++++++++++++++++")
        // return listAllEtudiant.map(etudiant => {
        //     let etudiantOption = {}
        //     etudiantOption.label = etudiant.prenom + " " + etudiant.nom
        //     etudiantOption.value = etudiant.nom
        //     return etudiantOption
        // })
        return listEtudiant.map(etudiant =>{
            console.log(etudiant)
            let etudiantOption = {}
            etudiantOption.label = etudiant.prenom + " " + etudiant.nom
            etudiantOption.value = etudiant.nom
            return etudiantOption
        })
    }

    const getListEtudiantFromOptions = (listWhitelistOptions) => {

        return listWhitelistOptions.map(option => {
            let etudiant = {}
            etudiant = option.value
            return etudiant
        })
    }


    const onClickOffre = (offre) => {
        setCurrentOffre(offre)
        getOffreWhitelist(offre)
        setShowModal(true)

    }
    const getOffreWhitelist = (offre) => {
        console.log(offre, "offre for fetch")
        fetch(`http://localhost:9191/stage/offre/whitelist/${offre.id}`)
            .then(res => {
                console.log(res, "resultat res")

                if (res.ok) {
                    return res.json()
                }
                throw res

            })
            .then(data => {
                // setOffreWhitelist(
                //     data
                // )
                console.log(data, "logging data of whitelist")
                offreWhitelist.current = data
                listAllEtudiant.map(s => console.log(s, "________________________________"));
                
                console.log(JSON.stringify(data), "STRINGIFIEDDDDDDD")
                setListWhitelistedEtudiant(
                    getOptionsEtudiant(JSON.stringify(data.whitelistedEtudiant), true)
                )
            }
            )
            .catch(error => {
                alert("Incapable de retrouver le whitelist")
            })
    }

    const onClickClose = () => {
        setCurrentOffre({})
        setShowModal(false)
    }

    const onToggleValid = () => {
        setCurrentOffre((offre) => ({
            ...offre,
            valid: !offre.valid
        }))
    }

    useEffect(() => {
        console.log(currentOffre)
    }, [currentOffre])

    const onClickSave = () => {
        setCurrentOffre({...currentOffre, visibiliteEtudiant: getListEtudiantFromOptions(listWhitelistedEtudiant)})
        saveOffre(currentOffre)
        // setOffreWhitelist({ ...offreWhitelist, whitelistedEtudiant: getListEtudiantFromOptions(listWhitelistedEtudiant) })
        // offreWhitelist.current = { ...offreWhitelist, whitelistedEtudiant: getListEtudiantFromOptions(listWhitelistedEtudiant) }
        // saveWhitelist(offreWhitelist.current)
        onClickClose()
    }

    const saveWhitelist = async (whitelist) => {
        const res = await fetch('http://localhost:9191/stage/offre',
            {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json',
                },
                body: JSON.stringify(whitelist)
            })
        await res.json()
        updateOffres()
    }

    const saveOffre = async (offre) => {
        const res = await fetch('http://localhost:9191/stage/offre',
            {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json',
                },
                body: JSON.stringify(offre)
            })
        await res.json()
        updateOffres()
    }

    const updateOffres = async () => {
        const dbOffres = loggedUser.role === "ETUDIANT" ?
            OffreService.getEtudiantOffres(loggedUser.courriel) :
            OffreService.getAllOffres()
        setListOffres(dbOffres)
    }

    // const offreList = listOffres.map((offre) =>
    //     <tr key={offre.id.toString()}>
    //         <td colSpan='3'>{offre.titre}</td>
    //         <td colSpan='3'>{offre.entreprise}</td>
    //         <td colSpan='1'>{offre.valid ? <AiOutlineCheckCircle color='green' /> : <AiOutlineCloseCircle color='red' />}</td>
    //         <td colSpan='1'><input type='button' onClick={() => onClickOffre(offre)} value='Détails' className='p-1 btn-secondary' /></td>
    //     </tr>)

    // const whitelist = (whitelistedEtudiant) => {
    //     const list = getListEtudiantFromOptions(whitelistedEtudiant)
    //     return list.map((etudiant) =>
    //         <li key={etudiant.id}>{etudiant.prenom + ' ' + etudiant.nom}</li>
    //     )
    // }

    // const etudiantList = listAllEtudiant.map((etudiant) =>
    //     <li><input type="checkbox" checked={false} />{etudiant.nom} {etudiant.prenom}</li>
    // )


    return (
        <div className="container" style={{ textAlign: 'center' }}>
            <h1>Offres</h1>
            <table className="table border">
                <thead>
                    <tr>
                        <th colSpan='3'>Titre</th>
                        <th colSpan='3'>Entreprise</th>
                        <th colSpan='1'>Valide</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {listOffres.map((offre) =>
                        <tr key={offre.id.toString()}>
                            <td colSpan='3'>{offre.titre}</td>
                            <td colSpan='3'>{offre.entreprise}</td>
                            <td colSpan='1'>{offre.valid ? <AiOutlineCheckCircle color='green' /> : <AiOutlineCloseCircle color='red' />}</td>
                            <td colSpan='1'><input type='button' onClick={() => onClickOffre(offre)} value='Détails' className='p-1 btn-secondary' /></td>
                        </tr>)

                    }
                </tbody>
            </table>
            <ReactModal isOpen={showModal} ariaHideApp={false}>
                <div className="container">
                    <AiOutlineClose color='red' size='24px' onClick={onClickClose} />
                    <div className="row">
                        <h3 className="col-2">Titre</h3>
                        <h3 className="col-6">Description</h3>
                        <h3 className="col-2">Entreprise</h3>
                        <h3 className="col-2">Validity</h3>
                    </div>

                    <div className="row mt-4">
                        <div className="col-2">{currentOffre.titre}</div>
                        <div className="col-6">{currentOffre.description}</div>
                        <div className="col-2">{currentOffre.entreprise}</div>
                        <div className="col-2 form-check">
                            <input type='checkbox' name='valid' className="form-check-input" checked={currentOffre.valid} onChange={onToggleValid} />
                            <label className="form-check-label" htmlFor="valid"> Valid </label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-6">
                            <h1>Select Etudiants</h1>
                            <pre>{JSON.stringify(listWhitelistedEtudiant)}</pre>
                            <MultiSelect
                                options={getOptionsEtudiant(listAllEtudiant, false)}
                                value={listWhitelistedEtudiant}
                                onChange={setListWhitelistedEtudiant}
                                labelledBy="Select"
                            />
                        </div>
                        <div className="col-6">
                            <h1>Whitelisted Etudiants</h1>
                            {listWhitelistedEtudiant.map((etudiant, index) =>
                                <li key={index}>{etudiant.label}</li>
                            )}
                        </div>

                    </div>
                    <div className="row text-center">
                        <input type='button' value='Save' onClick={onClickSave}></input>
                    </div>

                </div>
            </ReactModal>
        </div>
    )
}

export default Offres
