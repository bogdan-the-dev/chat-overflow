package ro.bogdan.chatoverflow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.DTO.VoteDTO;
import ro.bogdan.chatoverflow.model.User;
import ro.bogdan.chatoverflow.model.Vote;
import ro.bogdan.chatoverflow.repository.IVoteRepository;

@Service
public class VoteService {
    @Autowired
    private IVoteRepository iVoteRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnswerService answerService;

    private static final ModelMapper modelMapper = new ModelMapper();





    private VoteDTO convertToDTO(Vote vote) {
        VoteDTO voteDTO = modelMapper.map(vote, VoteDTO.class);
        voteDTO.setUsername(vote.getUser().getUsername());
        voteDTO.setQuestion_id(vote.getQuestion().getQuestionId());
        voteDTO.setAnswer_id((vote.getAnswer().getId()));
        return voteDTO;
    }

    private Vote convertFromDTO(VoteDTO voteDTO) {
        Vote vote = modelMapper.map(voteDTO, Vote.class);
        vote.setUser((User) this.userService.getUserByUsername(voteDTO.getUsername()));
        vote.setQuestion(questionService.getQuestionById(voteDTO.getQuestion_id()));
        vote.setAnswer(answerService.getAnswerById(voteDTO.getAnswer_id()));
        return vote;
    }
}
