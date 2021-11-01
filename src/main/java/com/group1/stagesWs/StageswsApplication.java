package com.group1.stagesWs;

import com.group1.stagesWs.enums.CVStatus;
import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.*;
import com.group1.stagesWs.service.StageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;



@SpringBootApplication
public class StageswsApplication implements CommandLineRunner{

    private final OffreRepository offreRepository;
    private final EtudiantRepository etudiantRepository;
    private final MoniteurRepository moniteurRepository;
    private final GestionnaireRepository gestionnaireRepository;
    private final SuperviseurRepository superviseurRepository;
    private final CVRepository cvRepository;
    private final StageService service;

    public StageswsApplication(OffreRepository offreRepository, EtudiantRepository etudiantRepository, MoniteurRepository moniteurRepository, GestionnaireRepository gestionnaireRepository, SuperviseurRepository superviseurRepository, CVRepository cvRepository, StageService service) {
        this.offreRepository = offreRepository;
        this.etudiantRepository = etudiantRepository;
        this.moniteurRepository = moniteurRepository;
        this.gestionnaireRepository = gestionnaireRepository;
        this.superviseurRepository = superviseurRepository;
        this.cvRepository = cvRepository;
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(StageswsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Etudiant etudiant1 = new Etudiant();
        etudiant1.setPrenom("Patrick");
        etudiant1.setNom("Star");
        etudiant1.setCourriel("pat@gmail.com");
        etudiant1.setPassword("Password1");
        etudiant1.setNumTelephone("123145676");
        etudiant1.setRole(UserType.ETUDIANT);
        etudiant1.setProgramme("Info");
        etudiant1.setAdresse("113 lapierre");
        etudiant1.setNumMatricule("12345678");
        etudiant1.setHasLicense(true);
        etudiant1.setHasVoiture(true);
        etudiantRepository.save(etudiant1);

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

        Etudiant etudiant = new Etudiant();
        etudiant.setPrenom("Mathieu");
        etudiant.setNom("Felton");
        etudiant.setCourriel("mathieu@gmail.com");
        etudiant.setPassword("Password1");
        etudiant.setNumTelephone("2323232323");
        etudiant.setRole(UserType.ETUDIANT);
        etudiant.setProgramme("Informatique");
        etudiant.setAdresse("113 lapierre");
        etudiant.setNumMatricule("1822323");
        etudiant.setHasLicense(true);
        etudiant.setSuperviseur(superviseur);
        etudiantRepository.save(etudiant);

        CV cv1 = new CV(); // pending
        cv1.setEtudiant(etudiant);
        cv1.setNom("cv-pending.pdf");
        CV cv2 = new CV(); // accepted
        cv2.setStatus(CVStatus.ACCEPTED);
        cv2.setEtudiant(etudiant);
        cv2.setNom("cv-accepted.pdf");
        CV cv3 = new CV(); // rejected
        cv3.setStatus(CVStatus.REJECTED);
        cv3.setEtudiant(etudiant);
        cv3.setNom("cv-rejected.pdf");
        cvRepository.saveAll(List.of(cv1, cv2, cv3));

        Offre offre1 = new Offre("TITRE1", "DESCRIPTION1", "ENTREPRISE1", true, "1 rue de la riviere Brossard", "2021-12-05", "2022-3-05", 13, "9:00 à 5:00", 40, 21);
        Offre offre2 = new Offre("TITRE2", "DESCRIPTION2", "ENTREPRISE2", true, "6 boul lachine Montreal", "2021-12-05", "2022-3-05", 13, "9:00 à 5:00", 40, 20);
        Offre offre3 = new Offre("TITRE3", "DESCRIPTION3", "ENTREPRISE3", false, "2055 route 206 Laval", "2022-1-05", "2022-4-05", 13, "9:00 à 5:00", 40, 17.50);
        Offre offre4 = new Offre("TITRE4", "DESCRIPTION4", "ENTREPRISE4", false, "1052 montee saint-claude Laprairie", "2021-12-05", "2022-3-05", 13, "9:00 à 5:00", 40, 25);
        Offre offre5 = new Offre("TITRE5", "DESCRIPTION5", "ENTREPRISE5", true, "10 boul dagenais Montreal", "2021-12-05", "2022-3-05", 13, "9:00 à 5:00", 40, 18.75);
//        offre1.getVisibiliteEtudiant().setWhitelistedEtudiant(Set.of(etudiant));
        offreRepository.save(offre1);
        offreRepository.save(offre2);
        offreRepository.save(offre3);
        offreRepository.save(offre4);
        offreRepository.save(offre5);
    }
}