import React from 'react'
import { useState } from 'react/cjs/react.development'
import FormSuperviseurHTML from './FormSuperviseurHTML'
import FormSuperviseurSuccess from './FormSuperviseurSuccess'
import './FormSuperviseurCSS.css'

const FormSuperviseur = () => {
    const [isSbubmitted,setIsSubmitted] = useState(false)

    function submitForm(){
        setIsSubmitted(true)
    }

    return (
        <>
            <div className="form-container">
                <span className="close-btn">x</span>
                <div className="form-content-left">
                    <img src="img/img-2.svg" alt="spaceship" className="form-img"></img>
                </div>
                {!isSbubmitted ? (<FormSuperviseurHTML submitForm={submitForm} /> ) : (<FormSuperviseurSuccess />)  }
            </div>
            
        </>
    )
}

export default FormSuperviseur
