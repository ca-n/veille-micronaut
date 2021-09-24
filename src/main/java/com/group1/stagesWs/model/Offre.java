package com.group1.stagesWs.model;

import lombok.Data;

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

    @OneToOne
    private Whitelist visibiliteEtudiant;

    public Offre() {
    }

    public Offre(String titre, String description, String entreprise) {
        this.titre = titre;
        this.description = description;
        this.entreprise = entreprise;
    }
}
