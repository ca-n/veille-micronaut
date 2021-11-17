import React, { useEffect, useState } from 'react'
import UserService from '../../services/UserService';

const AllSessionEtudiant = ({ reloadList, getListForSpecificSession, elementsPerPage }) => {
  const [etudiants, setEtudiants] = useState([]);
  const [pageNumber, setPageNumber] = useState(0);
  const [etudiantsVisible, setEtudiantsVisible] = useState([]);

  useEffect(async () => {
    const etudiants = await UserService.getListAllEtudiantsAllSession()
    setEtudiants(getListForSpecificSession(etudiants));
    setEtudiantsVisible(getListForSpecificSession(etudiants).slice(0, elementsPerPage));
  }, []);

  useEffect(async () => {
    const etudiants = await UserService.getListAllEtudiantsAllSession()
    setEtudiants(getListForSpecificSession(etudiants));
    setEtudiantsVisible(getListForSpecificSession(etudiants).slice(0, elementsPerPage));

  }, [reloadList]);



  const updateListeEtudiants = (pageNumber) => {
    let offset = elementsPerPage * pageNumber;

    setEtudiantsVisible(etudiants.slice(0 + offset, elementsPerPage + offset));
  };

  const nextPage = () => {
    if (elementsPerPage * (pageNumber + 1) >= etudiants.length) return;
    updateListeEtudiants(pageNumber + 1);
    setPageNumber(pageNumber + 1);
  };

  const previousPage = () => {
    if (pageNumber === 0) return;
    updateListeEtudiants(pageNumber - 1);
    setPageNumber(pageNumber - 1);
  };

  const etudiantsList = etudiantsVisible.map((etudiant) => (
    <tr key={etudiant.id}>
      <td>{etudiant.prenom} {etudiant.nom}</td>
      <td>{etudiant.courriel}</td>
      <td>{etudiant.session}</td>
    </tr>
  ));

  return (
    <>
      <table>
        <tr>
          <th colSpan="2">Étudiants All Sessions</th>
        </tr>
        <tr>
          <th>Prénom/Nom</th>
          <th>Courriel</th>
          <th>Session</th>
        </tr>
        <tbody>{etudiantsList}</tbody>
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

export default AllSessionEtudiant
