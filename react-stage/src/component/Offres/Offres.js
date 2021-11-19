import React from "react";
import { useState, useEffect, useContext, useRef } from "react";
import { useHistory } from "react-router-dom";
import {
  AiOutlineCheckCircle,
  AiOutlineCloseCircle,
  AiOutlineClose,
} from "react-icons/ai";
import { UserInfoContext } from "../../contexts/UserInfo";
import ReactModal from "react-modal";
import "./PickList.css";
import OffreService from "../../services/OffreService.js";
import UserService from "../../services/UserService.js";
import { MultiSelect } from "react-multi-select-component";

const Offres = () => {
  const history = useHistory();
  const [listOffres, setListOffres] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [currentOffre, setCurrentOffre] = useState({
    titre: String,
    description: String,
    entreprise: String,
    adresse: String,
    dateDebut: String,
    dateFin: String,
    nbTotalSemaine: Number,
    horaire: String,
    nbTotalHeuresParSemaine: Number,
    tauxHoraire: Number,
    whitelist: Array,
    applicants: Array,
    valid: Boolean,
  });

  const [listAllEtudiant, setListAllEtudiant] = useState([]);

  const [listWhitelistedEtudiant, setListWhitelistedEtudiant] = useState([]);

  const [loggedUser, setLoggedUser] = useContext(UserInfoContext);

  useEffect(() => {
    if (
      !loggedUser.isLoggedIn ||
      !(
        loggedUser.role === "GESTIONNAIRE" ||
        loggedUser.role === "ETUDIANT" ||
        loggedUser.role === "MONITEUR"
      )
    )
      history.push("/login");
    const getOffres = async () => {
      let dbOffres;
      switch (loggedUser.role) {
        case "GESTIONNAIRE":
          dbOffres = await OffreService.getAllOffres();
          break;
        case "MONITEUR":
          dbOffres = await OffreService.getMoniteurOffres(loggedUser.courriel);
          break;
        case "ETUDIANT":
          dbOffres = await OffreService.getEtudiantOffres(loggedUser.courriel);
          break;
        default:
          break;
      }
      console.log(dbOffres, "dbOffres");
      setListOffres(dbOffres);
    };
    getOffres();
  }, []);

  useEffect(() => {
    const getListAllEtudiants = async () => {
      const allEtudiants = await UserService.getListAllEtudiants();
      setListAllEtudiant(allEtudiants);
    };
    getListAllEtudiants();
  }, []);

  useEffect(() => {
    console.log("-----UseEffect Logging--------");
    console.log(listAllEtudiant, "list all etudiant --- listAllEtudiant");
    console.log(
      listWhitelistedEtudiant,
      "list whitelisted etudiant --- listWhitelistedEtudiant "
    );
    console.log(listOffres, "list all offres  ------------ listOffres");
    // console.log(getOptionsEtudiant(listAllEtudiant), "filtered list etudiant ===================================")
  }, [listAllEtudiant, listWhitelistedEtudiant, listOffres]);

  const getOptionsEtudiant = (listEtudiant) => {
    return listEtudiant.map((etudiant) => {
      let etudiantOption = {};
      etudiantOption.label = etudiant.prenom + " " + etudiant.nom;
      etudiantOption.value = etudiant;
      return etudiantOption;
    });
  };

  const getListEtudiantFromOptions = (listWhitelistOptions) => {
    return listWhitelistOptions.map((option) => {
      let etudiant = {};
      etudiant = option.value;
      return etudiant;
    });
  };

  const onClickOffre = (offre) => {
    console.log(offre, "ON click details offre");
    setCurrentOffre(offre);
    setListWhitelistedEtudiant(getOptionsEtudiant(offre.whitelist));
    setShowModal(true);
  };

  const appliquerOffre = async (offre) => {
    console.log(offre, "offre");
    let offreApplied;
    offreApplied = await OffreService.applyForOffre(
      offre.id,
      loggedUser.courriel
    );
    console.log(offreApplied, "offreApplied");
    if (offreApplied != null) {
      alert("Application recu");
    }
  };

  const onClickClose = () => {
    setShowModal(false);
    setCurrentOffre(null);
  };

  const onToggleValid = () => {
    setCurrentOffre((offre) => ({
      ...offre,
      valid: !offre.valid,
    }));
  };

  // useEffect(() => {
  //     console.log(currentOffre, "CURRENT OFFRE")
  // }, [currentOffre])

  const onClickSave = async () => {
    const updatedOffre = currentOffre;
    updatedOffre.whitelist = getListEtudiantFromOptions(
      listWhitelistedEtudiant
    );
    setCurrentOffre(updatedOffre);
    console.log(updatedOffre, "UPDATED OFFRE");
    await OffreService.saveOffre(updatedOffre);
    await updateOffres();

    console.log(listOffres, "list offres as save -------------------");
    onClickClose();
  };

  const updateOffres = async () => {
    const dbOffres =
      loggedUser.role === "ETUDIANT"
        ? await OffreService.getEtudiantOffres(loggedUser.courriel)
        : await OffreService.getAllOffres();
    console.log(dbOffres, "dbOffres in update offres");
    setListOffres(dbOffres);
  };

  const getOptionsList = () => {
    if (currentOffre != null) {

      if (listWhitelistedEtudiant.length == 0) {
        return getOptionsEtudiant(listAllEtudiant);
      } else {
        console.log(currentOffre != null, "CONDITION___________________________________")
        let listAllEtudiantArray = listAllEtudiant;
        listAllEtudiantArray = listAllEtudiantArray.filter(
          (etudiant) =>
            !listWhitelistedEtudiant.some(
              (whitelistedEtudiantOption) =>
                whitelistedEtudiantOption.value.id === etudiant.id
            )
        );
        return getOptionsEtudiant(listAllEtudiantArray).concat(
          listWhitelistedEtudiant
        );

      }
    }
  }

  const getBoolIcon = (bool) => {
    return bool ? (
      <AiOutlineCheckCircle color="green" />
    ) : (
      <AiOutlineCloseCircle color="red" />
    );
  };

  return (
    <div className="container" style={{ textAlign: "center" }}>
      <h1>Offres</h1>
      <table className="table border">
        <thead>
          <tr>
            <th colSpan="3" style={{ color: "black" }}>
              Titre
            </th>
            <th colSpan="3" style={{ color: "black" }}>
              Entreprise
            </th>
            {loggedUser.role !== "ETUDIANT" && <th colSpan="1">Valide</th>}
            <th></th>
          </tr>
        </thead>
        <tbody>
          {loggedUser.role === "ETUDIANT"
            ? listOffres.map((offre) => (
              <tr key={offre.id.toString()}>
                <td colSpan="3">{offre.titre}</td>
                <td colSpan="3">{offre.entreprise}</td>
                <td colSpan="1">
                  <input
                    type="button"
                    onClick={() => onClickOffre(offre)}
                    value="Détails"
                    className="p-1 btn-secondary"
                  />
                </td>
              </tr>
            ))
            : listOffres.map((offre) => (
              <tr key={offre.id.toString()}>
                <td colSpan="3">{offre.titre}</td>
                <td colSpan="3">{offre.entreprise}</td>
                <td colSpan="1">
                  {offre.valid ? (
                    <AiOutlineCheckCircle color="green" />
                  ) : (
                    <AiOutlineCloseCircle color="red" />
                  )}
                </td>
                <td colSpan="1">
                  <input
                    type="button"
                    onClick={() => onClickOffre(offre)}
                    value="Détails"
                    className="p-1 btn-secondary"
                  />
                </td>
              </tr>
            ))}
        </tbody>
      </table>
      <ReactModal isOpen={showModal} ariaHideApp={false}>
        <div className="container text-center">
          <AiOutlineClose color="red" size="24px" onClick={onClickClose} />
          <div className="row">
            <h3 className="col-2">Titre</h3>
            <h3 className="col-4">Description</h3>
            <h3 className="col-2">Entreprise</h3>
            <h3 className="col-2">Adresse</h3>
            {loggedUser.role !== "ETUDIANT" && (
              <h3 className="col-2">Validity</h3>
            )}
          </div>

          {currentOffre &&
            <div>
              <div className="row mt-4">
                <div className="col-2">{currentOffre.titre}</div>
                <div className="col-4">{currentOffre.description}</div>
                <div className="col-2">{currentOffre.entreprise}</div>
                <div className="col-2">{currentOffre.adresse}</div>
                {loggedUser.role !== "ETUDIANT" && (
                  <div className="col-2 form-check ">
                    <label className="form-check-label" htmlFor="valid">
                      {" "}
                      <input
                        type="checkbox"
                        name="valid"
                        className="form-check-input"
                        checked={currentOffre.valid}
                        onChange={onToggleValid}
                      />
                      Valid{" "}
                    </label>
                  </div>
                )}
              </div>
              <br />
              <br />
              <br />

              <div className="row text-center">
                <h3 className="col-2">Date Debut</h3>
                <h3 className="col-2">Date Fin</h3>
                <h3 className="col-2">Duree Total</h3>
                <h3 className="col-2">Horaire</h3>
                <h3 className="col-2">Total Heure Semaine</h3>
                <h3 className="col-2">Taux Horaire</h3>
              </div>
              <div className="row mt-4 text-center">
                <div className="col-2">{currentOffre.dateDebut}</div>
                <div className="col-2">{currentOffre.dateFin}</div>
                <div className="col-2">{currentOffre.nbTotalSemaine} semaines</div>
                <div className="col-2">{currentOffre.horaire}</div>
                <div className="col-2">
                  {currentOffre.nbTotalHeuresParSemaine} heures
                </div>
                <div className="col-2">{currentOffre.tauxHoraire} $/h</div>
                {loggedUser.role === "ETUDIANT" && (
                  <div className="row">
                    <input
                      type="button"
                      onClick={() => appliquerOffre(currentOffre)}
                      value="Appliquer à l offre"
                      className="p-1 btn-primary"
                    />
                  </div>
                )}
              </div>
            </div>
          }

          {loggedUser.role === "GESTIONNAIRE" && (
            <div className="mt-4">
              <div className="row">
                <div className="col-6">
                  <h1>Select Etudiants</h1>
                  <MultiSelect
                    options={getOptionsList()}
                    value={listWhitelistedEtudiant}
                    onChange={setListWhitelistedEtudiant}
                    labelledBy="Select"
                  />
                </div>
                <div className="col-6">
                  <h1>Whitelisted Etudiants</h1>
                  {listWhitelistedEtudiant.map((etudiant, index) => (
                    <li key={index}>{etudiant.label}</li>
                  ))}
                </div>
              </div>
              <div className="row text-center">
                <input type="button" value="Save" onClick={onClickSave}></input>
              </div>
            </div>
          )}

          {(loggedUser.role === "MONITEUR" && currentOffre) &&
            <div>
              <h2 className="text-center text-muted mt-4">Candidats</h2>
              <table className="table">
                <thead>
                  <tr>
                    <th scope="col" className="text-secondary">Nom d'étudiant</th>
                    <th scope="col" className="text-secondary">Courriel</th>
                    <th scope="col" className="text-secondary">Permis</th>
                    <th scope="col" className="text-secondary">Voiture</th>
                  </tr>
                </thead>
                <tbody>
                  {showModal &&
                    currentOffre.applicants.map((etudiant) => (
                      <tr key={etudiant.id}>
                        <td>
                          {etudiant.prenom} {etudiant.nom}
                        </td>
                        <td>{etudiant.courriel}</td>
                        <td>{getBoolIcon(etudiant.hasLicense)}</td>
                        <td>{getBoolIcon(etudiant.hasVoiture)}</td>
                      </tr>
                    ))}
                </tbody>
              </table>
            </div>
          }
        </div>
      </ReactModal>
    </div>
  );
};

export default Offres;
