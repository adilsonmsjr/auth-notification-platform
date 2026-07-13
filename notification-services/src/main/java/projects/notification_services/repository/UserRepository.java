package projects.notification_services.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import projects.notification_services.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<UserDetails> findByEmail(String email);

}
