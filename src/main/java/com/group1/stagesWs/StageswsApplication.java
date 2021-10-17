package com.group1.stagesWs;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.repositories.*;
import com.group1.stagesWs.model.*;
import com.group1.stagesWs.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
public class StageswsApplication implements CommandLineRunner {

    @Autowired
    OffreRepository offreRepository;
  
    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    MoniteurRepository moniteurRepository;

    @Autowired
    GestionnaireRepository gestionnaireRepository;

    @Autowired
    SuperviseurRepository superviseurRepository;

    @Autowired
    CVRepository cvRepository;

    @Autowired
    StageService service;

    public static void main(String[] args) {
        SpringApplication.run(StageswsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Offre offre1 = new Offre("TITRE1", "DESCRIPTION1", "ENTREPRISE1", true);
        Offre offre2 = new Offre("TITRE2", "DESCRIPTION2", "ENTREPRISE2", true);
        Offre offre3 = new Offre("TITRE3", "DESCRIPTION3", "ENTREPRISE3", false);
        Offre offre4 = new Offre("TITRE4", "DESCRIPTION4", "ENTREPRISE4", false);
        Offre offre5 = new Offre("TITRE5", "DESCRIPTION5", "ENTREPRISE5", true);
        offreRepository.save(offre1);
        offreRepository.save(offre2);
        offreRepository.save(offre3);
        offreRepository.save(offre4);
        offreRepository.save(offre5);

        Etudiant etudiant = new Etudiant();
        etudiant.setPrenom("Mathieu");
        etudiant.setNom("Felton");
        etudiant.setCourriel("mat@gmail.com");
        etudiant.setPassword("Password1");
        etudiant.setNumTelephone("2323232323");
        etudiant.setRole(UserType.ETUDIANT);
        etudiant.setProgramme("Informatique");
        etudiant.setAdresse("113 lapierre");
        etudiant.setNumMatricule("1822323");
        etudiant.setHasLicense(true);
        etudiantRepository.save(etudiant);

        Moniteur moniteur = new Moniteur();
        moniteur.setPrenom("Pascal");
        moniteur.setNom("Bourgoin");
        moniteur.setCourriel("pascal@gmail.com");
        moniteur.setPassword("Password1");
        moniteur.setNumTelephone("2389238");
        moniteur.setRole(UserType.MONITEUR);
        moniteur.setNomEntreprise("Bob the builder");
        moniteur.setAdresseEntreprise("110 lapierre");
        moniteurRepository.save(moniteur);

        Gestionnaire gestionnaire = new Gestionnaire();
        gestionnaire.setPrenom("Neil");
        gestionnaire.setNom("Carrie");
        gestionnaire.setCourriel("neil@gmail.com");
        gestionnaire.setPassword("Password1");
        gestionnaire.setNumTelephone("879382378");
        gestionnaire.setRole(UserType.GESTIONNAIRE);
        gestionnaire.setDepartement("Informatique");
        gestionnaireRepository.save(gestionnaire);


        Superviseur superviseur = new Superviseur();
        superviseur.setPrenom("Jeremie");
        superviseur.setNom("Munger");
        superviseur.setCourriel("jeremie@gmail.com");
        superviseur.setPassword("Password1");
        superviseur.setNumTelephone("82308920938");
        superviseur.setRole(UserType.SUPERVISEUR);
        superviseur.setDepartement("Informatique");
        superviseur.setSpecialite("fullstack");
        superviseurRepository.save(superviseur);

    }
}
