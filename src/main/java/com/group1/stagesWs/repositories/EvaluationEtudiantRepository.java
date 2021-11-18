package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.EvaluationEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationEtudiantRepository extends JpaRepository<EvaluationEtudiant, Integer> {
    List<EvaluationEtudiant> findAllBySession(String session);
}
