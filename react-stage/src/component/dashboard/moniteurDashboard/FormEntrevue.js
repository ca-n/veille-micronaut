import { React, useEffect, useState, useRef } from 'react'
import EntrevueService from '../../../services/EntrevueService'

const FormEntrevue = ({ handleReloadList }) => {
    const [values, setValues] = useState({
        titre: "",
        date: "",
        time: "",
        etudiantId: "",
        moniteurId: ""
    })

    const reload = useRef(0)

    const handleChange = e => {
        const { name, value } = e.target
        setValues({
            ...values,
            [name]: value,
        })
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        await fetchAndPost()
        handleReloadList()
    }

    const fetchAndPost = async () => {
        const moniteurEtudiant = await fetchMoniteurEtEtudiant()
        postEntrevue(moniteurEtudiant.pop(), moniteurEtudiant.pop())
    }

    const fetchMoniteurEtEtudiant = async () => {
        const moniteurEtudiant = []
        await fetch(`http://localhost:9191/user/etudiant/${values.etudiantId}`)
            .then(response => response.json())
            .then(data => moniteurEtudiant.push(data));
        await fetch(`http://localhost:9191/user/moniteur/${values.moniteurId}`)
            .then(response => response.json())
            .then(data => moniteurEtudiant.push(data));
        return moniteurEtudiant;

    }

    const postEntrevue = async (moniteur, etudiant) => {
        const entrevue = {
            titre: values.titre,
            date: values.date,
            time: values.time,
            etudiant: etudiant,
            moniteur: moniteur
        }
        console.log(entrevue)
        EntrevueService.addEntrevue(entrevue)
    }



    return (
        <div>
            <form className="register-form" onSubmit={handleSubmit}>
                <input
                    value={values.titre}
                    onChange={handleChange}
                    id="titre"
                    className="form-field"
                    type="text"
                    placeholder="titre"
                    name="titre" />
                <input
                    value={values.date}
                    onChange={handleChange}
                    id="date"
                    className="form-field"
                    type="date"
                    name="date" />
                <input
                    value={values.time}
                    onChange={handleChange}
                    id="time"
                    className="form-field"
                    type="time"
                    name="time" />
                <input
                    value={values.etudiantId}
                    onChange={handleChange}
                    id="etudiantId"
                    className="form-field"
                    type="text"
                    placeholder="etudiantId"
                    name="etudiantId" />
                <input
                    value={values.moniteurId}
                    onChange={handleChange}
                    id="moniteurId"
                    className="form-field"
                    type="text"
                    placeholder="moniteurId"
                    name="moniteurId" />
                <button class="form-field" type="submit">
                    Créer
                </button>

            </form>

        </div>
    )
}

export default FormEntrevue

