import React, { useContext, useState, useEffect } from "react";
import { UserInfoContext } from "../contexts/UserInfo";

const DashboardGestionnaire = () => {
  const [listPendingCV, setListPendingCV] = useState("0");
  const [listAcceptedCV, setListAcceptedCV] = useState("0");
  const [listRejectedCV, setListRejectedCV] = useState("0");
  const [etudiants, setEtudiants] = useState([]);
  const [moniteurs, setMoniteurs] = useState([]);
  const [superviseurs, setSuperviseurs] = useState([]);
  const [loggedUser, setLoggedUser] = useContext(UserInfoContext);
  const [cvs, setCVs] = useState();

  useEffect(() => {
    if (loggedUser.isLoggedIn && loggedUser.role === "GESTIONNAIRE") {
      fetch(`http://localhost:9191/stage/cv`)
        .then((res) => {
          return res.json();
        })
        .then((cvs) => {
          console.log(cvs, "cvs");
          setCVs(cvs);
        });

      fetch(`http://localhost:9191/stage/etudiants`)
        .then((res) => {
          return res.json();
        })
        .then((etudiants) => {
          setEtudiants(etudiants);
        });

      fetch(`http://localhost:9191/stage/moniteurs`)
        .then((res) => {
          return res.json();
        })
        .then((moniteurs) => {
          setMoniteurs(moniteurs);
        });

      fetch(`http://localhost:9191/stage/superviseurs`)
        .then((res) => {
          return res.json();
        })
        .then((superviseurs) => {
          setSuperviseurs(superviseurs);
        });
    }
  }, []);

  useEffect(async () => {
    if (cvs !== undefined) {
      console.log(cvs, "test cvs");
      listCV(cvs);
    }
  }, [cvs]);

  const listCV = (listCV) => {
    let pendingCVS = [];
    let acceptedCVS = [];
    let rejectedCVS = [];
    listCV.map((cv) => {
      if (cv.status === "PENDING") {
        pendingCVS.push(cv);
      } else if (cv.status === "REJECTED") {
        rejectedCVS.push(cv);
      } else if (cv.status === "ACCEPTED") {
        acceptedCVS.push(cv);
      }
    });
    setListAcceptedCV(acceptedCVS.length);
    setListPendingCV(pendingCVS.length);
    setListRejectedCV(rejectedCVS.length);
  };

  const etudiantsList = etudiants.map((etudiant) => (
    <tr key={etudiant.id.toString()}>
      <td>{etudiant.prenom}</td>
      <td>{etudiant.nom}</td>
    </tr>
  ));

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
          <th colSpan="2">CV</th>
        </tr>
        <tr>
          <td>Le status du cv</td>
          <td>Le nombre de cv relié à ce statut</td>
        </tr>
        <tr>
          <td>Le nombre de cv accepté</td>
          <td>{listAcceptedCV}</td>
        </tr>
        <tr>
          <td>Le nombre de cv refusé</td>
          <td>{listRejectedCV}</td>
        </tr>
        <tr>
          <td>Le nombre de cv en attente</td>
          <td>{listPendingCV}</td>
        </tr>
        <tr>
          <th colSpan="2">Étudiants</th>
        </tr>
        <tr>
          <td>Prénom</td>
          <td>Nom</td>
        </tr>
        <tr>
          <td>Le nombre d'étudiants inscrient</td>
          <td>{etudiants.length}</td>
        </tr>
        {etudiantsList}
        <tr>
          <th colSpan="2">superviseurs</th>
        </tr>
        <tr>
          <td>Le nombre de supervieurs inscrient</td>
          <td>{superviseurs.length}</td>
        </tr>
        {superviseursList}
      </table>
    </>
  );
};

export default DashboardGestionnaire;
