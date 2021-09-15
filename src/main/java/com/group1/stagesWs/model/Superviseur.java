package com.group1.stagesWs.model;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class Superviseur extends User implements Serializable {

    private String programme;
    private String specialite;


    public Superviseur(){
        super();
    }

    public Superviseur(String prenom, String nom, String courriel, String password, String numTelephone, String programme, String specialite) {
        super(prenom, nom, courriel, password, numTelephone);
        this.programme = programme;
        this.specialite = specialite;
    }
}
