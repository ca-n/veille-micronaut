export default function validateInfoContrat(values) {
    let errors = {}

    if (!values.offre) {
        errors.offre = "L'offre est requise"
    }

    if (!values.etudiant) {
        errors.etudiant = "L'étudiant est requis"
    }

    if (!values.collegeEngagement) {
        errors.collegeEngagement = "Les engagements du collège sont requis"
    }

    if (!values.entrepriseEngagement) {
        errors.entrepriseEngagement = "Les engagements de l'entreprise sont requis"
    }

    if (!values.etudiantEngagement) {
        errors.etudiantEngagement = "Les engagements de l'étudiant sont requis"
    }



    return errors;
}