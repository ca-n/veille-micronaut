package xyz.carn.model;

import lombok.Data;
import xyz.carn.model.type.UserType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class User implements Serializable {
    @Id
    @GeneratedValue
    protected int id;
    protected String prenom;
    protected String nom;
    @Column(unique = true)
    protected String courriel;
    protected String password;
    protected String numTelephone;
    protected UserType role;
    protected LocalDateTime dateCreation;

    public User() {
        dateCreation = LocalDateTime.now();
    }
}
