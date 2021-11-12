export default function validateInfoSuperviseur(values) {
    let errors = {}

    if(!values.prenom) {
        errors.prenom = "Prenom requis"
    }

    if(!values.nom) {
        errors.nom = "Nom requis"
    }

    if(!values.courriel) {
        errors.courriel = "Courriel requis"
    }

    if(!values.password) {
        errors.password = "Mot de passe requis"
    } else if (!/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/i.test(values.password)) {
        errors.password = "Le mot de passe requiert au moins 6 charactères, une majuscule, une minuscule et un chiffre "
    }

    if(!values.password2){
        errors.password2 = "Mot de passe requis"
    } else if (values.password2 !== values.password){
        errors.password2 = "Les mots de passe de correspondent pas"
    }

    if(!values.numTelephone) {
        errors.numTelephone = "Numero de telephone requis"
    }

    if(!values.departement) {
        errors.departement = "Nom du departement requis"
    }

    if(!values.specialite) {
        errors.specialite = "Specialite requise"
    }
    
    return errors;
}