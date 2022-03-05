package ro.bogdan.chatoverflow.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.bogdan.chatoverflow.model.Question;

import java.util.List;

@Repository
public interface IQuestionRepository extends CrudRepository<Question, Integer> {


    Iterable<Question> findQuestionsByTitleContainingIgnoreCase(String name);
}
