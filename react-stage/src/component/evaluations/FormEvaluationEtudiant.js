import React, { useState } from 'react'

const FormEvaluationEtudiant = ({contrat}) => {
    const [values, setValues] = useState({
        evaluationGrid: [],
        moniteurFonction: '',
        commentairesProductivite: '',
        commentairesTravail: '',
        commentairesRelations: '',
        commentairesAttitude: '',
        commentairesGlobale: '',
        communiqueAuStagiaire: false,
        heuresEncadrementParSemaine: 0,
        garderStagiaire: false,
        commentairesFormation: '',
        contrat: contrat
    })
    const [evaluationGrid] = useState([])

    const onSubmit = async (e) => {
        e.preventDefault();

    }

    return (
        <div>
            <h1>Évaluation du stagiaire</h1>
            <form onSubmit={onSubmit}>
                <label>Nom de l'élève: </label>
                <input type="text" value={values.contrat.etudiant.prenom + ' ' + values.contrat.etudiant.nom} readOnly />
                <label>Programme d'études: </label>
                <input type="text" value={values.contrat.etudiant.programme} />
            </form>
        </div>
    )
}

export default FormEvaluationEtudiant
