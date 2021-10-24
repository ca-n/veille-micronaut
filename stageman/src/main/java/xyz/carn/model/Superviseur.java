package xyz.carn.model;

import lombok.Data;
import xyz.carn.model.type.UserType;

import javax.persistence.Entity;

@Data
@Entity
public class Superviseur extends User {
    private String departement;
    private String specialite;

    public Superviseur() {
        role = UserType.SUPERVISEUR;
    }
}
