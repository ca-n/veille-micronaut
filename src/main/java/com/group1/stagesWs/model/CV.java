package com.group1.stagesWs.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class CV implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dateSoumission;
    private boolean valid;


    @Lob
    private byte[] data;

    public CV() {
        this.dateSoumission = LocalDate.now();
        valid = false;
    }
}
