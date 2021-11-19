import React, { useState, useEffect, useContext } from 'react'
import { UserInfoContext } from '../../../contexts/UserInfo'
import OffreService from '../../../services/OffreService'
import ContratService from '../../../services/ContratService'
import { get } from 'request'
import validateInfoDemarrerContrat from './validateInfoDemarrerContrat'

const GestionnaireAfficherContrat = () => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const [listContrats, setListContrats] = useState([])
    const [listOffres, setListOffres] = useState([])
    const [listEtudiants, setListEtudiants] = useState([])
    const [values, setValues] = useState({})
    const [contrat, setContrat] = useState({})
    const [errors, setErrors] = useState({})

    useEffect( async () => {

            let dbContrats
            dbContrats = await ContratService.getAllContrats()
            console.log(dbContrats, "dbContrats")
            //setListOffres(dbOffres)
            //setValuesOnLoad(dbOffres)
            getOffres(dbContrats)
            setListContrats(dbContrats)

        
    }, [])

    const handleChange = e => {
        const { name, value } = e.target
        setValues({
            ...values,
            [name]: value,
        })

    }

    const getOffres =  (listContrats) => {
        console.log(listContrats,"LISTCONTRAT")
        let tempListOffres = []
        listContrats.forEach(contrat => {
            tempListOffres = [...tempListOffres, contrat.offre]
        });
        console.log(tempListOffres, "dbOffres2")
        setListOffres(tempListOffres)
        console.log(tempListOffres,"|||||||")
        setValuesOnLoad(/*listContrats,*/ tempListOffres)
    }

    const setValuesOnLoad = (/*listContrats,*/ listOffres) => {
        getListEtudiants(/*listContrats,*/listOffres[0].applicants)
        setValueOffre(listOffres[0])
        console.log(listOffres[0].applicants, "??????")

    }

    const getListEtudiants = (/*listContrats,*/listApplicants) => {
        let listEtudiantsContratOffre = getEtudiantsForContrat(/*listContrats,*/listApplicants)
        //listEtudiantsContratOffre est vide on loadant la page
        console.log(listEtudiantsContratOffre, "listEtudiantsContratOffre")
        setListEtudiants(listEtudiantsContratOffre)
        setContratValues(listEtudiantsContratOffre[0])
    }

    const getEtudiantsForContrat = (/*listContrats,*/listApplicants) => {
        console.log(listContrats, "123344listContrats")
        let tempListEtudiantsContratOffre = []
        listContrats.forEach(contrat => {
            if (listApplicants.some(applicant => applicant.id === contrat.etudiant.id)) {
                tempListEtudiantsContratOffre = [...tempListEtudiantsContratOffre, contrat.etudiant]
            }
        });
        console.log(tempListEtudiantsContratOffre, "123344")
        return tempListEtudiantsContratOffre
    }

    const onChangeOffre = (e) => {
        let offre = JSON.parse(e.target.value)
        setValueOffre(offre)
        setValueMoniteur(offre.moniteur)
        getListEtudiants(offre.applicants)

    }

    const onChangeEtudiant = async (e) => {
        let etudiant = JSON.parse(e.target.value)

        await setContratValues(etudiant)
    }

    const setValueMoniteur = (moniteur) => {
        values.moniteur = moniteur
    }

    const setValueOffre = (offre) => {
        values.offre = offre
    }

    const setValueEtudiant = (etudiant) => {
        values.etudiant = etudiant
    }

    const setContratValues = async (etudiant) => {
        setValueEtudiant(etudiant)
        let tempContrat = {}
        console.log(etudiant, "ETUDIANT")
        console.log(listContrats, "LISTCONTRATS")
        listContrats.forEach(contrat => {
            if (contrat.etudiant.id === etudiant.id) {
                tempContrat = contrat
            }
        });
        console.log(tempContrat, "tempContrat>>>")
        setContrat(tempContrat)
    }

    const isAlreadyStarted = (contrat) => {
        if(contrat.gestionnaireConfirmed == true && contrat.moniteurConfirmed == true && contrat.etudiantConfirmed == true){
            return true
        }else{
            return false
        }
    }

    const handleSubmit = e => {
        e.preventDefault()
        setErrors(validateInfoDemarrerContrat(contrat))
        if (Object.keys(errors).length === 0 && !isAlreadyStarted(contrat)) {
            const date = new Date()
            contrat.dateSignatureGestionnaire = date.toISOString().split('T')[0];
            contrat.gestionnaireConfirmed = true
            ContratService.saveContrat(contrat)
        }
    }

    return (
        <div className="form-content-right">
            <form className="form" onSubmit={handleSubmit}>
                <h1>Démarrer contrat</h1>

                <div className="form-inputs">
                    <label htmlFor="offre"
                        className="form-label">
                        Offre
                    </label>
                    <select onChange={onChangeOffre}>
                        {listOffres.map((offre) => (
                            <option value={JSON.stringify(offre)}>{offre.titre}</option>
                        ))}
                    </select>
                </div>

                <div className="form-inputs">
                    <label htmlFor="etudiant"
                        className="form-label">
                        Étudiant
                    </label>
                    <select onChange={onChangeEtudiant}>
                        {listEtudiants.map((etudiant) => (
                            <option value={JSON.stringify(etudiant)}>{etudiant.prenom} {etudiant.nom}</option>
                        ))}
                    </select>
                </div>

                <div className="form-inputs">
                    <label htmlFor="collegeEngagement"
                        className="form-label">
                        Le Collège s’engage à :
                    </label>
                    <input id="collegeEngagement" type="text" name="collegeEngagement" className="form-input" placeholder="Entrez les engagments du collège" defaultValue={contrat.collegeEngagement} readOnly></input>
                </div>

                <div className="form-inputs">
                    <label htmlFor="entrepriseEngagement"
                        className="form-label">
                        L’entreprise s’engage à :
                    </label>
                    <input id="entrepriseEngagement" type="text" name="entrepriseEngagement" className="form-input" placeholder="Entrez les engagments de l'entreprise" defaultValue={contrat.entrepriseEngagement} readOnly></input>
                </div>

                <div className="form-inputs">
                    <label htmlFor="etudiantEngagement"
                        className="form-label">
                        L’étudiant s’engage à :
                    </label>
                    <input id="etudiantEngagement" type="text" name="etudiantEngagement" className="form-input" placeholder="Entrez les engagments de l'étudiant" defaultValue={contrat.etudiantEngagement} readOnly></input>
                </div>

                <div className="form-inputs">
                    <label htmlFor="moniteurConfirmed" className="form-label">
                        Signature moniteur
                    </label>
                    <input id="moniteurConfirmed" type="checkbox" name="moniteurConfirmed" className="form-input" placeholder="" checked={contrat.moniteurConfirmed} disabled></input>
                </div>
                {errors.moniteurConfirmed && <p>{errors.moniteurConfirmed}</p>}

                <div className="form-inputs">
                    <label htmlFor="etudiantConfirmed" className="form-label">
                        Signature étudiant
                    </label>
                    <input id="etudiantConfirmed" type="checkbox" name="etudiantConfirmed" className="form-input" placeholder="" checked={contrat.etudiantConfirmed} disabled></input>
                </div>
                {errors.etudiantConfirmed && <p>{errors.etudiantConfirmed}</p>}

                <div className="form-inputs">
                    <label htmlFor="gestionnaireConfirmed" className="form-label">
                        Signature gestionnaire
                    </label>
                    <input id="gestionnaireConfirmed" type="checkbox" name="gestionnaireConfirmed" className="form-input" placeholder="" checked={contrat.gestionnaireConfirmed} disabled></input>
                </div>


                <button className="form-input-btn" type="submit">Signer et démarrer le contrat</button>

            </form>
        </div>
    )
}

export default GestionnaireAfficherContrat
