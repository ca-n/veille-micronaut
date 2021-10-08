package com.group1.stagesWs.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

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

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToOne
    private Whitelist visibiliteEtudiant;

    public Offre() {
        visibiliteEtudiant = new Whitelist();
    }

    public Offre(String titre, String description, String entreprise, boolean isValid) {
        this.titre = titre;
        this.description = description;
        this.entreprise = entreprise;
        this.isValid = isValid;
        visibiliteEtudiant = new Whitelist();
    }
}
