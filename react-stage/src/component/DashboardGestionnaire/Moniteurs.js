import React, { useContext, useState, useEffect } from "react";
import { UserInfoContext } from "../../contexts/UserInfo";

const Moniteurs = () => {
  const [loggedUser, setLoggedUser] = useContext(UserInfoContext);
  const [moniteurs, setMoniteurs] = useState([]);

  useEffect(() => {
    if (loggedUser.isLoggedIn && loggedUser.role === "GESTIONNAIRE") {
      fetch(`http://localhost:9191/user/moniteurs`)
        .then((res) => {
          return res.json();
        })
        .then((moniteurs) => {
          setMoniteurs(moniteurs);
        });
    }
  }, []);

  const moniteursList = moniteurs.map((moniteur) => (
    <tr key={moniteur.id.toString()}>
      <td>{moniteur.prenom}</td>
      <td>{moniteur.nom}</td>
    </tr>
  ));
  return (
    <>
      <table>
        <tr>
          <th colSpan="2">Moniteurs</th>
        </tr>
        <tr>
          <td>Le nombres de moniteurs inscrient</td>
          <td>{moniteurs.length}</td>
        </tr>
        <tr>
          <th>Prénom</th>
          <th>Nom</th>
        </tr>
        {moniteursList}
      </table>
    </>
  );
};

export default Moniteurs;
