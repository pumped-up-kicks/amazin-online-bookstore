package project11.amazinbookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project11.amazinbookstore.model.RegisteredUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<RegisteredUser, Integer> {
    Optional<RegisteredUser> findByUsername(String username);
}
