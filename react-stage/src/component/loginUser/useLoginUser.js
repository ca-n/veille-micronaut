import { useState, useEffect, useContext } from "react";
import validateInfoLogin from "./validateInfoLogin";
import axios from 'axios';
import { UserInfoContext } from "../../contexts/UserInfo";


const useLoginUser = (callback,validateInfoLogin) => {

    const [values,setValues] = useState({
        courriel: "",
        password: ""
    })
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext);
    const [errors,setErrors] = useState({});
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleChange = e => {
        const {name, value} = e.target
        setValues({
            ...values,
            [name]: value,
        })
    }

    const handleSubmit = e =>{
        e.preventDefault();

        setErrors(validateInfoLogin(values))
        setIsSubmitting(true)

       
    }


    useEffect(() => {
        if(Object.keys(errors).length === 0 && isSubmitting) {
            // callback();

            fetch(`http://localhost:9191/user/${values.courriel}/${values.password}`)
            .then(res => {
                return res.json();
            })
            .then(data => {

                console.log(data)
            
                setLoggedUser({type : "", payload : {
                    courriel : data.courriel,
                    role : data.role,
                    isLoggedIn : true
                }})
                console.log(loggedUser)
                console.log("Logged user in context")
            })

        }
    }, [errors]
    );

    useEffect(() => {
        if(loggedUser.isLoggedIn){
           console.log(loggedUser) 
        }
        console.log()
        
    },[loggedUser])

    return {handleChange, values, handleSubmit, errors}
};

export default useLoginUser;
