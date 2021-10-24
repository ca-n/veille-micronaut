package xyz.carn.model;

import lombok.Data;
import xyz.carn.model.type.CVStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class CV implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    private LocalDate dateSoumission;
    private CVStatus status;

    @Lob
    private byte[] data;

    @ManyToOne
    private Etudiant etudiant;

    public CV() {
        this.dateSoumission = LocalDate.now();
        status = CVStatus.PENDING;
    }
}
