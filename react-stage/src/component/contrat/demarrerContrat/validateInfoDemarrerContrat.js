export default function validateInfoDemarrerContrat(values) {
    let errors = {}

    if (!values.etudiantConfirmed) {
        errors.etudiantConfirmed = "La signature de l'étudiant est requise"
    }

    if (!values.moniteurConfirmed) {
        errors.moniteurConfirmed = "La signature du moniteur est requise"
    }

    return errors
}