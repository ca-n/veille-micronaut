package com.group1.stagesWs;

import com.group1.stagesWs.controller.UserController;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Gestionnaire;
import com.group1.stagesWs.model.Moniteur;
import com.group1.stagesWs.model.Superviseur;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.GestionnaireRepository;
import com.group1.stagesWs.repositories.MoniteurRepository;
import com.group1.stagesWs.repositories.SuperviseurRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StageswsApplicationTests {

    @Autowired
    UserController controller;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    @Autowired
    private MoniteurRepository moniteurRepository;

    @Autowired
    private SuperviseurRepository superviseurRepository;

    @BeforeAll
    public void insertData() {
        Etudiant etudiant1 = new Etudiant("Jeremie", "Munger", "jay123@email.com", "password123", "123-456-7890", "Informatique", "1 boul sa Brossard", "009875", true, true);
        etudiantRepository.save(etudiant1);

        Gestionnaire gestionnaire1 = new Gestionnaire("Marcel", "Dupuis", "mdupuis@email.ca", "allo123", "514-879-8794", "Informatique");
        gestionnaireRepository.save(gestionnaire1);

        Moniteur moniteur1 = new Moniteur("Pascal", "Desjardins", "pascald@email.ca", "banque123", "123-456-7891", "Banque Desjardins", "2 boul leduc montreal");
        moniteurRepository.save(moniteur1);

        Superviseur superviseur1 = new Superviseur("Johny", "Cash", "cashJ@email.ca", "money123", "995-897-4562", "Informatique", "AI");
        superviseurRepository.save(superviseur1);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testFindEtudiantByLogin() {
        assertNotNull(controller.login("jay123@email.com", "password123"));
    }

    @Test
    void testFindGestionnaireByLogin() {
        assertNotNull(controller.login("mdupuis@email.ca", "allo123"));
    }

    @Test
    void testFindMoniteurByLogin() {
        assertNotNull(controller.login("pascald@email.ca", "banque123"));
    }

    @Test
    void testFindSuperviseurByLogin() { assertNotNull(controller.login("cashJ@email.ca", "money123")); }

    @Test
    void testAddMoniteur() {
        Moniteur moniteur = controller.addMoniteur("Alfa", "Collins", "acollins@email.ca", "cooli123", "123-456-7891", "Banque Desjardins", "2 boul leduc montreal");
        assertNotNull(moniteurRepository.findMoniteurByCourriel("acollins@email.ca"));
    }

}
