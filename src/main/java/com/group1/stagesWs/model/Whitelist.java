package com.group1.stagesWs.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Whitelist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private Set<Etudiant> whitelistedEtudiant;

    public Whitelist() {
        whitelistedEtudiant = new HashSet<>();
    }
}
