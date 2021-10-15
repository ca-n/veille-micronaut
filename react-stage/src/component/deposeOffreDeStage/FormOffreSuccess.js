import React from 'react'
import { Link } from 'react-router-dom'

const FormOffreSuccess = () => {
    return (
        <div className="form-content-right">
            <div className="form-success">Vous pouvez maintenant voir votre offre  <Link to="/offres">ici</Link></div>
            <img src="./img/img-3.svg" alt="success-image" className="form-img-2"></img>
        </div>
    )
}

export default FormOffreSuccess
