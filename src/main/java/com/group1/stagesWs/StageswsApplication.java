package com.group1.stagesWs;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.repositories.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StageswsApplication implements CommandLineRunner {

    @Autowired
    OffreRepository offreRepository;

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
    }
}
