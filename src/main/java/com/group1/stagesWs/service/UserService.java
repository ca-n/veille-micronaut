package com.group1.stagesWs.service;
import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements SessionManager<User> {

    private final EtudiantRepository etudiantRepository;
    private final GestionnaireRepository gestionnaireRepository;
    private final MoniteurRepository moniteurRepository;
    private final SuperviseurRepository superviseurRepository;

    public UserService(EtudiantRepository etudiantRepository, GestionnaireRepository gestionnaireRepository, MoniteurRepository moniteurRepository, SuperviseurRepository superviseurRepository) {
        this.etudiantRepository = etudiantRepository;
        this.gestionnaireRepository = gestionnaireRepository;
        this.moniteurRepository = moniteurRepository;
        this.superviseurRepository = superviseurRepository;
    }

    public Optional<Etudiant> addEtudiant(Etudiant etudiant) {
        return Optional.of(etudiantRepository.save(etudiant));
    }

    public Optional<Moniteur> addMoniteur(Moniteur moniteur) {
        return Optional.of(moniteurRepository.save(moniteur));
    }

    public Optional<Superviseur> addSuperviseur(Superviseur superviseur) {
        return Optional.of(superviseurRepository.save(superviseur));
    }

    public Optional<User> login(String email, String pwd) {
        if (etudiantRepository.findEtudiantByCourrielIgnoreCase(email) != null) {
            return Optional.of(etudiantRepository.findEtudiantByCourrielIgnoreCaseAndPassword(email, pwd));
        }
        if (gestionnaireRepository.findGestionnaireByCourrielIgnoreCase(email) != null) {
            return Optional.of(gestionnaireRepository.findGestionnaireByCourrielIgnoreCaseAndPassword(email, pwd));
        }
        if (moniteurRepository.findMoniteurByCourrielIgnoreCase(email) != null) {
            return Optional.of(moniteurRepository.findMoniteurByCourrielIgnoreCaseAndPassword(email, pwd));
        }
        if (superviseurRepository.findSuperviseurByCourrielIgnoreCase(email) != null) {
            return Optional.of(superviseurRepository.findSuperviseurByCourrielIgnoreCaseAndPassword(email, pwd));
        }
        return Optional.empty();
    }
  
    public Optional<User> findUserByCourriel(String email){
        if (etudiantRepository.findEtudiantByCourrielIgnoreCase(email) != null) {
            return Optional.of(etudiantRepository.findEtudiantByCourrielIgnoreCase(email));
        }
        if (gestionnaireRepository.findGestionnaireByCourrielIgnoreCase(email) != null) {
            return Optional.of(gestionnaireRepository.findGestionnaireByCourrielIgnoreCase(email));
        }
        if (moniteurRepository.findMoniteurByCourrielIgnoreCase(email) != null) {
            return Optional.of(moniteurRepository.findMoniteurByCourrielIgnoreCase(email));
        }
        if (superviseurRepository.findSuperviseurByCourrielIgnoreCase(email) != null) {
            return Optional.of(superviseurRepository.findSuperviseurByCourrielIgnoreCase(email));
        }
        return Optional.empty();
    }
  
    public List<Etudiant> getAllEtudiants() {
        List<Etudiant> listAllEtudiant = etudiantRepository.findAll();
        return (List<Etudiant>)(List<?>) getListForCurrentSession((List<User>)(List<?>)listAllEtudiant);
    }

    public List<Etudiant> getAllEtudiantsAllSession() {
        return etudiantRepository.findAll();
    }


    public List<Etudiant> getAllEtudiantsWithoutSuperviseur(){
        List<Etudiant> etudiantListe = etudiantRepository.findAllEtudiantBySuperviseurNull();
        return (List<Etudiant>)(List<?>) getListForCurrentSession((List<User>)(List<?>)etudiantListe);
    }

    @Override
    public List<User> getListForCurrentSession(List<User> listUser) {
        List<User> listUserCurrentSession = new ArrayList<>();
        for(User user : listUser){
            if(user.getSession() == SessionManager.CURRENT_SESSION){
                listUserCurrentSession.add(user);
            }
        }
        return listUserCurrentSession;
    }

    public Optional<Superviseur> addListeEtudiantSuperviseur(int superviseurId, List<Etudiant> listeEtudiants) {
        if(!superviseurRepository.findById(superviseurId).equals(Optional.empty())){
            Optional<Superviseur> superviseur = superviseurRepository.findById(superviseurId);
            superviseur.get().setEtudiantSupervise(listeEtudiants);
            return superviseurRepository.save(superviseur);
        }

        return Optional.empty();

    }
}
