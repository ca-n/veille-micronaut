package com.group1.stagesWs;

import com.group1.stagesWs.enums.CVStatus;
import com.group1.stagesWs.enums.Session;
import com.group1.stagesWs.enums.UserType;
import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.*;
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


    public StageswsApplication(OffreRepository offreRepository, EtudiantRepository etudiantRepository, MoniteurRepository moniteurRepository, GestionnaireRepository gestionnaireRepository, SuperviseurRepository superviseurRepository, CVRepository cvRepository) {
        this.offreRepository = offreRepository;
        this.etudiantRepository = etudiantRepository;
        this.moniteurRepository = moniteurRepository;
        this.gestionnaireRepository = gestionnaireRepository;
        this.superviseurRepository = superviseurRepository;
        this.cvRepository = cvRepository;
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

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setPrenom("bob");
        etudiant2.setNom("Star");
        etudiant2.setCourriel("bob@gmail.com");
        etudiant2.setPassword("Password1");
        etudiant2.setNumTelephone("123145676");
        etudiant2.setRole(UserType.ETUDIANT);
        etudiant2.setProgramme("Info");
        etudiant2.setAdresse("113 lapierre");
        etudiant2.setNumMatricule("12345678");
        etudiant2.setHasLicense(true);
        etudiant2.setHasVoiture(true);
        etudiantRepository.save(etudiant2);

        Etudiant etudiant3 = new Etudiant();
        etudiant3.setPrenom("bub");
        etudiant3.setNom("Star");
        etudiant3.setCourriel("bub@gmail.com");
        etudiant3.setPassword("Password1");
        etudiant3.setNumTelephone("123145676");
        etudiant3.setRole(UserType.ETUDIANT);
        etudiant3.setProgramme("Info");
        etudiant3.setAdresse("113 lapierre");
        etudiant3.setNumMatricule("12345678");
        etudiant3.setHasLicense(true);
        etudiant3.setHasVoiture(true);
        etudiantRepository.save(etudiant3);

        Etudiant etudiant4 = new Etudiant();
        etudiant4.setPrenom("rick");
        etudiant4.setNom("Star");
        etudiant4.setCourriel("rick@gmail.com");
        etudiant4.setPassword("Password1");
        etudiant4.setNumTelephone("123145676");
        etudiant4.setRole(UserType.ETUDIANT);
        etudiant4.setProgramme("Info");
        etudiant4.setAdresse("113 lapierre");
        etudiant4.setNumMatricule("12345678");
        etudiant4.setHasLicense(true);
        etudiant4.setHasVoiture(true);
        etudiantRepository.save(etudiant4);

        Etudiant etudiant5 = new Etudiant();
        etudiant5.setPrenom("Eric");
        etudiant5.setNom("Bob");
        etudiant5.setCourriel("eric@gmail.com");
        etudiant5.setPassword("Password1");
        etudiant5.setNumTelephone("123145676");
        etudiant5.setRole(UserType.ETUDIANT);
        etudiant5.setProgramme("Info");
        etudiant5.setAdresse("113 lapierre");
        etudiant5.setNumMatricule("12345678");
        etudiant5.setHasLicense(true);
        etudiant5.setHasVoiture(true);
        etudiantRepository.save(etudiant5);




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

        Moniteur moniteur2 = new Moniteur();
        moniteur2.setPrenom("mat");
        moniteur2.setNom("Bourgoin");
        moniteur2.setCourriel("mat@gmail.com");
        moniteur2.setPassword("Password1");
        moniteur2.setNumTelephone("2389238");
        moniteur2.setRole(UserType.MONITEUR);
        moniteur2.setNomEntreprise("Bob the builder");
        moniteur2.setAdresseEntreprise("110 lapierre");
        moniteurRepository.save(moniteur2);

        Moniteur moniteur3= new Moniteur();
        moniteur3.setPrenom("neil");
        moniteur3.setNom("Bourgoin");
        moniteur3.setCourriel("neil@gmail.com");
        moniteur3.setPassword("Password1");
        moniteur3.setNumTelephone("2389238");
        moniteur3.setRole(UserType.MONITEUR);
        moniteur3.setNomEntreprise("Bob the builder");
        moniteur3.setAdresseEntreprise("110 lapierre");
        moniteurRepository.save(moniteur3);

        Moniteur moniteur4 = new Moniteur();
        moniteur4.setPrenom("Jeremie");
        moniteur4.setNom("Bourgoin");
        moniteur4.setCourriel("jeremie@gmail.com");
        moniteur4.setPassword("Password1");
        moniteur4.setNumTelephone("2389238");
        moniteur4.setRole(UserType.MONITEUR);
        moniteur4.setNomEntreprise("Bob the builder");
        moniteur4.setAdresseEntreprise("110 lapierre");
        moniteurRepository.save(moniteur4);

        Moniteur moniteur5 = new Moniteur();
        moniteur5.setPrenom("Bob");
        moniteur5.setNom("Bourgoin");
        moniteur5.setCourriel("bob@gmail.com");
        moniteur5.setPassword("Password1");
        moniteur5.setNumTelephone("2389238");
        moniteur5.setRole(UserType.MONITEUR);
        moniteur5.setNomEntreprise("Bob the builder");
        moniteur5.setAdresseEntreprise("110 lapierre");
        moniteurRepository.save(moniteur5);

        Moniteur moniteur6 = new Moniteur();
        moniteur6.setPrenom("Lynn");
        moniteur6.setNom("Bob");
        moniteur6.setCourriel("lynn@gmail.com");
        moniteur6.setPassword("Password1");
        moniteur6.setNumTelephone("2389238");
        moniteur6.setRole(UserType.MONITEUR);
        moniteur6.setNomEntreprise("Bob the builder");
        moniteur6.setAdresseEntreprise("110 lapierre");
        moniteurRepository.save(moniteur6);


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

        Superviseur superviseur2 = new Superviseur();
        superviseur2.setPrenom("Neil");
        superviseur2.setNom("Munger");
        superviseur2.setCourriel("neil@gmail.com");
        superviseur2.setPassword("Password1");
        superviseur2.setNumTelephone("82308920938");
        superviseur2.setRole(UserType.SUPERVISEUR);
        superviseur2.setDepartement("Informatique");
        superviseur2.setSpecialite("fullstack");
        superviseurRepository.save(superviseur2);

        Superviseur superviseur3 = new Superviseur();
        superviseur3.setPrenom("Mat");
        superviseur3.setNom("Munger");
        superviseur3.setCourriel("mat@gmail.com");
        superviseur3.setPassword("Password1");
        superviseur3.setNumTelephone("82308920938");
        superviseur3.setRole(UserType.SUPERVISEUR);
        superviseur3.setDepartement("Informatique");
        superviseur3.setSpecialite("fullstack");
        superviseurRepository.save(superviseur3);

        Superviseur superviseur4 = new Superviseur();
        superviseur4.setPrenom("pascal");
        superviseur4.setNom("Munger");
        superviseur4.setCourriel("pascal@gmail.com");
        superviseur4.setPassword("Password1");
        superviseur4.setNumTelephone("82308920938");
        superviseur4.setRole(UserType.SUPERVISEUR);
        superviseur4.setDepartement("Informatique");
        superviseur4.setSpecialite("fullstack");
        superviseurRepository.save(superviseur4);

        Superviseur superviseur5 = new Superviseur();
        superviseur5.setPrenom("Ricky");
        superviseur5.setNom("Munger");
        superviseur5.setCourriel("ricky@gmail.com");
        superviseur5.setPassword("Password1");
        superviseur5.setNumTelephone("82308920938");
        superviseur5.setRole(UserType.SUPERVISEUR);
        superviseur5.setDepartement("Informatique");
        superviseur5.setSpecialite("fullstack");
        superviseurRepository.save(superviseur5);

        Superviseur superviseur6 = new Superviseur();
        superviseur6.setPrenom("bob");
        superviseur6.setNom("Munger");
        superviseur6.setCourriel("bob@gmail.com");
        superviseur6.setPassword("Password1");
        superviseur6.setNumTelephone("82308920938");
        superviseur6.setRole(UserType.SUPERVISEUR);
        superviseur6.setDepartement("Informatique");
        superviseur6.setSpecialite("fullstack");
        superviseurRepository.save(superviseur6);

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
//
        CV cv4 = new CV(); // accepted
        cv4.setStatus(CVStatus.ACCEPTED);
        cv4.setEtudiant(etudiant);
        cv4.setSession(Session.AUTOMNE_2021);
        cv4.setNom("cv-accepted.pdf");
//
        cvRepository.saveAll(List.of(cv1, cv2, cv3, cv4));

        Offre offre1 = new Offre("TITRE1", "DESCRIPTION1", "ENTREPRISE1", true, "1 rue de la riviere Brossard", "2021-12-05", "2022-3-05", 13, "9:00 à 5:00", 40, 21);
        Offre offre2 = new Offre("TITRE2", "DESCRIPTION2", "ENTREPRISE2", true, "6 boul lachine Montreal", "2021-12-05", "2022-3-05", 13, "9:00 à 5:00", 40, 20);
        Offre offre3 = new Offre("TITRE3", "DESCRIPTION3", "ENTREPRISE3", false, "2055 route 206 Laval", "2022-1-05", "2022-4-05", 13, "9:00 à 5:00", 40, 17.50);
        Offre offre4 = new Offre("TITRE4", "DESCRIPTION4", "ENTREPRISE4", false, "1052 montee saint-claude Laprairie", "2021-12-05", "2022-3-05", 13, "9:00 à 5:00", 40, 25);
        Offre offre5 = new Offre("TITRE5", "DESCRIPTION5", "ENTREPRISE5", true, "10 boul dagenais Montreal", "2021-12-05", "2022-3-05", 13, "9:00 à 5:00", 40, 18.75);
        Offre offre6 = new Offre("TITRE6", "DESCRIPTION6", "ENTREPRISE6", true, "113 lapierre Montreal", "2022-12-05", "2023-3-05", 13, "9:00 à 5:00", 40, 20.75);

//        offre1.getVisibiliteEtudiant().setWhitelistedEtudiant(Set.of(etudiant));
        offreRepository.save(offre1);
        offreRepository.save(offre2);
        offreRepository.save(offre3);
        offreRepository.save(offre4);
        offreRepository.save(offre5);
        offreRepository.save(offre6);
    }
}