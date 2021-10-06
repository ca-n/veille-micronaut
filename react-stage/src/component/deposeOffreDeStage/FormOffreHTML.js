import React from 'react'
import useFormOffre from './useFormOffre'
import validateInfoOffre from './validateInfoOffre';
import './FormOffre';
import { Link } from 'react-router-dom';



const FormOffreHTML = ({submitForm}) => {
    const {handleChange,values, handleSubmit, errors} = useFormOffre(submitForm,validateInfoOffre);
    return (
        <div className="form-content-right">
            <form className="form" onSubmit={handleSubmit}>
                <h1>Créez votre compte offre de stage dès maintenant!</h1>

                <div className="form-inputs">
                    <label htmlFor="titre"
                    className="form-label">
                        Titre
                    </label>
                    <input id="titre" type="text" name="titre" className="form-input" placeholder="Entrez le titre" value={values.titre} onChange={handleChange}></input>
                    {errors.titre && <p>{errors.titre}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="description"
                    className="form-label">
                        Description
                    </label>
                    <input id="description" type="text" name="description" className="form-input" placeholder="Entrez la description" value={values.description} onChange={handleChange}></input>
                    {errors.description && <p>{errors.description}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="entreprise"
                    className="form-label">
                        Entreprise
                    </label>
                    <input id="entreprise" type="text" name="entreprise" className="form-input" placeholder="Entrez votre entreprise" value={values.email} onChange={handleChange}></input>
                    {errors.entreprise && <p>{errors.entreprise}</p>}
                </div>

                <button className="form-input-btn" type="submit">Soumettre l'offre</button>
            </form>
        </div>
    )
}

export default FormOffreHTML

