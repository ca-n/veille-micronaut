import React from "react";
import Cvs from "./Cvs";
import Etudiants from "./Etudiants";
import Moniteurs from "./Moniteurs";
import Offres from "./Offres";
import Superviseurs from "./Superviseurs";

const Dashboard = () => {
  return (
    <>
      <Cvs />
      <Etudiants />
      <Moniteurs />
      <Superviseurs />
      <Offres />
    </>
  );
};

export default Dashboard;
