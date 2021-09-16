package com.group1.stagesWs.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private LocalDate dateConfirmationMoniteur;
    private LocalDate dateConfirmationEtudiant;
    private LocalDate dateConfirmationGestionnaire;

    public Contrat() {
    }

    public Contrat(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        this.isEtudiantConfirmed = false;
        this.isGestionnaireConfirmed = false;
        this.isMoniteurConfirmed = false;
    }
}
