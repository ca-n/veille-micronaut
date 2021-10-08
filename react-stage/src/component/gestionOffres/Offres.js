import React from 'react'
import {useState, useEffect} from 'react'
import {AiOutlineCheckCircle, AiOutlineCloseCircle} from 'react-icons/ai'

const Offres = () => {
    const [offres, setOffres] = useState([]);

    useEffect(() => {
        const getOffres = async() => {
            const dbOffres = await fetchOffres();
            console.log(JSON.stringify(dbOffres))
            setOffres(dbOffres);
        }
        getOffres();
    }, [])

    const fetchOffres = async() => {
        const res = await fetch('http://localhost:9191/stage/offres');
        const data = await res.json();
        return data;
    }

    const offreList = offres.map((offre) => 
    <tr key={offre.id.toString()}>
        <td colSpan='3' className='green'>{offre.titre}</td>
        <td colSpan='3'>{offre.entreprise}</td>
        <td colSpan='1'>{offre.valid ? <AiOutlineCheckCircle color='green'/> : <AiOutlineCloseCircle color='red'/>}</td>
    </tr>);

    return (
        <div className="container" style={{textAlign: 'center'}}>
            <h1>Offres</h1>
            <table className="table border">
                <thead>
                    <tr>
                        <th colSpan='3'>Titre</th>
                        <th colSpan='3'>Entreprise</th>
                        <th colSpan='1'>Valide</th>
                    </tr>
                </thead>
                <tbody>
                    {offreList}
                </tbody>
            </table>
        </div>
    )
}

export default Offres
