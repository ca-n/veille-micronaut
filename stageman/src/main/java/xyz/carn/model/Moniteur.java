package xyz.carn.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.carn.model.type.UserType;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Moniteur extends User {
    private String nomEntreprise;
    private String addresseEntreprise;

    public Moniteur() {
        super();
        role = UserType.MONITEUR;
    }
}
