package com.group1.stagesWs.service;

import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NotificationService {

    private final EtudiantRepository etudiantRepository;

    private final MoniteurRepository moniteurRepository;

    private final SuperviseurRepository superviseurRepository;

    private final GestionnaireRepository gestionnaireRepository;

    private final NotificationRepository notificationRepository;

    public NotificationService(EtudiantRepository etudiantRepository,
                               MoniteurRepository moniteurRepository,
                               SuperviseurRepository superviseurRepository,
                               GestionnaireRepository gestionnaireRepository,
                               NotificationRepository notificationRepository) {
        this.etudiantRepository = etudiantRepository;
        this.moniteurRepository = moniteurRepository;
        this.superviseurRepository = superviseurRepository;
        this.gestionnaireRepository = gestionnaireRepository;
        this.notificationRepository = notificationRepository;
    }


    public boolean saveNotificationEtudiant(Notification notification, int etudiantId){
        notification = saveNotification(notification);
        Optional<Etudiant> etudiant = etudiantRepository.findById(etudiantId);
        if(etudiant.isPresent()){
            Set<Notification> etudiantNotifications = etudiant.get().getNotifications();
            etudiantNotifications.add(notification);
            etudiant = Optional.of(etudiantRepository.save(etudiant.get()));
            if(!etudiant.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean saveNotificationSuperviseur(Notification notification, int superviseurId){
        notification = saveNotification(notification);
        Optional<Superviseur> superviseur = superviseurRepository.findById(superviseurId);
        if(superviseur.isPresent()){
            Set<Notification> superviseurNotifications = superviseur.get().getNotifications();
            superviseurNotifications.add(notification);
            superviseur = Optional.of(superviseurRepository.save(superviseur.get()));
            if(!superviseur.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean saveNotificationMoniteur(Notification notification, int moniteurId){
        notification = saveNotification(notification);
        Optional<Moniteur> moniteur = moniteurRepository.findById(moniteurId);
        if(moniteur.isPresent()){
            Set<Notification> moniteurNotifications = moniteur.get().getNotifications();
            moniteurNotifications.add(notification);
            moniteur = Optional.of(moniteurRepository.save(moniteur.get()));
            if(!moniteur.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean saveNotificationGestionnaire(Notification notification){
        notification = saveNotification(notification);
        List<Gestionnaire> listGestionnaires = gestionnaireRepository.findAll();
        if(listGestionnaires.size() > 0){
            for(Gestionnaire gestionnaire : listGestionnaires) {
                Set<Notification> gestionnaireNotifications = gestionnaire.getNotifications();
                gestionnaireNotifications.add(notification);
                gestionnaire = gestionnaireRepository.save(gestionnaire);
                if (gestionnaire == null) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private Notification saveNotification(Notification notification){
        return notificationRepository.save(notification);
    }


}
