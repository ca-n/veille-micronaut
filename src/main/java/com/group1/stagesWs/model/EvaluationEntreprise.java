package com.group1.stagesWs.model;

import com.group1.stagesWs.SessionManager;
import lombok.Data;

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
    private char[] evaluationGrid;
    private String commentaires;
    private int stagePrefere;
    private int nombreStagiaires;
    private boolean garderStagiaire;
    private boolean variableShifts;
    private LocalDate dateCreation;
    private String session;

    @OneToOne
    private Contrat contrat;

    @ManyToOne
    private Superviseur superviseur;

    public EvaluationEntreprise() {
        dateCreation = LocalDate.now();
        session = SessionManager.CURRENT_SESSION.getNomSession();
    }
}
