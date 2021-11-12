import React from 'react'
import { Link } from 'react-router-dom'

const NewContratSuccess = () => {
    return (
        <div className="form-content-right">
            <div className="form-success">Retour à l'accueil  <Link to="/dashboard">ici</Link></div>
            <img src="./img/img-3.svg" alt="success-image" className="form-img-2"></img>
        </div>
    )
}

export default NewContratSuccess
