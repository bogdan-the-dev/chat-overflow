package ro.bogdan.chatoverflow.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.bogdan.chatoverflow.model.Answer;
import ro.bogdan.chatoverflow.model.Question;

@Repository
public interface IAnswerRepository extends CrudRepository<Answer, Integer> {

    Iterable<Answer> findAnswersByQuestionIdIs(Integer id);


}
