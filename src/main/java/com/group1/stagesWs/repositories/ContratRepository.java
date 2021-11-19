package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Contrat;
import com.group1.stagesWs.model.Etudiant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {
    List<Contrat> findAllByMoniteurCourrielIgnoreCase(String moniteurCourriel);

    
    Contrat findContratByEtudiant(Etudiant etudiant);


    List<Contrat> findAllByEtudiantSuperviseurCourrielIgnoreCase(String superviseurCourriel);
}
