package com.group1.stagesWs.model;

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

    @ManyToOne
    private Offre offre;

    private String collegeSengageA;
    private String entrepriseSengageA;
    private String etudiantSengageA;

    public Contrat() {
    }

    public Contrat(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        this.isEtudiantConfirmed = false;
        this.isGestionnaireConfirmed = false;
        this.isMoniteurConfirmed = false;
    }
}
