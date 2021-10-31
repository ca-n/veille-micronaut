package com.group1.stagesWs.model;


import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.enums.UserType;
import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class Moniteur extends User implements Serializable {

    private String nomEntreprise;
    private String adresseEntreprise;

    public Moniteur() {
        role = UserType.MONITEUR;
        session = SessionManager.CURRENT_SESSION;
    }

    public Moniteur(String prenom, String nom, String courriel, String password, String numTelephone, String nomEntreprise, String adresseEntreprise) {
        super(prenom, nom, courriel, password, numTelephone, UserType.MONITEUR, SessionManager.CURRENT_SESSION);
        this.nomEntreprise = nomEntreprise;
        this.adresseEntreprise = adresseEntreprise;
    }
}
