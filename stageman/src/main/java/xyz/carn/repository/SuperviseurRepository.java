package xyz.carn.repository;

import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Superviseur;

import java.util.Optional;

public interface SuperviseurRepository extends JpaRepository<Superviseur, Long> {
    Optional<Superviseur> findByCourrielIgnoreCase(String courriel);
}
