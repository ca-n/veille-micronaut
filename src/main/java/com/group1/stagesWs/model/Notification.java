package com.group1.stagesWs.model;

import lombok.Data;

import javax.persistence.*;
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


    public Notification() {
    }

    public Notification(LocalDate dateRappelNotif, String contenu, String categorie) {
        this.dateRappelNotif = dateRappelNotif;
        this.contenu = contenu;
        this.categorie = categorie;
    }
}
