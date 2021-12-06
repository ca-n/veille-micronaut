package xyz.carn.model;

import lombok.Data;
import xyz.carn.SessionManager;
import xyz.carn.model.enums.UserType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    protected String session;

    @OneToMany
    protected List<Notification> notifications = new ArrayList<>();

    public User() {
        dateCreation = LocalDate.now();
        this.session = SessionManager.CURRENT_SESSION.getNomSession();
    }

    public User(
            String prenom,
            String nom,
            String courriel,
            String password,
            String numTelephone,
            UserType role) {
        this.prenom = prenom;
        this.nom = nom;
        this.courriel = courriel;
        this.password = password;
        this.numTelephone = numTelephone;
        this.isVerifier = false;
        this.dateCreation = LocalDate.now();
        this.role = role;
        this.session = SessionManager.CURRENT_SESSION.getNomSession();
    }
}