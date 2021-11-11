import React from 'react'
import { useState } from 'react/cjs/react.development'
import NewContratForm from './NewContratForm'
import NewContratSuccess from './NewContratSuccess'
import './NewContratCSS.css'

const NewContrat = () => {
    const [isSubmitted,setIsSubmitted] = useState(false)

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
                {!isSubmitted ? (<NewContratForm submitForm={submitForm} /> ) : (<NewContratSuccess />)  }
            </div>
            
        </>
    )
}

export default NewContrat
