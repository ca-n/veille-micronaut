package xyz.carn.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import xyz.carn.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
