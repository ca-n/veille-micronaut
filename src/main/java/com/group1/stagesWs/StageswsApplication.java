package com.group1.stagesWs;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Moniteur;
import com.group1.stagesWs.model.Superviseur;
import com.group1.stagesWs.model.UserType;
import com.group1.stagesWs.model.factory.UserFactory;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.MoniteurRepository;
import com.group1.stagesWs.repositories.SuperviseurRepository;
import com.group1.stagesWs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StageswsApplication{

    public static void main(String[] args) {
        SpringApplication.run(StageswsApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
////        UserFactory userFactory = new UserFactory();
////        Etudiant etudiant = (Etudiant) userFactory.getInstance(UserType.ETUDIANT);
//        UserService service = new UserService();
//        Etudiant etudiant = new Etudiant(
//                "Pascal",
//                "Bourgoin",
//                "test@test.com",
//                "password",
//                 "123456789",
//                "technique",
//                "addy 123",
//                "123456",
//                true,
//                true);
//
//        service.addEtudiant(etudiant);
//    }
}
