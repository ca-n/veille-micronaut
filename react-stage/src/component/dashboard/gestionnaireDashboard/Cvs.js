import React, { useContext, useState, useEffect } from "react";
import { UserInfoContext } from "../../../contexts/UserInfo";

const Cvs = () => {
  const [loggedUser, setLoggedUser] = useContext(UserInfoContext);

  const [listPendingCV, setListPendingCV] = useState("0");
  const [listAcceptedCV, setListAcceptedCV] = useState("0");
  const [listRejectedCV, setListRejectedCV] = useState("0");
  const [cvs, setCVs] = useState([]);

  useEffect(() => {
    if (loggedUser.isLoggedIn && loggedUser.role === "GESTIONNAIRE") {
      fetch(`http://localhost:9191/cv`)
        .then((res) => {
          return res.json();
        })
        .then((cvs) => {
          console.log(cvs, "cvs");
          setCVs(cvs);
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

  return (
    <>
      <table>
        <tr>
          <th colSpan="2">CV</th>
        </tr>
        <tr>
          <td>Le nombres de cvs totals</td>
          <td>{cvs.length}</td>
        </tr>
        <tr>
          <th>Le status du cv</th>
          <th>Le nombre de cv relié à ce statut</th>
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
        <tr></tr>
      </table>
    </>
  );
};

export default Cvs;
