package com.group1.stagesWs.model;


import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.enums.UserType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Etudiant extends User implements Serializable {

    private String programme;
    private String adresse;
    private String numMatricule;

    private boolean hasLicense;
    private boolean hasVoiture;

    @ManyToOne
    private Superviseur superviseur;

    public Etudiant() {
        role = UserType.ETUDIANT;
        session = SessionManager.CURRENT_SESSION;
    }

    public Etudiant(String prenom, String nom, String courriel, String password, String numTelephone, String programme, String adresse, String numMatricule, boolean hasLicense, boolean hasVoiture) {
        super(prenom, nom, courriel, password, numTelephone, UserType.ETUDIANT);
        this.programme = programme;
        this.adresse = adresse;
        this.numMatricule = numMatricule;
        this.hasLicense = hasLicense;
        this.hasVoiture = hasVoiture;
    }


}
