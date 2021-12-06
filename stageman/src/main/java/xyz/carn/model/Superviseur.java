package xyz.carn.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.carn.model.enums.UserType;

import javax.persistence.Entity;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Superviseur extends User implements Serializable {

    private String departement;
    private String specialite;

    public Superviseur() {
        role = UserType.SUPERVISEUR;
    }

    public Superviseur(
            String prenom,
            String nom,
            String courriel,
            String password,
            String numTelephone,
            String departement,
            String specialite) {
        super(prenom, nom, courriel, password, numTelephone, UserType.SUPERVISEUR);
        this.departement = departement;
        this.specialite = specialite;
    }
}
