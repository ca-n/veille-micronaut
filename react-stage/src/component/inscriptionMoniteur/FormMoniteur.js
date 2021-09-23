import React from 'react'
import { useState } from 'react/cjs/react.development'
import FormMoniteurHTML from './FormMoniteurHTML'
import FormMoniteurSuccess from './FormMoniteurSuccess'
import './FormMoniteurCSS.css'

const FormMoniteur = () => {
    const [isSubmitted,setIsSubmitted] = useState(false)

    function submitForm(){
        setIsSubmitted(true)
    }

    return (
        <>
            <div className="form-container">
                <div className="form-content-left">
                    <img src="img/img-2.svg" alt="spaceship" className="form-img"></img>
                </div>
                {!isSubmitted ? (<FormMoniteurHTML submitForm={submitForm} /> ) : (<FormMoniteurSuccess />)  }
            </div>
            
        </>
    )
}

export default FormMoniteur
