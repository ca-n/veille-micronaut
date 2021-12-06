package xyz.carn.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.carn.model.enums.UserType;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class Moniteur extends User implements Serializable {

    private String nomEntreprise;
    private String adresseEntreprise;

    public Moniteur() {
        role = UserType.MONITEUR;
    }

    public Moniteur(
            String prenom,
            String nom,
            String courriel,
            String password,
            String numTelephone,
            String nomEntreprise,
            String adresseEntreprise) {
        super(prenom, nom, courriel, password, numTelephone, UserType.MONITEUR);
        this.nomEntreprise = nomEntreprise;
        this.adresseEntreprise = adresseEntreprise;
    }
}
