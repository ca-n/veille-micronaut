package com.group1.stagesWs.model;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class Superviseur extends User implements Serializable {

    private String departement;
    private String specialite;


    public Superviseur() {
        super();
    }

    public Superviseur(String prenom, String nom, String courriel, String password, String numTelephone, String departement, String specialite) {
        super(prenom, nom, courriel, password, numTelephone);
        this.departement = departement;
        this.specialite = specialite;
    }
}
