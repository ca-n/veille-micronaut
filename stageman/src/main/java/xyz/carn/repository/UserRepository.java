package xyz.carn.repository;

import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCourrielIgnoreCase(String courriel);
    Optional<User> findByCourrielIgnoreCaseAndPassword(String courriel, String password);
}
