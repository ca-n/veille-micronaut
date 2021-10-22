import React from 'react'
import { useState, useEffect, useContext } from 'react'
import { Link, useHistory } from 'react-router-dom'
import { AiOutlineCheckCircle, AiOutlineCloseCircle, AiOutlineClockCircle } from 'react-icons/ai'
import { UserInfoContext } from '../../contexts/UserInfo';
import CVService from '../../services/CVService'
import './VerificationCV.css'

const VerificationCVList = () => {
    const [cvList, setCVList] = useState([])
    const [loggedUser, setLoggedUser] = useContext(UserInfoContext)
    const history = useHistory()

    useEffect(() => {
        if (!loggedUser.isLoggedIn || loggedUser.role !== "GESTIONNAIRE") history.push("/login")

        const getAllCVs = async () => {
            const cvs = await CVService.getAllCVs()
            setCVList(cvs);
        }
        getAllCVs();
    }, [loggedUser, history])

    const getStatusIcon = (status) => {
        switch(status) {
            case "PENDING":
                return <AiOutlineClockCircle color="gold" size="48px"/>
            case "ACCEPTED":
                return <AiOutlineCheckCircle color="green" size="48px"/>
            case "REJECTED":
                return <AiOutlineCloseCircle color="red" size="48px"/>
            default:
                return;
        }
    }

    return (
        <div className="container">
            <h1 className="center">Liste des CV des étudiants</h1>
            <div className="table">
                <div className="border row container">
                    <div className="col-3 bold auto">Nom d'étudiant</div>
                    <div className="col-3 bold auto">Nom du fichier</div>
                    <div className="col-3 bold auto">Date soumission</div>
                    <div className="col-2 bold right">Statut du CV</div>
                </div>
                {cvList.length === 0 ? <div className="col center">Aucun CV à afficher</div> : cvList.map(cv =>
                    <Link key={cv.id} className="row border container cvitem" to={`/gestion/cv/${cv.id}`}>
                        <div className="col-3 auto">{cv.etudiant.nom}, {cv.etudiant.prenom}</div>
                        <div className="col-3 auto">{cv.nom}</div>
                        <div className="col-3 auto">{cv.dateSoumission}</div>
                        <div className="col-2 right">{getStatusIcon(cv.status)}</div>
                    </Link>)}
            </div>
        </div>
    )
}

export default VerificationCVList
