package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.SessionManager;
import xyz.carn.model.*;
import xyz.carn.model.enums.NotifStatus;
import xyz.carn.model.enums.Status;
import xyz.carn.repository.GestionnaireRepository;
import xyz.carn.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class NotificationService {
    private final NotificationRepository repository;
    private final GestionnaireRepository gestionnaireRepository;

    private final UserService userService;

    public NotificationService(NotificationRepository repository, GestionnaireRepository gestionnaireRepository, UserService userService) {
        this.repository = repository;
        this.gestionnaireRepository = gestionnaireRepository;
        this.userService = userService;
    }

    public Notification saveNotification(Notification notification) {
        return repository.save(notification);
    }

    public void saveNotificationEtudiant(Notification notification, Etudiant etudiant) {
        notification = saveNotification(notification);
        List<Notification> notifications = etudiant.getNotifications();
        notifications.add(notification);
        etudiant.setNotifications(notifications);
        userService.saveEtudiant(etudiant);
    }

    public void saveNotificationSuperviseur(Notification notification, Superviseur superviseur) {
        notification = saveNotification(notification);
        List<Notification> notifications = superviseur.getNotifications();
        notifications.add(notification);
        superviseur.setNotifications(notifications);
        userService.saveSuperviseur(superviseur);
    }

    public void saveNotificationMoniteur(Notification notification, Moniteur moniteur) {
        notification = saveNotification(notification);
        List<Notification> notifications = moniteur.getNotifications();
        notifications.add(notification);
        moniteur.setNotifications(notifications);
        userService.saveMoniteur(moniteur);
    }

    public void saveNotificationGestionnaire(Notification notification) {
        Optional<Gestionnaire> optional = userService.getGestionnaire();
        if (optional.isEmpty()) return;
        Gestionnaire gestionnaire = optional.get();
        List<Notification> notifications = gestionnaire.getNotifications();
        notifications.add(notification);
        gestionnaire.setNotifications(notifications);
        gestionnaireRepository.save(gestionnaire);
    }

    public List<Notification> getNotificationsByGestionnaire() {
        List<Notification> notifications = userService.getGestionnaire()
                .map(Gestionnaire::getNotifications)
                .orElse(List.of());
        return notifications.stream()
                .filter(notification -> notification.getSession().equals(currentSession()))
                .collect(Collectors.toList());
    }

    public List<Notification> getNotificationsBySuperviseurId(int id) {
        List<Notification> notifications = userService.getSuperviseurById(id)
                .map(Superviseur::getNotifications)
                .orElse(List.of());
        return notifications.stream()
                .filter(notification -> notification.getSession().equals(currentSession()))
                .collect(Collectors.toList());
    }

    public List<Notification> getNotificationsByEtudiantId(int id) {
        List<Notification> notifications = userService.getEtudiantById(id)
                .map(Etudiant::getNotifications)
                .orElse(List.of());
        return notifications.stream()
                .filter(notification -> notification.getSession().equals(currentSession()))
                .collect(Collectors.toList());
    }

    public List<Notification> getNotificationsByMoniteurId(int id) {
        List<Notification> notifications = userService.getMoniteurById(id)
                .map(Moniteur::getNotifications)
                .orElse(List.of());
        return notifications.stream()
                .filter(notification -> notification.getSession().equals(currentSession()))
                .collect(Collectors.toList());
    }

    public void notifyOnNewCV(CV cv) {
        Notification notification = new Notification(
                "Il y a un nouveau cv a verifier de l'etudiant : "
                        + cv.getEtudiant().getPrenom()
                        + " "
                        + cv.getEtudiant().getNom(), NotifStatus.ALERT);

        saveNotificationGestionnaire(notification);
    }

    public void notifyOnCvVerification(CV cv) {
        Notification notification = new Notification("Votre cv " + cv.getNom()
                + (cv.getStatus().equals(Status.REJECTED) ? " a été rejeté" : " a été accepté"), NotifStatus.ALERT);

        saveNotificationEtudiant(notification, cv.getEtudiant());
    }

    public void notifyOnNewOffre(Offre offre) {
        Notification notification = new Notification("Il y a une nouvelle offre qui vous êtes disponible.\n"
                + " L'offre est " + offre.getTitre() + " de l'entreprise " + offre.getEntreprise(),
                NotifStatus.ALERT);

        offre.getWhitelist().forEach(etudiant ->  saveNotificationEtudiant(notification, etudiant));
    }

    public void notifyOnNewEntrevue(Entrevue entrevue) {
        Notification etudiantNotification = new Notification(
                "Vous etes convoque a une entrevue le : "
                        + entrevue.getDate()
                        + " avec le moniteur "
                        + entrevue.getMoniteur().getPrenom()
                        + " "
                        + entrevue.getMoniteur().getNom() + " a l'heure " + entrevue.getTime(),
                NotifStatus.URGENT);
        saveNotificationEtudiant(etudiantNotification, entrevue.getEtudiant());

        Notification moniteurNotification = new Notification(
                "Vous avez une entrevue le : "
                        + entrevue.getDate()
                        + " avec l'étudiant "
                        + entrevue.getEtudiant().getPrenom()
                        + " "
                        + entrevue.getEtudiant().getNom()  + " a l'heure " + entrevue.getTime(),
                NotifStatus.URGENT);
        saveNotificationMoniteur(moniteurNotification, entrevue.getMoniteur());
    }

    private String currentSession() {
        return SessionManager.CURRENT_SESSION.getNomSession();
    }
}
