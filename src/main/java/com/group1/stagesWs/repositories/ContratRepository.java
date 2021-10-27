package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Contrat;
import com.group1.stagesWs.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {

    //Contrat findContratByEtudiant(Etudiant etudiant);
}
