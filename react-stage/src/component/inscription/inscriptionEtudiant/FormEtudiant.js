import React from 'react'
import { useContext, useState } from 'react/cjs/react.development'
import FormEtudiantHTML from './FormEtudiantHTML'
import FormEtudiantSuccess from './FormEtudiantSuccess'
import './FormEtudiantCSS.css'
import { Link } from 'react-router-dom'
import { UserInfoContext } from '../../../contexts/UserInfo'

const FormEtudiant = () => {
    const [isSbubmitted,setIsSubmitted] = useState(false)
    function submitForm(){
        setIsSubmitted(true)
    }

    return (
        <>
            <div className="form-container">
                <div className="form-content-left">
                    <img src="img/img-2.svg" alt="spaceship" className="form-img"></img>
                </div>
                {!isSbubmitted ? (<FormEtudiantHTML submitForm={submitForm} /> ) : (<FormEtudiantSuccess />)  }
            </div>
            
        </>
    )
}

export default FormEtudiant
