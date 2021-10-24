package xyz.carn.model;

import lombok.Data;
import xyz.carn.model.type.UserType;

import javax.persistence.Entity;

@Data
@Entity
public class Moniteur extends User {
    private String nomEntreprise;
    private String addresseEntreprise;

    public Moniteur() {
        role = UserType.MONITEUR;
    }
}
