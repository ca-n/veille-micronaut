package com.group1.stagesWs.model;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.enums.Session;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dateRappelNotif;
    private String contenu;
    private String categorie; //A CHANGER -Veux rendre le type un enum contenant tous les types de categories de notif.

    private Session session;

    public Notification() {
        this.session = SessionManager.CURRENT_SESSION;
    }

    public Notification(LocalDate dateRappelNotif, String contenu, String categorie) {
        this.dateRappelNotif = dateRappelNotif;
        this.contenu = contenu;
        this.categorie = categorie;
        this.session = SessionManager.CURRENT_SESSION;
    }
}
