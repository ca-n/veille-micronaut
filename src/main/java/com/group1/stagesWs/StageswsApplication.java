package com.group1.stagesWs;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Moniteur;
import com.group1.stagesWs.model.Superviseur;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.MoniteurRepository;
import com.group1.stagesWs.repositories.SuperviseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StageswsApplication implements CommandLineRunner {

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    MoniteurRepository moniteurRepository;

    @Autowired
    SuperviseurRepository superviseurRepository;


    public static void main(String[] args) {
        SpringApplication.run(StageswsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Etudiant etudiant = new Etudiant();
        etudiant.setPrenom("Pascal");
        etudiant.setNom("Bourgoin");
        etudiant.setCourriel("test@test.com");
        etudiant.setPassword("password");
        etudiant.setNumTelephone("123456789");
        etudiant.setNumMatricule("1234567");
        etudiant.setAdresse("addy 123");
        etudiant.setProgramme("Technique");
        etudiant.setHasLicense(true);
        etudiant.setHasVoiture(true);
        etudiantRepository.save(etudiant);


        Moniteur moniteur = new Moniteur();
        moniteur.setPrenom("Mathieu");
        moniteur.setNom("Felton");
        moniteur.setCourriel("mat@felt.com");
        moniteur.setPassword("password");
        moniteur.setNumTelephone("123456789");
        moniteur.setNomEntreprise("ate");
        moniteur.setAdresseEntreprise("113 chateauguay");
        moniteurRepository.save(moniteur);


        Superviseur superviseur = new Superviseur();
        superviseur.setPrenom("Neil");
        superviseur.setNom("Carry");
        superviseur.setCourriel("neil@gmail.com");
        superviseur.setPassword("password");
        superviseur.setNumTelephone("123456789");
        superviseur.setDepartement("informatique");
        superviseur.setSpecialite("full stack");
        superviseurRepository.save(superviseur);




    }



}
