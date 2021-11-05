import React, { useContext, useEffect, useState } from 'react'
import { AiOutlineCheckCircle, AiOutlineCloseCircle } from 'react-icons/ai'
import { useHistory } from 'react-router'
import { Link } from 'react-router-dom'
import { UserInfoContext } from '../../../contexts/UserInfo'
import OffreService from '../../../services/OffreService'
import './MoniteurOffres.css'

const MoniteurOffres = () => {
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const history = useHistory()
    const [offres, setOffres] = useState([])

    useEffect(() => {
        if (!loggedUser.isLoggedIn || loggedUser.role !== "MONITEUR") history.push("/login")

        const fetchOffres = async () => {
            const dbOffres = await OffreService.getMoniteurOffres(loggedUser.courriel)
            setOffres(dbOffres)
            console.log(dbOffres)
        }
        fetchOffres()
    }, [loggedUser, history])

    const getStatusIcon = (isValid) => {
        return isValid ? 
        <AiOutlineCheckCircle color="green" size="48px" /> :
        <AiOutlineCloseCircle color="red" size="48px" />
    }

    return (
        <div className="container">
            <h1 className="center">Liste de vos offres</h1>
            <div className="table">
                <div className="border row container">
                    <div className="col-3 bold auto">Titre</div>
                    <div className="col-3 bold auto">Entreprise</div>
                    <div className="col-2 bold right">Valide</div>
                </div>
                {offres.length === 0 ? <div className="col center">Aucun offres à afficher</div> : 
                offres.map(offre => 
                    <Link key={offre.id} className="row border container offre" to={`/moniteur/offres/${offre.id}`}>
                        <div className="col-3 auto">{offre.titre}</div>
                        <div className="col-3 auto">{offre.entreprise}</div>
                        <div className="col-2 right">{getStatusIcon(offre.isValid)}</div>
                    </Link>
                )}
            </div>
        </div>
    )
}

export default MoniteurOffres
