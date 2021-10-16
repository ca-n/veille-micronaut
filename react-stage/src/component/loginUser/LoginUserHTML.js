import React from 'react'
import useLoginUser from './useLoginUser'
import validateInfoLogin from './validateInfoLogin';
import { useState, useEffect, useContext, Redirect } from "react";
import { UserInfoContext } from "../../contexts/UserInfo";

import './LoginUser';
import { Link } from 'react-router-dom'



const LoginUserHTML = ({ setSubmitTrue }) => {
    // const {handleChange,values, handleSubmit, errors} = useLoginUser(submitForm,validateInfoLogin);

    const [values, setValues] = useState({
        courriel: "",
        password: ""
    })
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const [errors, setErrors] = useState({})
    const [isSubmitted, setIsSubmitted] = useState(false)


    const handleChange = e => {
        const { name, value } = e.target
        setValues({
            ...values,
            [name]: value,
        })
    }

    const handleSubmit = e => {
        e.preventDefault();

        setErrors(validateInfoLogin(values))

        setIsSubmitted(true)
    }


    useEffect(() => {
        if (Object.keys(errors).length === 0 && isSubmitted) {
            // callback();

            fetch(`http://localhost:9191/user/${values.courriel}/${values.password}`)
                .then(res => {
                    console.log(res)
                    return res.json();
                })
                .then(data => {
                    console.log(data)


                    setLoggedUser({
                        courriel: data.courriel,
                        role: data.role,
                        isLoggedIn: true
                    })
                    console.log(loggedUser, "right after the setter")
                    console.log("Logged user in context")
                    setSubmitTrue()
                })

        }
    }, [errors]
    );

    useEffect(() => {
        if (loggedUser.isLoggedIn) {
            console.log(loggedUser)
        }
        console.log("aaa")

    }, [loggedUser])
    return (
        <div className="form-content-right">

            <form className="form" onSubmit={handleSubmit}>
                <h1>Vous pouvez vous connecter ici ↓</h1>
                <div className="form-inputs">
                    <label htmlFor="courriel"
                        className="form-label">
                        Courriel
                    </label>
                    <input id="courriel" type="email" name="courriel" className="form-input" placeholder="Entrez votre courriel" value={values.email} onChange={handleChange}></input>
                    {errors.courriel && <p>{errors.courriel}</p>}
                </div>

                <div className="form-inputs">
                    <label htmlFor="password"
                        className="form-label">
                        Mot de passe
                    </label>
                    <input id="password" type="password" name="password" className="form-input" placeholder="Entrez votre mot de passe" value={values.password} onChange={handleChange}></input>
                    {errors.password && <p>{errors.password}</p>}
                </div>


                <button className="form-input-btn" type="submit">Login</button>
            </form>
        </div>
    )
}

export default LoginUserHTML

