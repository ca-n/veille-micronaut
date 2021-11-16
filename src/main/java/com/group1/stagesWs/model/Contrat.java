package com.group1.stagesWs.model;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.model.Session;
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

    private String session;

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
        this.session = SessionManager.CURRENT_SESSION.getNomSession();
    }

    public Contrat(String collegeEngagement, String entrepriseEngagement, String etudiantEngagement, Offre offre, Etudiant etudiant, Moniteur moniteur) {
        this.dateCreation = LocalDate.now();
        this.isEtudiantConfirmed = false;
        this.isGestionnaireConfirmed = false;
        this.isMoniteurConfirmed = false;
        this.session = SessionManager.CURRENT_SESSION;
        this.collegeEngagement = collegeEngagement;
        this.entrepriseEngagement = entrepriseEngagement;
        this.etudiantEngagement = etudiantEngagement;
        this.offre = offre;
        this.etudiant = etudiant;
        this.moniteur = moniteur;
    }
}
