package com.group1.stagesWs.model;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.enums.Session;
import com.group1.stagesWs.enums.UserType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Entrevue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;
    private Date date;

    private Session session;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Moniteur moniteur;

    public Entrevue() {
        session = SessionManager.CURRENT_SESSION;
    }



}
