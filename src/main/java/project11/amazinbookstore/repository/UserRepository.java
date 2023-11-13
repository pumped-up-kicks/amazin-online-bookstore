package project11.amazinbookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project11.amazinbookstore.model.RegisteredUser;

import java.util.Optional;

/**
 * The repository for tracking users.
 * @author Patrick Liu
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<RegisteredUser, Long> {
    Optional<RegisteredUser> findByUsername(String username);
}
