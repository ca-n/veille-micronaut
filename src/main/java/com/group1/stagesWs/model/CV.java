package com.group1.stagesWs.model;


import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.enums.Status;
import com.group1.stagesWs.enums.Session;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class CV implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    private LocalDate dateSoumission;
    private Status status;
    private Session session;

    @Lob
    private byte[] data;

    @ManyToOne
    private Etudiant etudiant;

    public CV() {
        this.dateSoumission = LocalDate.now();
        this.status = Status.PENDING;
        this.session = SessionManager.CURRENT_SESSION;
    }
}
