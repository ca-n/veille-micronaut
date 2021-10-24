package xyz.carn.model;

import lombok.Data;
import xyz.carn.model.type.UserType;

import javax.persistence.Entity;

@Data
@Entity
public class Gestionnaire extends User {
    private String departement;

    public Gestionnaire() {
        role = UserType.GESTIONNAIRE;
    }
}
