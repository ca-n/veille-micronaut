package xyz.carn.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.carn.model.enums.UserType;

import javax.persistence.Entity;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Gestionnaire extends User implements Serializable {

    private String departement;

    public Gestionnaire() {
        role = UserType.GESTIONNAIRE;
    }

    public Gestionnaire(
            String prenom,
            String nom,
            String courriel,
            String password,
            String numTelephone,
            String departement) {
        super(prenom, nom, courriel, password, numTelephone, UserType.GESTIONNAIRE);
        this.departement = departement;
    }
}
