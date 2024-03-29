package ro.bogdan.chatoverflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.bogdan.chatoverflow.DTO.QuestionDTO;
import ro.bogdan.chatoverflow.model.Question;

import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface IQuestionRepository extends JpaRepository<Question, Integer> {

    Iterable<Question> findQuestionsByTitleContainingIgnoreCaseOrderByCreationDateDesc(String name);

    Iterable<Question> findAllByOrderByCreationDateDesc();

    Iterable<Question> findAllByAuthorUserIdIsOrderByCreationDateDesc(int userId);
}
