import React from 'react'
import { useState } from 'react/cjs/react.development'
import LoginUserHTML from './LoginUserHTML'
import './LoginUserCss.css'
import { Redirect } from 'react-router'

const LoginUser = () => {
    const [isSubmitted,setIsSubmitted] = useState(false)

    function setSubmitTrue (){
        setIsSubmitted(true)
    }

    return (
        <>
            <div className="form-container">
                <div className="form-content-left">
                    <img src="img/img-2.svg" alt="spaceship" className="form-img"></img>
                </div>
                {!isSubmitted ? (<LoginUserHTML setSubmitTrue={setSubmitTrue} /> ) : (<Redirect to="/"/>)  }
            </div>
            
        </>
    )
}

export default LoginUser
