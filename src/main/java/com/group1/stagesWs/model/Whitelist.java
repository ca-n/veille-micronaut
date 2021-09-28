package com.group1.stagesWs.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Whitelist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    @Column(name = "visibilite_etudiant") //Cette ligne ne veut pas renommer la colonne dans la base de donnees - A REVOIR
    private Set<Etudiant> whitelistedEtudiant;

    public Whitelist() {}

    public Whitelist(Set<Etudiant> whitelisted) {
        whitelistedEtudiant = whitelisted;
    }
}
