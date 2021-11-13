package com.group1.stagesWs.model;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.enums.UserType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Superviseur extends User implements Serializable {

    private String departement;
    private String specialite;

    public Superviseur() {
        role = UserType.SUPERVISEUR;
//        session = SessionManager.CURRENT_SESSION;
    }

    public Superviseur(String prenom, String nom, String courriel, String password, String numTelephone, String departement, String specialite) {
        super(prenom, nom, courriel, password, numTelephone, UserType.SUPERVISEUR);
        this.departement = departement;
        this.specialite = specialite;
    }
}
