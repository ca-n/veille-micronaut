import React, { useContext, useState, useEffect } from "react";
import { UserInfoContext } from "../../contexts/UserInfo";

const Etudiants = () => {
  const [loggedUser, setLoggedUser] = useContext(UserInfoContext);
  const [etudiants, setEtudiants] = useState([]);

  useEffect(() => {
    if (loggedUser.isLoggedIn && loggedUser.role === "GESTIONNAIRE") {
      fetch(`http://localhost:9191/user/etudiants`)
        .then((res) => {
          return res.json();
        })
        .then((etudiants) => {
          setEtudiants(etudiants);
        });
    }
  }, []);

  const etudiantsList = etudiants.map((etudiant) => (
    <tr key={etudiant.id.toString()}>
      <td>{etudiant.prenom}</td>
      <td>{etudiant.nom}</td>
    </tr>
  ));

  return (
    <>
      <table>
        <tr>
          <th colSpan="2">Étudiants</th>
        </tr>
        <tr>
          <td>Le nombre d'étudiants inscrient</td>
          <td>{etudiants.length}</td>
        </tr>
        <tr>
          <th>Prénom</th>
          <th>Nom</th>
        </tr>

        {etudiantsList}
      </table>
    </>
  );
};

export default Etudiants;
