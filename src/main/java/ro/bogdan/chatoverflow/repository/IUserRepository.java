package ro.bogdan.chatoverflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.bogdan.chatoverflow.model.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsernameContainingIgnoreCase(String username);
    Optional<User> findUserByEmailIsIgnoreCase(String email);
    Optional<User> findUserByUsernameIs(String username);
}
