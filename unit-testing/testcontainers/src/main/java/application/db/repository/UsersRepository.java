package application.db.repository;

import application.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
