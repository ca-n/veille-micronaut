package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Moniteur;

import java.util.Optional;

@Repository
public interface MoniteurRepository extends JpaRepository<Moniteur, Integer> {
    Optional<Moniteur> findByCourrielIgnoreCase(String email);
    Optional<Moniteur> findByCourrielIgnoreCaseAndPassword(String email, String password);
}
