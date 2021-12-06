package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.EvaluationEtudiant;

import java.util.List;

@Repository
public interface EvaluationEtudiantRepository extends JpaRepository<EvaluationEtudiant, Integer> {
    List<EvaluationEtudiant> findAllBySession(String session);
}
