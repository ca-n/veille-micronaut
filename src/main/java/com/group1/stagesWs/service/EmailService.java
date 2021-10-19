package com.group1.stagesWs.service;

import com.group1.stagesWs.model.Gestionnaire;
import com.group1.stagesWs.repositories.GestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    public void sendGestionnaireEmailCVAjouter() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        String[] emailArray = getAllGestionnairesEmail().toArray(new String[0]);
        message.setTo(emailArray);
        message.setSubject("Un nouveau CV à été ajouté sur la plateforme OSEVM");
        message.setText("Un nouveau CV a été ajouté. Veuillez vous connecter à l'application OSE version meilleur pour voir les CV.");
        emailSender.send(message);
    }

    private List<String> getAllGestionnairesEmail(){
        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        List<String> emailList = new ArrayList<>();
        for (Gestionnaire gestionnaire : gestionnaireList) {
            emailList.add(gestionnaire.getCourriel());
        }
        return emailList;
    }
}