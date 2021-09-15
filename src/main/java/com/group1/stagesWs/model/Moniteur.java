package com.group1.stagesWs.model;


import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class Moniteur extends User implements Serializable {

    private String nomEntreprise;
    private String adresseEntreprise;

    public Moniteur(){

    }

    public Moniteur(String prenom, String nom, String courriel, String password, String numTelephone, String nomEntreprise, String adresseEntreprise) {
        super(prenom, nom, courriel, password, numTelephone);
        this.nomEntreprise = nomEntreprise;
        this.adresseEntreprise = adresseEntreprise;
    }
}
