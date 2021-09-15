package com.group1.stagesWs.model;


import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class Etudiant extends User implements Serializable {

    private String programme;
    private String adresse;
    private String numMatricule;

    private boolean license;
    private boolean voiture;

    public Etudiant(){

    }

    public Etudiant(String prenom, String nom, String courriel, String password, String numTelephone, String programme, String adresse, String numMatricule, boolean license, boolean voiture) {
        super(prenom, nom, courriel, password, numTelephone);
        this.programme = programme;
        this.adresse = adresse;
        this.numMatricule = numMatricule;
        this.license = license;
        this.voiture = voiture;
    }
}
