package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Offre;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer> {
}
