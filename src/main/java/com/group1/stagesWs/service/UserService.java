package com.group1.stagesWs.service;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Moniteur;
import com.group1.stagesWs.model.Superviseur;
import com.group1.stagesWs.model.User;
import com.group1.stagesWs.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

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
        return etudiantRepository.findAll();
    }
}
