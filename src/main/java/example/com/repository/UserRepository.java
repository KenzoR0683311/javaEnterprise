package example.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import example.com.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

