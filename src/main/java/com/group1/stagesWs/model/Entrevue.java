package com.group1.stagesWs.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Entrevue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;
    private Date date;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Moniteur moniteur;

}
