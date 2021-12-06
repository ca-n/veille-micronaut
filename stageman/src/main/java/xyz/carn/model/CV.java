package xyz.carn.model;

import lombok.Data;
import xyz.carn.SessionManager;
import xyz.carn.model.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class CV implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    private LocalDate dateSoumission;
    private Status status;
    private String session;
    private boolean defaultCV;

    @Lob
    private byte[] data;

    @ManyToOne
    private Etudiant etudiant;

    public CV() {
        this.dateSoumission = LocalDate.now();
        this.defaultCV = true;
        this.status = Status.PENDING;
        this.session = SessionManager.CURRENT_SESSION.getNomSession();
    }
}
