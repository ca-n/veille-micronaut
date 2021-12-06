package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.CV;
import xyz.carn.model.Etudiant;

import java.util.List;

@Repository
public interface CVRepository extends JpaRepository<CV, Integer> {
    List<CV> findAllBySession(String session);
    List<CV> findAllByEtudiantIdAndSession(int id, String session);
    List<CV> findAllByDefaultCVTrue();
}
