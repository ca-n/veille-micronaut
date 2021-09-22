import React from 'react'
import useFormMoniteur from './useFormMoniteur'
import validateInfoMoniteur from './validateInfoMoniteur';
import './FormMoniteur';


const FormMoniteurHTML = ({submitForm}) => {
    const {handleChange, values, handleSubmit, errors} = useFormMoniteur(submitForm, validateInfoMoniteur);
    return (
        <div className="form-content-right">
            <form className="form" onSubmit={handleSubmit}>
                <h1>Créez votre compte dès maintenant!</h1>

                <div className="form-inputs">
                    <label htmlFor="prenom"
                           className="form-label">
                        Prenom
                    </label>
                    <input id="prenom" type="text" name="prenom" className="form-input"
                           placeholder="Entrez votre prenom" value={values.prenom} onChange={handleChange}></input>
                    {errors.prenom && <p>{errors.prenom}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="nom"
                           className="form-label">
                        Nom
                    </label>
                    <input id="nom" type="text" name="nom" className="form-input" placeholder="Entrez votre nom"
                           value={values.nom} onChange={handleChange}></input>
                    {errors.nom && <p>{errors.nom}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="courriel"
                           className="form-label">
                        Courriel
                    </label>
                    <input id="courriel" type="email" name="courriel" className="form-input"
                           placeholder="Entrez votre courriel" value={values.email} onChange={handleChange}></input>
                    {errors.courriel && <p>{errors.courriel}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="password"
                           className="form-label">
                        Mot de passe
                    </label>
                    <input id="password" type="password" name="password" className="form-input"
                           placeholder="Entrez votre mot de passe" value={values.password}
                           onChange={handleChange}></input>
                    {errors.password && <p>{errors.password}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="password2"
                           className="form-label">
                        Confirmez le mot de passe
                    </label>
                    <input id="password2" type="password" name="password2" className="form-input"
                           placeholder="Confirmez votre mot de passe" value={values.password2}
                           onChange={handleChange}></input>
                    {errors.password2 && <p>{errors.password2}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="numTelephone"
                           className="form-label">
                        Numero de telephone
                    </label>
                    <input id="numTelephone" type="text" name="numTelephone" className="form-input"
                           placeholder="Entrez votre numero de telephone" value={values.numTelephone}
                           onChange={handleChange}></input>
                    {errors.numTelephone && <p>{errors.numTelephone}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="nomEntreprise"
                           className="form-label">
                        Nom de l'entreprise
                    </label>
                    <input id="nomEntreprise" type="text" name="nomEntreprise" className="form-input"
                           placeholder="Entrez le nom de votre entreprise" value={values.nomEntreprise}
                           onChange={handleChange}></input>
                    {errors.nomEntreprise && <p>{errors.nomEntreprise}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="adresseEntreprise"
                           className="form-label">
                        Adresse de l'entreprise
                    </label>
                    <input id="adresseEntreprise" type="text" name="adresseEntreprise" className="form-input"
                           placeholder="Entrez l'adresse de votre entreprise" value={values.adresseEntreprise}
                           onChange={handleChange}></input>
                    {errors.adresseEntreprise && <p>{errors.adresseEntreprise}</p>}
                </div>

                <button className="form-input-btn" type="submit">S'inscrire</button>
                <span className="form-input-login">Déjà un compte? Login <a href="#">ici</a></span>
            </form>
        </div>
    )
}

export default FormMoniteurHTML
