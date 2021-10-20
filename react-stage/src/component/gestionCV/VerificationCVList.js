import React from 'react'
import { useState, useEffect } from 'react'
import CVService from '../../services/CVService'

const VerificationCVList = () => {
    const [cvList, setCVList] = useState([])

    useEffect(() => {
        const getAllCVs = async () => {
            const cvs = await CVService.getAllCVs()
            console.log(cvs)
            setCVList(cvs);
        }
        getAllCVs();
    }, [])

    return (
        <div className="container">
            <div className="row">
                <div className="col">Nom d'étudiant</div>
                <div className="col">Nom de fichier</div>
                <div className="col">Statut du CV</div>
            </div>
            <div className="row">
                {cvList.map(cv => 
                <div key={cv.id} className="row border">
                    <div className="col">{cv.etudiant.nom}, {cv.etudiant.prenom}</div>
                    <div className="col">{cv.nom}</div>
                    <div className="col">{cv.status}</div>
                </div>)}
            </div>
        </div>
    )
}

export default VerificationCVList
