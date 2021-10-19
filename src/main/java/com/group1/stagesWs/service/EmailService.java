package com.group1.stagesWs.service;

import com.group1.stagesWs.model.Gestionnaire;
import com.group1.stagesWs.repositories.GestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
        List<Gestionnaire> gestionnaireList = gestionnaireRepository.findAll();
        for (Gestionnaire gestionnaire : gestionnaireList) {
            message.setTo(gestionnaire.getCourriel());
        }
        message.setSubject("Un nouveau CV à été ajouté");
        message.setText("Un nouveau CV à été ajouté. Veuillez vous connecter a l'application pour voir les CV.");
        emailSender.send(message);
    }
}