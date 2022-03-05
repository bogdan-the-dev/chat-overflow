package ro.bogdan.chatoverflow.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.bogdan.chatoverflow.model.Tag;

@Repository
public interface ITagRepository extends CrudRepository<Tag, Integer> {
}
