import {createContext, useState, useEffect} from 'react'

import React from 'react'



const user = {
    courriel: "",
    role: "",
    isLoggedIn: "",
}
const UserInfo = ({children}) =>{
    const [loggedUser, setLoggedUser] = useState(user)

    useEffect(() => {
        console.log(loggedUser, " in use effect of context");
    })
    return (
            <UserInfoContext.Provider value={[loggedUser, setLoggedUser]}>
                {children}
            </UserInfoContext.Provider>
        )
}
export const UserInfoContext = createContext(user);
export default UserInfo;





