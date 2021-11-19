import { useState, useEffect } from "react"
import ContratService from "../../../services/ContratService"

const useNewContrat = (callback, validateInfoContrat) => {
    const [values, setValues] = useState({})
    const [errors, setErrors] = useState({})
    const [isSubmitting, setIsSubmitting] = useState(false)

    const handleChange = e => {
        const { name, value } = e.target
        setValues({
            ...values,
            [name]: value,
        })

    }

    const handleSubmit = e => {
        e.preventDefault()
        setErrors(validateInfoContrat(values))
        setIsSubmitting(true)

    }

    useEffect(() => {
        if (Object.keys(errors).length === 0 && isSubmitting) {
            callback()

            ContratService.saveContrat(values)
           /* var request = new XMLHttpRequest()
            request.open('POST', 'http://localhost:9191/contrat', true)
            request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8')


            const contrat = JSON.stringify(values)

            request.send(contrat)*/
        }
    }, [errors]
    )


    return { handleChange, values, handleSubmit, errors }
}

export default useNewContrat