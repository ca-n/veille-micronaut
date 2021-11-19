import React, { useState } from 'react'

const FormEvaluationEntreprise = ({contrat, onClickSubmit, onClickCancel, superviseur}) => {
    const [errors, setErrors] = useState({})
    const [evaluationGrid, setEvaluationGrid] = useState(['0', '0', '0', '0', '0', '0', '0', '0', '0', '0'])
    const [garderStagiaire, setGarderStagiaire] = useState(false)
    const [variableShifts, setVariableShifts] = useState(false)
    const [values, setValues] = useState({
        numeroStage: 0,
        evaluationGrid: [],
        commentaires: '',
        stagePrefere: 0,
        nombreStagiaires: 0,
        garderStagiaire: false,
        variableShifts: false,
        heuresEncadrementParSemaineMois1: 0,
        heuresEncadrementParSemaineMois2: 0,
        heuresEncadrementParSemaineMois3: 0,
        contrat: contrat,
        superviseur: superviseur
    })

    const handleChange = e => {
        const {name, value} = e.target
        setValues({
            ...values,
            [name]: value,
        })
    }

    const handleChangeRadio = e => {
        const {name, value} = e.target
        setEvaluationGrid(Object.values({
            ...evaluationGrid,
            [name]: value,
        }))
    }

    const toggleGarderStagiaire = () => setGarderStagiaire(!garderStagiaire)
    const toggleVariableShifts = () => setVariableShifts(!variableShifts)

    const checkErrors = () => {
        let errors = {}

        if (!values.numeroStage) errors.numeroStage = "Numero du stage requis"
        if (!values.commentaires) errors.commentaires = "Commentaires requis"
        if (!values.stagePrefere) errors.stagePrefere = "Stage privilégié requis"
        if (!values.nombreStagiaires) errors.nombreStagiaires = "Nombre de stagiaires requis"
        if (!values.heuresEncadrementParSemaineMois1) errors.heuresEncadrementParSemaineMois = "Nombre d'eures d'encadrement par semaine requis"
        if (!values.heuresEncadrementParSemaineMois2) errors.heuresEncadrementParSemaineMois = "Nombre d'eures d'encadrement par semaine requis"
        if (!values.heuresEncadrementParSemaineMois3) errors.heuresEncadrementParSemaineMois = "Nombre d'eures d'encadrement par semaine requis"

        setErrors(errors)
        return errors
    }

    const onSubmit = (e) => {
        e.preventDefault()
        let errors = checkErrors()
        if (Object.keys(errors).length === undefined || Object.keys(errors).length === 0) {
            let evaluation = values;
            evaluation.evaluationGrid = evaluationGrid
            evaluation.garderStagiaire = garderStagiaire
            evaluation.variableShifts = variableShifts
            onClickSubmit(evaluation)
        }
    }

    return (
        <div>
            <button type="button" className="btn btn-danger" onClick={onClickCancel}>Annuler</button>
            <h1>ÉVALUATION DU MILIEU DE STAGE</h1>
            <form className="container" onSubmit={onSubmit}>
                <div className="border p-5">
                    <h2>IDENTIFICATION DE L'ENTREPRISE</h2>

                    <label>Nom de l'entreprise: </label>
                    <input type="text" value={values.contrat.moniteur.nomEntreprise} readOnly/>

                    <label>Personne contact: </label>
                    <input type="text" value={values.contrat.moniteur.prenom + ' ' + values.contrat.moniteur.nom} readOnly/>

                    <label>Adresse: </label>
                    <input type="text" value={values.contrat.moniteur.adresseEntreprise} readOnly/>

                    <label>Téléphone: </label>
                    <input type="text" value={values.contrat.moniteur.numTelephone} readOnly/>
                </div>

                <div className="border p-5">
                    <h2>IDENTIFICATION DU STAGIAIRE</h2>
                    <label>Nom du stagiaire: </label>
                    <input type="text" value={values.contrat.etudiant.prenom + ' ' + values.contrat.etudiant.nom} readOnly/>
                    
                    <label>Date du stage: </label>
                    <input type="text" value={values.contrat.offre.dateDebut + ' à ' + values.contrat.offre.dateFin} readOnly/>
                    <div className="row pt-4">
                        <div className="col">
                            <label>Stage numero: </label>
                        </div>
                        <div className="col text-center">
                            <p>1</p>
                            <input type="radio" name="numeroStage" value="1" onChange={handleChange} />
                        </div>
                        <div className="col text-center">
                            <p>2</p>
                            <input type="radio" name="numeroStage" value="2" onChange={handleChange} />
                        </div>
                    </div>
                </div>

                <div className="border p-5">
                    <h2>ÉVALUATION</h2>
                    <div className="row mb-3">
                        <div className="col-lg text-center"></div>
                        <div className="col-sm text-center">
                            <p>Totalement en accord</p>
                        </div>
                        <div className="col-sm text-center">
                            <p>Plutôt en accord</p>
                        </div>
                        <div className="col-sm text-center">
                            <p>Plutôt désaccord</p>
                        </div>
                        <div className="col-sm text-center">
                            <p>Totalement désaccord</p>
                        </div>
                        <div className="col-sm text-center">
                            <p>Impossible de se prononcer</p>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-lg text-center">
                            <p>Les tâches confiées au stagiaire sont conformes aux tâches annoncées dans l'entente de stage.</p>
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="0" value="4" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="0" value="3" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="0" value="2" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="0" value="1" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="0" value="0" onChange={handleChangeRadio} />
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-lg text-center">
                            <p>Des mesures d'acceuil facilitent l'intégration du nouveau stagiaire.</p>
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="1" value="4" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="1" value="3" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="1" value="2" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="1" value="1" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="1" value="0" onChange={handleChangeRadio} />
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-lg text-center">
                            <p>Le temps rées consacré a l'encadrement du stagiaire est suffisant.</p>
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="2" value="4" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="2" value="3" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="2" value="2" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="2" value="1" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="2" value="0" onChange={handleChangeRadio} />
                        </div>
                    </div>
                    <div className="col">
                        <label>Préciser le nombre d'heures/semaine: </label>
                        <p>Premier mois</p>
                        <input type="text" name="heuresEncadrementParSemaineMois1" value={values.heuresEncadrementParSemaineMois1} onChange={handleChange} />
                        <p>Deuxième mois</p>
                        <input type="text" name="heuresEncadrementParSemaineMois2" value={values.heuresEncadrementParSemaineMois2} onChange={handleChange} />
                        <p>Troisième mois</p>
                        <input type="text" name="heuresEncadrementParSemaineMois3" value={values.heuresEncadrementParSemaineMois3} onChange={handleChange} />
                        {errors.heuresEncadrementParSemaineMois && <p className="error">{errors.heuresEncadrementParSemaineMois}</p>}
                    </div>
                    <div className="row mb-3">
                        <div className="col-lg text-center"></div>
                        <div className="col-sm text-center">
                            <p>Totalement en accord</p>
                        </div>
                        <div className="col-sm text-center">
                            <p>Plutôt en accord</p>
                        </div>
                        <div className="col-sm text-center">
                            <p>Plutôt désaccord</p>
                        </div>
                        <div className="col-sm text-center">
                            <p>Totalement désaccord</p>
                        </div>
                        <div className="col-sm text-center">
                            <p>Impossible de se prononcer</p>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-lg text-center">
                            <p>L'environment de travail respecte les normes d'hygiène et de sécurité au travail.</p>
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="3" value="4" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="3" value="3" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="3" value="2" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="3" value="1" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="3" value="0" onChange={handleChangeRadio} />
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-lg text-center">
                            <p>Le climat de travail est agréable.</p>
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="4" value="4" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="4" value="3" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="4" value="2" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="4" value="1" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="4" value="0" onChange={handleChangeRadio} />
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-lg text-center">
                            <p>Le milieu de stage est accessible par transport en commun.</p>
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="5" value="4" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="5" value="3" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="5" value="2" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="5" value="1" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="5" value="0" onChange={handleChangeRadio} />
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-lg text-center">
                            <p>Le salaire offert est intéressant pour le stagiaire. (${values.contrat.offre.tauxHoraire}/l'heure)</p>
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="6" value="4" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="6" value="3" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="6" value="2" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="6" value="1" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="6" value="0" onChange={handleChangeRadio} />
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-lg text-center">
                            <p>La communication avec le superviseur de stage facilite le déroulement du stage.</p>
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="7" value="4" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="7" value="3" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="7" value="2" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="7" value="1" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="7" value="0" onChange={handleChangeRadio} />
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-lg text-center">
                            <p>L'équipement fourni est adéquat pour réaliser les tâches confiées.</p>
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="8" value="4" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="8" value="3" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="8" value="2" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="8" value="1" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="8" value="0" onChange={handleChangeRadio} />
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-lg text-center">
                            <p>Le volume de travail est acceptable.</p>
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="9" value="4" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="9" value="3" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="9" value="2" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="9" value="1" onChange={handleChangeRadio} />
                        </div>
                        <div className="col-sm text-center">
                            <input type="radio" name="9" value="0" onChange={handleChangeRadio} />
                        </div>
                    </div>
                </div>
                <label htmlFor="commentaires">COMMENTAIRES</label>
                <input type="text" id="commentaires" name="commentaires" value={values.commentaires} onChange={handleChange} />
                {errors.commentaires && <p className="error">{errors.commentaires}</p>}
                <div className="border p-5">
                    <h2>OBSERVATIONS GÉNÉRALES</h2>
                    <div className="row pt-4">
                        <div className="col">
                            <label>Ce milieu est à privilégier pour le: </label>
                        </div>
                        <div className="col text-center">
                            <p>Premier stage</p>
                            <input type="radio" name="stagePrefere" value="1" onChange={handleChange} />
                        </div>
                        <div className="col text-center">
                            <p>Deuxième stage</p>
                            <input type="radio" name="stagePrefere" value="2" onChange={handleChange} />
                        </div>
                        {errors.stagePrefere && <p className="error">{errors.stagePrefere}</p>}
                    </div>
                    <div className="row pt-4">
                        <div className="col">
                            <label>Ce milieu est ouvert à accueillir: </label>
                        </div>
                        <div className="col text-center">
                            <p>Un stagiaire</p>
                            <input type="radio" name="nombreStagiaires" value="1" onChange={handleChange} />
                        </div>
                        <div className="col text-center">
                            <p>Deux stagiaires</p>
                            <input type="radio" name="nombreStagiaires" value="2" onChange={handleChange} />
                        </div>
                        <div className="col text-center">
                            <p>Trois stagiaires</p>
                            <input type="radio" name="nombreStagiaires" value="3" onChange={handleChange} />
                        </div>
                        <div className="col text-center">
                            <p>Plus de trois</p>
                            <input type="radio" name="nombreStagiaires" value="4" onChange={handleChange} />
                        </div>
                        {errors.nombreStagiaires && <p className="error">{errors.nombreStagiaires}</p>}
                    </div>
                    <div className="row">
                        <label htmlFor="garderStagiaire">Ce milieu désire accueillir le même stagiaire pour un prochain stage: </label>
                        <input className="m-3" type="checkbox" id="garderStagiaire" name="garderStagiaire" value={values.garderStagiaire} onClick={toggleGarderStagiaire} />
                    </div>
                    <div className="row">
                        <label htmlFor="variableShifts">Ce milieu offre des quarts de travail variables: </label>
                        <input className="m-3" type="checkbox" id="variableShifts" name="variableShifts" value={values.variableShifts} onClick={toggleVariableShifts} />
                    </div>
                </div>
                <input type="submit" value="Soumettre" className="button" />
                {errors.moniteurFonction && <p className="error">Veuillez remplir tout le formulaire</p>}
            </form>
        </div>
    )
}

export default FormEvaluationEntreprise
