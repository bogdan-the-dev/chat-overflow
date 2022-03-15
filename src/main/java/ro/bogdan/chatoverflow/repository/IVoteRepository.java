package ro.bogdan.chatoverflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.bogdan.chatoverflow.model.Vote;

@Repository
public interface IVoteRepository extends JpaRepository<Vote, Integer> {

}
