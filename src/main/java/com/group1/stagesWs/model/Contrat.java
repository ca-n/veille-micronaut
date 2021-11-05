package com.group1.stagesWs.model;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.enums.Session;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Contrat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dateCreation;

    private boolean isMoniteurConfirmed;
    private boolean isEtudiantConfirmed;
    private boolean isGestionnaireConfirmed;

    private LocalDate dateSignatureMoniteur;
    private LocalDate dateSignatureEtudiant;
    private LocalDate dateSignatureGestionnaire;

    private String collegeEngagement;
    private String entrepriseEngagement;
    private String etudiantEngagement;

    private Session session;

    @ManyToOne
    private Offre offre;

    @OneToOne
    private Etudiant etudiant;

    @ManyToOne
    private Moniteur moniteur;

    public Contrat() {
        this.dateCreation = LocalDate.now();
        this.isEtudiantConfirmed = false;
        this.isGestionnaireConfirmed = false;
        this.isMoniteurConfirmed = false;
        this.session = SessionManager.CURRENT_SESSION;
    }
}
