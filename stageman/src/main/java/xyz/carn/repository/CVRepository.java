package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.CV;

@Repository
public interface CVRepository extends JpaRepository<CV, Integer> {
}
