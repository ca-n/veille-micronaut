import React, { useState, useEffect, useContext } from 'react'
import { UserInfoContext } from '../../../contexts/UserInfo';
import ContratService from '../../../services/ContratService';

const EtudiantAfficherContrat = () => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const [listContrats, setListContrats] = useState([])
    const [listOffres, setListOffres] = useState([])
    const [listEtudiants, setListEtudiants] = useState([])
    const [values, setValues] = useState({})
    const [contrat, setContrat] = useState({})

    useEffect(() => {

        const getContrats = async () => {
            let contrat
            contrat = await ContratService.getContratsByEtudiantEmail(loggedUser.courriel)
            console.log(contrat, "contrat")
            //setListOffres(dbOffres)
            //setValuesOnLoad(dbOffres)
            //await getOffres(dbContrats)
            //setListContrats(dbContrats)
            setContrat(contrat)

        }
        getContrats()
    }, [])

    const handleChange = e => {
        const { name, value } = e.target
        setValues({
            ...values,
            [name]: value,
        })

    }

    const getOffres = async (listContrats) => {
        let tempListOffres = []
        listContrats.forEach(contrat => {
            tempListOffres = [...tempListOffres, contrat.offre]
        });
        console.log(tempListOffres, "dbOffres2")
        setListOffres(tempListOffres)
        await setValuesOnLoad(tempListOffres)
    }

    const setValuesOnLoad = async (listOffres) => {
        setValueOffre(listOffres[0])
        await getListEtudiants(listOffres[0].applicants)

    }

    const getListEtudiants = async (listApplicants) => {
        let listEtudiantsContratOffre = await getEtudiantsForContrat(listApplicants)
        //listEtudiantsContratOffre est vide on loadant la page
        console.log(listEtudiantsContratOffre, "listEtudiantsContratOffre")
        setListEtudiants(listEtudiantsContratOffre)
        await setContratValues(listEtudiantsContratOffre[0])
    }

    const getEtudiantsForContrat = async (listApplicants) => {
        let tempListEtudiantsContratOffre = []
        listContrats.forEach(contrat => {
            if (listApplicants.some(applicant => applicant.id === contrat.etudiant.id)) {
                tempListEtudiantsContratOffre = [...tempListEtudiantsContratOffre, contrat.etudiant]
            }
        });
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

    const handleSubmit = e => {
        e.preventDefault()
        const date = new Date()
        contrat.dateSignatureEtudiant = date.toISOString().split('T')[0];
        contrat.etudiantConfirmed = true
        ContratService.saveContrat(contrat)

    }

    //readonly checkbox doesnt work
    return (
        <div className="form-content-right">
            <form className="form" onSubmit={handleSubmit}>
                <h1>Démarrer contrat</h1>

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

                <div className="form-inputs">
                    <label htmlFor="etudiantConfirmed" className="form-label">
                        Signature étudiant
                    </label>
                    <input id="etudiantConfirmed" type="checkbox" name="etudiantConfirmed" className="form-input" placeholder="" checked={contrat.etudiantConfirmed} disabled></input>
                </div>

                <div className="form-inputs">
                    <label htmlFor="gestionnaireConfirmed" className="form-label">
                        Signature gestionnaire
                    </label>
                    <input id="gestionnaireConfirmed" type="checkbox" name="gestionnaireConfirmed" className="form-input" placeholder="" checked={contrat.gestionnaireConfirmed} disabled></input>
                </div>


                <button className="form-input-btn" type="submit">Signer le contrat</button>

            </form>
        </div>
    )
}

export default EtudiantAfficherContrat
