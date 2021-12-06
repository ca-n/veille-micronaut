package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Etudiant;
import xyz.carn.model.Moniteur;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    Optional<Etudiant> findByCourrielIgnoreCase(String email);
    Optional<Etudiant> findByCourrielIgnoreCaseAndPassword(String email, String password);
    List<Etudiant> findAllBySession(String session);
    List<Etudiant> findAllBySuperviseurIsNull();
    List<Etudiant> findAllBySuperviseurId(int id);
}
