import { useState, useEffect } from "react";
import validateInfoEtudiant from "./validateInfoEtudiant";





const useFormEtudiant = (callback,validateInfoEtudiant) => {
    const url = ""
    const [values,setValues] = useState({
        prenom: "",
        nom: "",
        courriel: "",
        password: "",
        password2: "",
        numTelephone: "",
        programme: "",
        adresse: "",
        numMatricule: "",
        hasLicense: "",
        hasVoiture: "",
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

        setErrors(validateInfoEtudiant(values))
        setIsSubmitting(true)
    }


    useEffect(() => {
        if(Object.keys(errors).length === 0 && isSubmitting) {
            callback();

            var request = new XMLHttpRequest();
            request.open('POST', 'http://localhost:9191/stage/etudiant', true);
            request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');


            const etudiant = JSON.stringify(values);

            request.send(etudiant)
        }
    }, [errors]
    );


    return {handleChange, values, handleSubmit, errors}
};

export default useFormEtudiant;