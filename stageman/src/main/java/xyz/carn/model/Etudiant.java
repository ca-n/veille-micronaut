package xyz.carn.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.carn.model.enums.UserType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
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
    }

    public Etudiant(
            String prenom,
            String nom,
            String courriel,
            String password,
            String numTelephone,
            String programme,
            String adresse,
            String numMatricule,
            boolean hasLicense,
            boolean hasVoiture) {
        super(prenom, nom, courriel, password, numTelephone, UserType.ETUDIANT);
        this.programme = programme;
        this.adresse = adresse;
        this.numMatricule = numMatricule;
        this.hasLicense = hasLicense;
        this.hasVoiture = hasVoiture;
    }
}
