import React from 'react'
import useNewContrat from './useNewContrat'
import validateInfoContrat from './validateInfoContrat';


const NewContrat = () => {
    const {handleChange, values, handleSubmit, errors} = useNewContrat(validateInfoContrat);

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
                        Prenom
                    </label>
                    <input id="offre" type="text" name="offre" className="form-input" placeholder="Entrez votre prenom" value={values.offre} onChange={handleChange}></input>
                    {errors.offre && <p>{errors.offre}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="collegeEngagement"
                        className="form-label">
                        Prenom
                    </label>
                    <input id="collegeEngagement" type="text" name="collegeEngagement" className="form-input" placeholder="Entrez votre prenom" value={values.collegeEngagement} onChange={handleChange}></input>
                    {errors.collegeEngagement && <p>{errors.collegeEngagement}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="entrepriseEngagement"
                        className="form-label">
                        Prenom
                    </label>
                    <input id="entrepriseEngagement" type="text" name="entrepriseEngagement" className="form-input" placeholder="Entrez votre prenom" value={values.entrepriseEngagement} onChange={handleChange}></input>
                    {errors.entrepriseEngagement && <p>{errors.entrepriseEngagement}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="etudiantEngagement"
                        className="form-label">
                        Prenom
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
