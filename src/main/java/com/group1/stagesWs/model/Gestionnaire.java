package com.group1.stagesWs.model;


import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class Gestionnaire extends User implements Serializable {

    private String programme;


    public Gestionnaire(){

    }

    public Gestionnaire(String prenom, String nom, String courriel, String password, String numTelephone, String programme) {
        super(prenom, nom, courriel, password, numTelephone);
        this.programme = programme;
    }
}
