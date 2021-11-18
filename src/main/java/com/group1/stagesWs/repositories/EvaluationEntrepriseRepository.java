package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.EvaluationEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationEntrepriseRepository extends JpaRepository<EvaluationEntreprise, Integer> {
    List<EvaluationEntreprise> findAllBySession(String session);
}
