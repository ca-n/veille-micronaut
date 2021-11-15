package xyz.carn.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Etudiant;
import xyz.carn.model.Gestionnaire;
import xyz.carn.model.Moniteur;
import xyz.carn.model.Offre;

import java.util.List;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer> {
//    List<Offre> findAllByWhitelistContainsAndValidTrue(Etudiant etudiant);
    List<Offre> findAllByMoniteur(Moniteur moniteur);
}
