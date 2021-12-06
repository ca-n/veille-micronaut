package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Contrat;

import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {
    List<Contrat> findAllBySession(String session);
    List<Contrat> findAllByMoniteurCourrielIgnoreCaseAndSession(String courriel, String session);
    List<Contrat> findAllByEtudiantCourrielIgnoreCaseAndSession(String courriel, String session);
    List<Contrat> findAllByEtudiantSuperviseurCourrielIgnoreCaseAndSession(String courriel, String session);
}
