import React, { useContext, useState, useEffect } from "react";
import { UserInfoContext } from "../../contexts/UserInfo";

const Offres = () => {
  const [loggedUser, setLoggedUser] = useContext(UserInfoContext);
  const [offres, setOffres] = useState([]);

  useEffect(() => {
    if (loggedUser.isLoggedIn && loggedUser.role === "GESTIONNAIRE") {
      fetch(`http://localhost:9191/offres`)
        .then((res) => {
          return res.json();
        })
        .then((offres) => {
          setOffres(offres);
        });
    }
  }, []);

  const offresList = offres.map((offre) => (
    <tr key={offre.id.toString()}>
      <td>{offre.titre}</td>
      <td>{offre.description}</td>
    </tr>
  ));

  return (
    <>
      <table>
        <tr>
          <th colSpan="2">Offres</th>
        </tr>
        <tr>
          <td>Le nombre d'offres totales</td>
          <td>{offres.length}</td>
        </tr>
        <tr>
          <th>Titre</th>
          <th>Description</th>
        </tr>

        {offresList}
      </table>
    </>
  );
};

export default Offres;
