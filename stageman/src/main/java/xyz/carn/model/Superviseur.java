package xyz.carn.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.carn.model.type.UserType;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Superviseur extends User {
    private String departement;
    private String specialite;

    public Superviseur() {
        role = UserType.SUPERVISEUR;
    }
}
