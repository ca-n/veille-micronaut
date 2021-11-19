package com.group1.stagesWs.controller;


import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.model.Notification;
import com.group1.stagesWs.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(path = "/etudiant/{id}")
    public ResponseEntity<List<Notification>> getAllNotificationByEtudiant(@PathVariable("id") int id) {
        return new ResponseEntity<>(notificationService.getNotificationsEtudiant(id), HttpStatus.OK);
    }

    @GetMapping(path = "/moniteur/{id}")
    public ResponseEntity<List<Notification>> getAllNotificationByMoniteur(@PathVariable("id") int id) {
        return new ResponseEntity<>(notificationService.getNotificationsMoniteur(id), HttpStatus.OK);
    }

    @GetMapping(path = "/superviseur/{id}")
    public ResponseEntity<List<Notification>> getAllNotificationBySuperviseur(@PathVariable("id") int id) {
        return new ResponseEntity<>(notificationService.getNotificationsSuperviseur(id), HttpStatus.OK);
    }

    @GetMapping(path = "/gestionnaire")
    public ResponseEntity<List<Notification>> getAllNotificationByGestionnaire() {
        return new ResponseEntity<>(notificationService.getNotificationsGestionnaire(), HttpStatus.OK);
    }


}
