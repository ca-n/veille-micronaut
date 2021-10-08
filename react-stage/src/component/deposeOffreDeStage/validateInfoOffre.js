export default function validateInfoOffre(values) {
    let errors = {}

    if (!values.titre) {
        errors.titre = "Titre requis"
    }

    if (!values.description) {
        errors.description = "Description requis"
    }

    if (!values.entreprise) {
        errors.entreprise = "Entreprise requis"
    }

    return errors
}