package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Gestionnaire;
import xyz.carn.model.Moniteur;

import java.util.Optional;

@Repository
public interface GestionnaireRepository extends JpaRepository<Gestionnaire, Integer> {
    Optional<Gestionnaire> findFirst();
    Optional<Gestionnaire> findByCourrielIgnoreCase(String email);
    Optional<Gestionnaire> findByCourrielIgnoreCaseAndPassword(String email, String password);
}
