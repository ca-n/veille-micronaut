package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Entrevue;

import java.util.List;

@Repository
public interface EntrevueRepository extends JpaRepository<Entrevue, Integer> {
    List<Entrevue> findAllBySession(String session);
    List<Entrevue> findAllByEtudiantIdAndSession(int id, String session);
    List<Entrevue> findAllByMoniteurIdAndSession(int id, String session);
}
