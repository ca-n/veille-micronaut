package xyz.carn.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.carn.model.type.UserType;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Gestionnaire extends User {
    private String departement;

    public Gestionnaire() {
        super();
        role = UserType.GESTIONNAIRE;
    }
}
