import { useState, useEffect } from "react";

const useFormMoniteur = (callback, validateInfoMoniteur) => {
    const url = ""
    const [values,setValues] = useState({
        prenom: "",
        nom: "",
        courriel: "",
        password: "",
        password2: "",
        numTelephone: "",
        nomEntreprise: "",
        adresseEntreprise: "",
    })
    const [errors,setErrors] = useState({})
    const [isSubmitting, setIsSubmitting] = useState(false)

    const handleChange = e => {
        const {name, value} = e.target
        setValues({
            ...values,
            [name]: value,
        })
    }

    const handleSubmit = e =>{
        e.preventDefault();

        setErrors(validateInfoMoniteur(values))
        setIsSubmitting(true)
    }


    useEffect(() => {
        if(Object.keys(errors).length === 0 && isSubmitting) {
            callback();

            var request = new XMLHttpRequest();
            request.open('POST', 'http://localhost:9191/stage/moniteur', true);
            request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');


            const moniteur = JSON.stringify(values);

            request.send(moniteur)
        }
    }, [errors]
    );


    return {handleChange, values, handleSubmit, errors}
};

export default useFormMoniteur;