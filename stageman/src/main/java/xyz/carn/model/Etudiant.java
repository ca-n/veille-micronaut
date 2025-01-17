package xyz.carn.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.carn.model.type.UserType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Etudiant extends User {
    private String programme;
    private String addresse;
    private String nomMatricule;

    private boolean hasLicense;
    private boolean hasVoiture;

    public Etudiant() {
        super();
        role = UserType.ETUDIANT;
    }
}
