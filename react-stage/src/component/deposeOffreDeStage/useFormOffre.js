import { useContext, useState, useEffect } from "react";
import { UserInfoContext } from '../../contexts/UserInfo'



const useFormOffre = (callback, validateInfoOffre) => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const url = ""
    const [values, setValues] = useState({
        titre: "",
        description: "",
        entreprise: "",
        isValid: false,
        adresse: "",
        dateDebut: "",
        dateFin: "",
        nbTotalSemaine: 0,
        horaire: "",
        nbTotalHeuresParSemaine: 0,
        tauxHoraire: 0
    })
    const [errors, setErrors] = useState({})
    const [isSubmitting, setIsSubmitting] = useState(false)

    const handleChange = e => {
        const { name, value } = e.target
        if (loggedUser.role == "GESTIONNAIRE") {
            setValues({ ...values, [name]: value, isValid: true })
        } else {
            setValues({
                ...values,
                [name]: value,
            })
        }
    }
    useEffect(() => {
        console.log(values, "TT")
    }, [values])

    const handleSubmit = e => {
        e.preventDefault();
        setErrors(validateInfoOffre(values))
        setIsSubmitting(true)
    }


    useEffect(() => {
        if (Object.keys(errors).length === 0 && isSubmitting) {
            callback();
            var request = new XMLHttpRequest()
            request.open('POST', 'http://localhost:9191/stage/offre', true)
            request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8')

            const offre = JSON.stringify(values)

            request.send(offre)
        }
    }, [errors]
    );


    return { handleChange, values, handleSubmit, errors }
};

export default useFormOffre