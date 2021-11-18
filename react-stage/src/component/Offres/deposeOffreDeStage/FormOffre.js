import React from 'react'
import { useState } from 'react/cjs/react.development'
import './FormOffreCSS.css'
import FormOffreHTML from "./FormOffreHTML";
import FormOffreSuccess from "./FormOffreSuccess";

const FormOffre = () => {
    const [isSubmitted, setIsSubmitted] = useState(false)

    function submitForm() {
        setIsSubmitted(true)
    }

    return (
        <>
            <div className="form-container">
                <span className="close-btn">x</span>
                <div className="form-content-left">
                    <img src="img/img-2.svg" alt="spaceship" className="form-img"></img>
                </div>
                {!isSubmitted ? (<FormOffreHTML submitForm={submitForm} />) : (<FormOffreSuccess />)}
            </div>

        </>
    )
}

export default FormOffre
