import React, { useContext, useState, useEffect } from "react";
import { UserInfoContext } from "../../contexts/UserInfo";

const Superviseurs = () => {
  const [loggedUser, setLoggedUser] = useContext(UserInfoContext);
  const [superviseurs, setSuperviseurs] = useState([]);

  useEffect(() => {
    if (loggedUser.isLoggedIn && loggedUser.role === "GESTIONNAIRE") {
      fetch(`http://localhost:9191/user/superviseurs`)
        .then((res) => {
          return res.json();
        })
        .then((superviseurs) => {
          setSuperviseurs(superviseurs);
        });
    }
  }, []);

  const superviseursList = superviseurs.map((superviseur) => (
    <tr key={superviseur.id.toString()}>
      <td>{superviseur.prenom}</td>
      <td>{superviseur.nom}</td>
    </tr>
  ));

  return (
    <>
      <table>
        <tr>
          <th colSpan="2">Superviseurs</th>
        </tr>
        <tr>
          <td>Le nombres de superviseur inscrient</td>
          <td>{superviseurs.length}</td>
        </tr>
        <tr>
          <th>Prénom</th>
          <th>Nom</th>
        </tr>
        {superviseursList}
      </table>
    </>
  );
};

export default Superviseurs;
