package com.group1.stagesWs.service;


import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Superviseur;
import com.group1.stagesWs.model.Moniteur;
import com.group1.stagesWs.model.User;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.GestionnaireRepository;
import com.group1.stagesWs.repositories.MoniteurRepository;
import com.group1.stagesWs.repositories.SuperviseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    @Autowired
    private MoniteurRepository moniteurRepository;

    @Autowired
    private SuperviseurRepository superviseurRepository;



    public Optional<Etudiant> addEtudiant(Etudiant etudiant){
        return Optional.of(etudiantRepository.save(etudiant));
    }

    public Moniteur addMoniteur(String prenom, String nom, String courriel, String password, String numTelephone, String nomEntreprise, String adresseEntreprise) {
        Moniteur moniteur = new Moniteur(prenom, nom, courriel, password, numTelephone, nomEntreprise, adresseEntreprise);
        return moniteurRepository.save(moniteur);
    }

    public Superviseur addSuperviseur(Superviseur superviseur) {
        return superviseurRepository.save(superviseur);
    }

    public User login(String email, String pwd) {
        if (etudiantRepository.findEtudiantByCourriel(email) != null) {
            return etudiantRepository.findEtudiantByCourrielAndPassword(email, pwd);
        }
        if (gestionnaireRepository.findGestionnaireByCourriel(email) != null) {
            return gestionnaireRepository.findGestionnaireByCourrielAndPassword(email, pwd);
        }
        if (moniteurRepository.findMoniteurByCourriel(email) != null) {
            return moniteurRepository.findMoniteurByCourrielAndPassword(email, pwd);
        }
        if (superviseurRepository.findSuperviseurByCourriel(email) != null) {
            return superviseurRepository.findSuperviseurByCourrielAndPassword(email, pwd);
        }
        return null;
    }
}
