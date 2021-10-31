import React from "react";
import Cvs from "./Cvs";
import Etudiants from "./Etudiants";
import Moniteurs from "./Moniteurs";
import Superviseurs from "./Superviseurs";

const Dashboard = () => {
  return (
    <>
      <Cvs />
      <Etudiants />
      <Moniteurs />
      <Superviseurs />
    </>
  );
};

export default Dashboard;
