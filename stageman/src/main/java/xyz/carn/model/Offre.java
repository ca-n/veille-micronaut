package xyz.carn.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Offre implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String titre;
    private String description;
    private String entreprise;
    private boolean valid;
    private String addresse;
    private String dateDebut;
    private String dateFin;
    private int nbTotalSemaine;
    private String horaire;
    private double heuresParSemaine;
    private double tauxHoraire;

    @ManyToMany
    private Set<Etudiant> whitelist;

    @ManyToOne
    private Moniteur moniteur;

    @ManyToOne
    private Gestionnaire gestionnaire;

    public Offre() {
        whitelist = new HashSet<>();
    }
}
