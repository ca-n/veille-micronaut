import React from 'react'
import { useState, useEffect } from 'react'
import { AiOutlineCheckCircle, AiOutlineCloseCircle, AiOutlineClose } from 'react-icons/ai'
import ReactModal from 'react-modal';
import './PickList.css';


const Offres = () => {
    const [offres, setOffres] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [currentOffre, setCurrentOffre] = useState({
        titre: String,
        description: String,
        entreprise: String,
        visibiliteEtudiant: Array,
        valid: Boolean,
        adresse: String,
        dateDebut: String,
        dateFin: String,
        nbTotalSemaine: 0,
        horaire: String,
        nbTotalHeuresParSemaine: Number,
        tauxHoraire: Number
    });
    const [listAllEtudiant, setListAllEtudiant] = useState([])

    const [listWhitelisted, setListWhitelisted] = useState([]);


    useEffect(() => {
        const getOffres = async () => {
            const dbOffres = await fetchOffres()
            console.log(JSON.stringify(dbOffres))
            setOffres(dbOffres)
        }
        getOffres();
    }, [])

    useEffect(() => {
        const getListAllEtudiants = async () => {
            const allEtudiants = await fetchAllEtudiants()
            console.log(JSON.stringify(allEtudiants))
            setListAllEtudiant(allEtudiants)
        }
        getListAllEtudiants()
    }, [])

    const fetchOffres = async () => {
        const res = await fetch('http://localhost:9191/stage/offres');
        const data = await res.json()
        return data
    }

    const fetchAllEtudiants = async () => {
        const res = await fetch('http://localhost:9191/stage/etudiants')
        const data = await res.json()
        console.log(data)
        return data
    }

    const onClickOffre = (offre) => {
        setCurrentOffre(offre)
        setListWhitelisted(offre.visibiliteEtudiant.whitelistedEtudiant)
        setShowModal(true)
    }

    const onClickClose = () => {
        setCurrentOffre({})
        setShowModal(false)
    }

    const onToggleValid = () => {
        setCurrentOffre((offre) => ({
            ...offre,
            valid: !offre.valid
        }))
    }

    useEffect(() => {
        console.log(currentOffre)
    }, [currentOffre])

    const onClickSave = () => {
        saveOffre(currentOffre);
        onClickClose();
    }

    const saveOffre = async (offre) => {
        const res = await fetch('http://localhost:9191/stage/offre',
            {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json',
                },
                body: JSON.stringify(offre)
            });
        await res.json();
        updateOffres();
    }

    const updateOffres = async () => {
        const dbOffres = await fetchOffres();
        setOffres(dbOffres)
    }

    const offreList = offres.map((offre) =>
        <tr key={offre.id.toString()}>
            <td colSpan='3'>{offre.titre}</td>
            <td colSpan='3'>{offre.entreprise}</td>
            <td colSpan='1'>{offre.valid ? <AiOutlineCheckCircle color='green' /> : <AiOutlineCloseCircle color='red' />}</td>
            <td colSpan='1'><input type='button' onClick={() => onClickOffre(offre)} value='Détails' className='p-1 btn-secondary' /></td>
        </tr>);

    const etudiantList = listAllEtudiant.map((etudiant) =>
        <li><input type="checkbox" checked={false} />{etudiant.nom} {etudiant.prenom}</li>
    )


    return (
        <div className="container" style={{ textAlign: 'center' }}>
            <h1>Offres</h1>
            <table className="table border">
                <thead>
                    <tr>
                        <th colSpan='3'>Titre</th>
                        <th colSpan='3'>Entreprise</th>
                        <th colSpan='1'>Valide</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {offreList}
                </tbody>
            </table>
            <ReactModal isOpen={showModal} ariaHideApp={false}>
                <div className="container row">
                    <AiOutlineClose color='red' size='24px' onClick={onClickClose} />
                    <div className="col">
                        <h3 className="row-2">Titre</h3>
                        <h3 className="row-6">Description</h3>
                        <h3 className="row-2">Entreprise</h3>
                        <h3 className="row-2">Validity</h3>
                        <h3 className="row-2">Adresse</h3>
                        <h3 className="row-2">Date début</h3>
                        <h3 className="row-2">Date fin</h3>
                        <h3 className="row-2">Nombre total de semaine</h3>
                        <h3 className="row-2">Horaire</h3>
                        <h3 className="row-2">Nombre total d'heures par semaine</h3>
                        <h3 className="row-2">Taux horaire</h3>
                    </div>

                    <div className="col">
                        <div className="row-2"><h3>{currentOffre.titre}</h3></div>
                        <div className="row-6"><h3>{currentOffre.description}</h3></div>
                        <div className="row-2"><h3>{currentOffre.entreprise}</h3></div>
                        <div className="row-2 form-check">
                            <input type='checkbox' name='valid' className="form-check-input" checked={currentOffre.valid} onChange={onToggleValid} />
                            <label className="form-check-label" htmlFor="valid"> <h3>Valid</h3> </label>
                        </div>
                        <div className="row-2"><h3>{currentOffre.adresse}</h3></div>
                        <div className="row-2"><h3>{currentOffre.dateDebut}</h3></div>
                        <div className="row-2"><h3>{currentOffre.dateFin}</h3></div>
                        <div className="row-2"><h3>{currentOffre.nbTotalSemaine}</h3></div>
                        <div className="row-2"><h3>{currentOffre.horaire}</h3></div>
                        <div className="row-2"><h3>{currentOffre.nbTotalHeuresParSemaine}</h3></div>
                        <div className="row-2"><h3>{currentOffre.tauxHoraire}</h3></div>

                    </div>
                    {/*<div className="row card text-center">
                        <ul>
                            {etudiantList}
                        </ul>
                    </div>*/}
                    <div className="row text-center">
                        <input type='button' value='Save' onClick={onClickSave}></input>
                    </div>

                </div>
            </ReactModal>
        </div>
    )
}

export default Offres
