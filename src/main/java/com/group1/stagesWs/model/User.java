package com.group1.stagesWs.model;

import com.group1.stagesWs.enums.Session;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String prenom;
    protected String nom;
    @Column(unique = true)
    protected String courriel;
    protected String password;
    protected String numTelephone;
    protected UserType role;

    protected boolean isVerifier;
    protected LocalDate dateCreation;
    protected Session session;


    public User() {
        dateCreation = LocalDate.now();
    }

    public User(String prenom, String nom, String courriel, String password, String numTelephone, UserType role, Session session) {
        this.prenom = prenom;
        this.nom = nom;
        this.courriel = courriel;
        this.password = password;
        this.numTelephone = numTelephone;
        this.isVerifier = false;
        this.dateCreation = LocalDate.now();
        this.role = role;
        this.session = session;
    }
}
