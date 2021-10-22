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

    if (!values.adresse) {
        errors.adresse = "Adresse requis"
    }
    
    if (!values.dateDebut) {
        errors.dateDebut = "Date de début requis"
    }

    if (!values.dateFin) {
        errors.dateFin = "Date de fin requis"
    }

    if (!values.nbTotalSemaine) {
        errors.nbTotalSemaine = "Nombre total de semaine requis"
    }

    if (!values.horaire) {
        errors.horaire = "Horaire requis"
    }

    if (!values.nbTotalHeuresParSemaine) {
        errors.nbTotalHeuresParSemaine = "Nombre total d'heures par semaine requis"
    }

    if (!values.tauxHoraire) {
        errors.tauxHoraire = "Taux horaire requis"
    }

    return errors
}