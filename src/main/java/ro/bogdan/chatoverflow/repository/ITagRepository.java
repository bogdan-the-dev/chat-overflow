package ro.bogdan.chatoverflow.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.bogdan.chatoverflow.model.Tag;

import java.util.Optional;

@Repository
public interface ITagRepository extends CrudRepository<Tag, Integer> {

    boolean existsTagByName(String name);

    Optional<Tag> findTagByName(String name);
}
