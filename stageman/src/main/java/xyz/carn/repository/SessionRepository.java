package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
}
