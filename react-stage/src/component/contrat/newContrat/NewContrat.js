import React, { useState, useEffect } from 'react'
import useNewContrat from './useNewContrat'
import validateInfoContrat from './validateInfoContrat';
import OffreService from '../../../services/OffreService';
import './NewContratCSS.css'


const NewContrat = () => {
    const { handleChange, values, handleSubmit, errors } = useNewContrat(validateInfoContrat);
    const [listOffres, setListOffres] = useState([])
    const [optionOffres, setOptionOffres] = useState([])
    const [optionEtudiants, setOptionEtudiants] = useState([])

    useEffect(() => {
        const getOffres = async () => {
            let dbOffres
            dbOffres = await OffreService.getAllOffres()

            console.log(dbOffres, "dbOffres")
            setListOffres(dbOffres)
            getOptionOffres(dbOffres)
        }
        getOffres()
    }, [])

    const getOptionOffres = (offres) => {
        let newOptionOffre = []
        offres.forEach(offre => {
            console.log(offre, "offre")
            newOptionOffre = [...newOptionOffre, { 'id': offre.id, 'titre': offre.titre }]
        });
        console.log(newOptionOffre, "newOptionOffre")
        setOptionOffres(newOptionOffre)
    }


    //doit recevoir l'offre lors du onChange
    const getOptionEtudiants = (offre) => {
        console.log("allo")
        let listEtudiant = []
        let applicants = offre.applicants
        let newOptionEtudiant = []
        applicants.forEach(etudiant => {
            console.log(etudiant, "etudiant")
            newOptionEtudiant = [...newOptionEtudiant, { 'id': etudiant.id, 'prenom': etudiant.prenom, 'nom': etudiant.nom }]
        });
        console.log(newOptionEtudiant, "newOptionEtudiant")
        setOptionEtudiants(newOptionEtudiant)
    }




    //apres avoir get offre
    //->getMoniteur
    //->list applicant
    //->select etudiant

    return (
        <div className="form-content-right">
            <form className="form" onSubmit={handleSubmit}>
                <h1>Créez un nouveau contrat</h1>


                <div className="form-inputs">
                    <label htmlFor="offre"
                        className="form-label">
                        Offre
                    </label>
                    <select>
                        {optionOffres.map((optionOffre) => (
                            <option onChange={getOptionEtudiants} value={optionOffre.id}>{optionOffre.titre}</option>
                        ))}
                    </select>
                    {errors.offre && <p>{errors.offre}</p>}
                </div>
                
                <div className="form-inputs">
                    <label htmlFor="etudiant"
                        className="form-label">
                        Etudiant
                    </label>
                    <select>
                        {optionEtudiants.map((optionEtudiant) => (
                            <option onChange={getOptionEtudiants} value={optionEtudiant.id}>{optionEtudiant.prenom} {optionEtudiant.nom}</option>
                        ))}
                    </select>
                    {errors.etudiant && <p>{errors.etudiant}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="collegeEngagement"
                        className="form-label">
                        Le Collège s’engage à :
                    </label>
                    <input id="collegeEngagement" type="text" name="collegeEngagement" className="form-input" placeholder="Entrez votre prenom" value={values.collegeEngagement} onChange={handleChange}></input>
                    {errors.collegeEngagement && <p>{errors.collegeEngagement}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="entrepriseEngagement"
                        className="form-label">
                        L’entreprise s’engage à :
                    </label>
                    <input id="entrepriseEngagement" type="text" name="entrepriseEngagement" className="form-input" placeholder="Entrez votre prenom" value={values.entrepriseEngagement} onChange={handleChange}></input>
                    {errors.entrepriseEngagement && <p>{errors.entrepriseEngagement}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="etudiantEngagement"
                        className="form-label">
                        L’étudiant s’engage à :
                    </label>
                    <input id="etudiantEngagement" type="text" name="etudiantEngagement" className="form-input" placeholder="Entrez votre prenom" value={values.etudiantEngagement} onChange={handleChange}></input>
                    {errors.etudiantEngagement && <p>{errors.etudiantEngagement}</p>}
                </div>


                <button className="form-input-btn" type="submit">S'inscrire</button>

            </form>
        </div>
    )
}

export default NewContrat
