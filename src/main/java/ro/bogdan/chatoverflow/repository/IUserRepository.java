package ro.bogdan.chatoverflow.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.bogdan.chatoverflow.model.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {
}
