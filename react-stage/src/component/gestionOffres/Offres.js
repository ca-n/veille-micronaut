
import React from 'react'
import { useState, useEffect } from 'react'
import { AiOutlineCheckCircle, AiOutlineCloseCircle, AiOutlineClose } from 'react-icons/ai'
import ReactModal from 'react-modal';


const Offres = () => {
    const [offres, setOffres] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [currentOffre, setCurrentOffre] = useState({
        titre: String,
        description: String,
        entreprise: String,
        visibiliteEtudiant: Array,
        valid: Boolean
    });

    useEffect(() => {
        const getOffres = async () => {
            const dbOffres = await fetchOffres();
            console.log(JSON.stringify(dbOffres))
            setOffres(dbOffres);
        }
        getOffres();
    }, [])

    const fetchOffres = async () => {
        const res = await fetch('http://localhost:9191/stage/offres');
        const data = await res.json();
        return data;
    }

    const onClickOffre = (offre) => {
        setCurrentOffre(offre);
        setShowModal(true);
    }

    const onClickClose = () => {
        setCurrentOffre({});
        setShowModal(false);
    }

    const offreList = offres.map((offre) =>
        <tr key={offre.id.toString()}>
            <td colSpan='3' className='green'>{offre.titre}</td>
            <td colSpan='3'>{offre.entreprise}</td>
            <td colSpan='1'>{offre.valid ? <AiOutlineCheckCircle color='green' /> : <AiOutlineCloseCircle color='red' />}</td>
            <td colSpan='1'><input type='button' onClick={() => onClickOffre(offre)} value='Détails' className='p-1 btn-secondary' /></td>
        </tr>);

    const onToggleValid = () => {
        setCurrentOffre((offre) => ({
            ...offre,
            valid: !offre.valid
        }));
    }

    useEffect(() => {
        console.log(currentOffre)
    }, [currentOffre])

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
            <ReactModal isOpen={showModal}>

                <div className="container">
                    <AiOutlineClose color='red' onClick={onClickClose} />
                    <div className="row">
                        <h3 className="col-3">Titre</h3>
                        <h3 className="col-3">Description</h3>
                        <h3 className="col-3">Entreprise</h3>
                        <h3 className="col-3">Validity</h3>
                    </div>

                    <div className="row mt-4">
                        <div className="col-3">{currentOffre.titre}</div>
                        <div className="col-3">{currentOffre.description}</div>
                        <div className="col-3">{currentOffre.entreprise}</div>
                        <div className="col-3 form-check">
                            <input type='checkbox' name='valid' className="form-check-input"  checked={currentOffre.valid} onClick={onToggleValid} />
                            <label class="form-check-label" for="valid"> Valid </label>
                        </div>
                        
                    </div>
                </div>

            </ReactModal>
        </div>
    )
}

export default Offres
