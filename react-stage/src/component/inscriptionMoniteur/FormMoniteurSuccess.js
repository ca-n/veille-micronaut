import React from 'react'
import { Link } from 'react-router-dom'

const FormMoniteurSuccess = () => {
    return (
        <div className="form-content-right">
             <div className="form-success">Vous pouvez maintenant vous connecter  <Link to="/login">ici</Link></div>
            <img src="./img/img-3.svg" alt="success-image" className="form-img-2"></img>
        </div>
    )
}

export default FormMoniteurSuccess
