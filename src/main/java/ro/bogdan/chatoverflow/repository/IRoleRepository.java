package ro.bogdan.chatoverflow.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.bogdan.chatoverflow.model.Role;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Integer> {
}
