package xyz.carn.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
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
    private boolean isValid;
    private String addresse;
    private String dateDebut;
    private String dateFin;
    private int nbTotalSemaine;
    private String horaire;
    private double heuresParSemaine;
    private double tauxHoraire;

    @ManyToMany
    private Set<Etudiant> whitelist;

    public Offre() {
        whitelist = Set.of();
    }
}
