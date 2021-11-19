package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Contrat;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Moniteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {
    
    Contrat findContratByEtudiant(Etudiant etudiant);

    List<Contrat> findAllByMoniteurCourrielIgnoreCase(String moniteurCourriel);

    List<Contrat> findAllByEtudiantSuperviseurCourrielIgnoreCase(String superviseurCourriel);
}
