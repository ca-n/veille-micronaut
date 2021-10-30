import React, { useContext, useState, useEffect } from "react";
import { Table } from "react-bootstrap";
import { UserInfoContext } from "../contexts/UserInfo";

const DashboardGestionnaire = () => {
  const [listPendingCV, setListPendingCV] = useState("0");
  const [listAcceptedCV, setListAcceptedCV] = useState("0");
  const [listRejectedCV, setListRejectedCV] = useState("0");
  const [nombreEtudiants, setnombreEtudiants] = useState("0");
  const [etudiants, setEtudiants] = useState([]);
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
          console.log(etudiants, "etudiants");
          setnombreEtudiants(etudiants.length);
          setEtudiants(etudiants);
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
          <td>{nombreEtudiants}</td>
        </tr>
        {etudiantsList}
      </table>
    </>
  );
};

export default DashboardGestionnaire;
