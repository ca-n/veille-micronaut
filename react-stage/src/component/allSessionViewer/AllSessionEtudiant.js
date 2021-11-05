import React, {useEffect, useState, useContext} from 'react'
import { UserInfoContext } from '../../contexts/UserInfo';

const AllSessionEtudiant = () => {
const [loggedUser, setLoggedUser] = useContext(UserInfoContext);
  const [etudiants, setEtudiants] = useState([]);
  const [pageNumber, setPageNumber] = useState(0);
  const [etudiantsVisible, setEtudiantsVisible] = useState([]);
  const etudiantsPerPage = 10;

  useEffect(() => {
    if ( loggedUser.role !== "ETUDIANT") {
      fetch(`http://localhost:9191/user/etudiants/allSession`)
        .then((res) => {
          return res.json();
        })
        .then((etudiants) => {
          setEtudiants(etudiants);
          setEtudiantsVisible(etudiants.slice(0, etudiantsPerPage));
        });
    }
  }, []);

  const updateListeEtudiants = (pageNumber) => {
    let offset = etudiantsPerPage * pageNumber;

    setEtudiantsVisible(etudiants.slice(0 + offset, etudiantsPerPage + offset));
  };

  const nextPage = () => {
    if (etudiantsPerPage * (pageNumber + 1) >= etudiants.length) return;
    updateListeEtudiants(pageNumber + 1);
    setPageNumber(pageNumber + 1);
  };

  const previousPage = () => {
    if (pageNumber === 0) return;
    updateListeEtudiants(pageNumber - 1);
    setPageNumber(pageNumber - 1);
  };

  const etudiantsList = etudiantsVisible.map((etudiant) => (
    <tr key={etudiant.id.toString()}>
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
