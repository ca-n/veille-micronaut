package xyz.carn.repository;

import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Moniteur;

import java.util.Optional;

public interface MoniteurRepository extends JpaRepository<Moniteur, Long> {
    Optional<Moniteur> findByCourrielIgnoreCase(String courriel);
}
