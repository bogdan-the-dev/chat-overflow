package ro.bogdan.chatoverflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.bogdan.chatoverflow.model.Vote;

import java.util.Optional;

@Repository
public interface IVoteRepository extends JpaRepository<Vote, Integer> {

    Optional<Vote> getVoteByQuestionQuestionIdAndUserUserId(int questionId, int userId);

    Optional<Vote> getVoteByAnswerIdAndUserUserId(int answerId, int userId);

}
