package xyz.carn.model;

import lombok.Data;
import xyz.carn.SessionManager;
import xyz.carn.model.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Entrevue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;
    private LocalDate date;
    private LocalTime time;
    private String nomEntreprise;

    private Status status;

    private String session;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Moniteur moniteur;

    public Entrevue() {
        session = SessionManager.CURRENT_SESSION.getNomSession();
        this.status = Status.PENDING;
    }

    public Entrevue(String titre, LocalDate date, LocalTime time,
                    String nomEntreprise, Etudiant etudiant, Moniteur moniteur) {
        this.titre = titre;
        this.date = date;
        this.time = time;
        this.nomEntreprise = nomEntreprise;
        this.etudiant = etudiant;
        this.moniteur = moniteur;
    }
}
