import { useState, useEffect } from "react";
import validateInfoOffre from "./validateInfoOffre";
import Axios from 'axios'



const useFormOffre = (callback,validateInfoOffre) => {
    const url = ""
    const [values,setValues] = useState({
        prenom: "",
        nom: "",
        courriel: "",
        isValid: false
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

        setErrors(validateInfoOffre(values))
        setIsSubmitting(true)
        console.log(values)
    }


    useEffect(() => {
            if(Object.keys(errors).length === 0 && isSubmitting) {
                callback();

                var request = new XMLHttpRequest();
                request.open('POST', 'http://localhost:9191/stage/offre', true);
                request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');


                const offre = JSON.stringify(values);

                request.send(offre)
            }
        }, [errors]
    );


    return {handleChange, values, handleSubmit, errors}
};

export default useFormOffre;