package xyz.carn.model;

import lombok.Data;
import xyz.carn.SessionManager;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class EvaluationEntreprise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int numeroStage;
    private int[] evaluationGrid;
    private String commentaires;
    private int stagePrefere;
    private int nombreStagiaires;
    private boolean garderStagiaire;
    private boolean variableShifts;
    private double heuresEncadrementParSemaineMois1;
    private double heuresEncadrementParSemaineMois2;
    private double heuresEncadrementParSemaineMois3;
    private LocalDate dateCreation;
    private String session;

    @OneToOne
    private Contrat contrat;

    @ManyToOne
    private Superviseur superviseur;

    public EvaluationEntreprise() {
        this.dateCreation = LocalDate.now();
        this.evaluationGrid = new int[10];
        this.session = SessionManager.CURRENT_SESSION.getNomSession();
    }
}
