package projects.notification_services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projects.notification_services.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
