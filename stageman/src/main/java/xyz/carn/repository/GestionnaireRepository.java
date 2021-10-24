package xyz.carn.repository;

import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Gestionnaire;

import java.util.Optional;

public interface GestionnaireRepository extends JpaRepository<Gestionnaire, Long> {
    Optional<Gestionnaire> findByCourrielIgnoreCase(String courriel);
}
