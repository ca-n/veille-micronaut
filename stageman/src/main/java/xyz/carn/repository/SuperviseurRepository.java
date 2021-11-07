package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Moniteur;
import xyz.carn.model.Superviseur;

import java.util.Optional;

@Repository
public interface SuperviseurRepository extends JpaRepository<Superviseur, Integer> {
    Optional<Superviseur> findByCourrielIgnoreCase(String email);
    Optional<Superviseur> findByCourrielIgnoreCaseAndPassword(String email, String password);
}
