package com.group1.stagesWs.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class OffreContratBinding implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Offre offre;

    @OneToOne
    private Contrat contrat;

}
