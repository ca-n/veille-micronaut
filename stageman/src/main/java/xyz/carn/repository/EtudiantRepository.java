package xyz.carn.repository;

import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Etudiant;

import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findByCourrielIgnoreCase(String courriel);
}
