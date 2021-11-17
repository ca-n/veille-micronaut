import React, { useEffect, useState, useContext } from 'react'
import OffreService from '../../services/OffreService';

const AllSessionOffre = ({ reloadList, getListForSpecificSession, elementsPerPage }) => {
    const [offres, setOffres] = useState([]);
    const [pageNumber, setPageNumber] = useState(0);
    const [offresVisible, setOffresVisible] = useState([]);

    useEffect(async () => {
        const offres = await OffreService.getAllOffresAllSession()
        setOffres(getListForSpecificSession(offres));
        setOffresVisible(getListForSpecificSession(offres).slice(0, elementsPerPage));
    }, []);

    useEffect(async () => {
        const offres = await OffreService.getAllOffresAllSession()
        setOffres(getListForSpecificSession(offres));
        setOffresVisible(getListForSpecificSession(offres).slice(0, elementsPerPage));
    }, [reloadList]);


    const updateListOffres = (pageNumber) => {
        let offset = elementsPerPage * pageNumber;

        setOffresVisible(offres.slice(0 + offset, elementsPerPage + offset));
    };

    const nextPage = () => {
        if (elementsPerPage * (pageNumber + 1) >= offres.length) return;
        updateListOffres(pageNumber + 1);
        setPageNumber(pageNumber + 1);
    };

    const previousPage = () => {
        if (pageNumber === 0) return;
        updateListOffres(pageNumber - 1);
        setPageNumber(pageNumber - 1);
    };

    const offresList = offresVisible.map((offre) => (
        <tr key={offre.id}>
            <td>{offre.titre} {offre.nom}</td>
            <td>{offre.entreprise}</td>
            <td>{offre.session}</td>
        </tr>
    ));

    return (
        <>
            <table>
                <tr>
                    <th colSpan="2">Offres All Sessions</th>
                </tr>
                <tr>
                    <th>Titre</th>
                    <th>Entreprise</th>
                    <th>Session</th>
                </tr>
                <tbody>{offresList}</tbody>
                <tr>
                    <td className="hoverButton">
                        <button onClick={previousPage} className="button">
                            «
                        </button>
                    </td>
                    <td className="hoverButton">
                        <button onClick={nextPage} className="button">
                            »
                        </button>
                    </td>
                </tr>
            </table>
        </>
    );
}

export default AllSessionOffre
