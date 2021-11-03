package com.group1.stagesWs.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Offre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;
    private String description;
    private String entreprise;
    private boolean isValid;
    private String adresse;
    private String dateDebut;
    private String dateFin;
    private int nbTotalSemaine;
    private String horaire;
    private double nbTotalHeuresParSemaine;
    private double tauxHoraire;


    @ManyToOne
    private Gestionnaire gestionnaire;

    @ManyToMany
    private Set<Etudiant> whitelist;

    @ManyToMany
    private Set<Etudiant> applicants;

    public Offre() {
        whitelist = new HashSet<>();
        applicants = new HashSet<>();
    }

    public Offre(String titre, String description, String entreprise, boolean isValid, String adresse, String dateDebut, String dateFin, int nbTotalSemaine, String horaire, double nbTotalHeuresParSemaine, double tauxHoraire) {
        this.titre = titre;
        this.description = description;
        this.entreprise = entreprise;
        this.isValid = isValid;
        this.adresse = adresse;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbTotalSemaine = nbTotalSemaine;
        this.horaire = horaire;
        this.nbTotalHeuresParSemaine = nbTotalHeuresParSemaine;
        this.tauxHoraire = tauxHoraire;
        whitelist = new HashSet<>();
        applicants = new HashSet<>();
    }
}
