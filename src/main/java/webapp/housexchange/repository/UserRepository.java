package webapp.housexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp.housexchange.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
