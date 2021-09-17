package com.group1.stagesWs;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StageswsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StageswsApplication.class, args);

        UserService service = new UserService();

//        Etudiant etudiant = new Etudiant("Pascal", "Bourgoin", "test@test.com", "password", "123456789", "informatique", "addresse123", "1750818",true,true);

        Etudiant etudiant = new Etudiant();
        service.addEtudiant(etudiant);
    }

}
