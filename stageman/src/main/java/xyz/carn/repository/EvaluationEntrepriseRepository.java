package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.EvaluationEntreprise;

import java.util.List;

@Repository
public interface EvaluationEntrepriseRepository extends JpaRepository<EvaluationEntreprise, Integer> {
    List<EvaluationEntreprise> findAllBySession(String session);
}
