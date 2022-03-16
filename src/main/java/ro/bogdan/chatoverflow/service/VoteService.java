package ro.bogdan.chatoverflow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.DTO.VoteDTO;
import ro.bogdan.chatoverflow.model.Answer;
import ro.bogdan.chatoverflow.model.Question;
import ro.bogdan.chatoverflow.model.User;
import ro.bogdan.chatoverflow.model.Vote;
import ro.bogdan.chatoverflow.repository.IVoteRepository;

import java.util.List;

@Service
public class VoteService {
    @Autowired
    private IVoteRepository iVoteRepository;
    @Autowired
    private UserService userService;

    private final AnswerService answerService;

    private final QuestionService questionService;

    @Autowired
    public VoteService(@Lazy AnswerService answerService, @Lazy QuestionService questionService){
        this.answerService = answerService;
        this.questionService = questionService;
    }


    private static final ModelMapper modelMapper = new ModelMapper();

    public void addVote(VoteDTO voteDTO) {
        User user = userService.getUserByUsername(voteDTO.getUsername());
        Vote vote = convertFromDTO(voteDTO);
        if (voteDTO.getQuestionId() != null) {
            Question question = vote.getQuestion();
            if (!hasUserAlreadyVotedQuestion(question.getQuestionId(), user.getUserId())) {
                if (vote.getScore() == 1)
                    addQuestionUpVote(vote);
                if (voteDTO.getScore() == -1) {
                    addQuestionDownVote(vote);
                }
            }
        } else {
            Answer answer = vote.getAnswer();
            if (!hasUserAlreadyVotedAnswer(answer.getId(), user.getUserId())) {
                if (vote.getScore() == 1) {
                    addAnswerUpVote(vote);
                }
                if (vote.getScore() == -1) {
                    addAnswerDownVote(vote, voteDTO.getUsername());
                }
            }
        }
    }

    public void changeVote(VoteDTO voteDTO) {
        User user = userService.getUserByUsername(voteDTO.getUsername());
        Vote vote = convertFromDTO(voteDTO);
        if (voteDTO.getQuestionId() != null) {
            Question question = vote.getQuestion();
            if (hasUserAlreadyVotedQuestion(question.getQuestionId(), user.getUserId())) {
                Vote changeVote = this.iVoteRepository.getVoteByQuestionQuestionIdAndUserUserId(vote.getQuestion().getQuestionId(), user.getUserId()).get();
                if (changeVote.getScore() != vote.getScore())
                    changeQuestionVote(changeVote, voteDTO.getScore());
            }
        } else {
            Answer answer = vote.getAnswer();
            if (hasUserAlreadyVotedAnswer(answer.getId(), user.getUserId())) {
                Vote changeVote = this.iVoteRepository.getVoteByAnswerIdAndUserUserId(answer.getId(), user.getUserId()).get();
                if (changeVote.getScore() != vote.getScore()) {
                    changeAnswerVote(changeVote, voteDTO.getScore(), voteDTO.getUsername());
                }
            }
        }
    }

    public void deleteVote(VoteDTO voteDTO) {
        User user = this.userService.getUserByUsername(voteDTO.getUsername());
        if(voteDTO.getQuestionId() != null) {
            Vote deletionVote = this.iVoteRepository.getVoteByQuestionQuestionIdAndUserUserId(voteDTO.getQuestionId(), user.getUserId()).orElse(null);
            deleteQuestionVote(deletionVote);
        } else {
            Vote deletionVote = this.iVoteRepository.getVoteByAnswerIdAndUserUserId(voteDTO.getAnswerId(), user.getUserId()).orElse(null);
            deleteAnswerVote(deletionVote, user.getUsername());
        }
    }

    private void deleteQuestionVote(Vote vote) {
        int score = vote.getScore();
        User votedUser = this.userService.getUserById(vote.getQuestion().getAuthor().getUserId());
        if(score == 1) {
            votedUser.downVote(5);
        } else if(score == -1) {
            votedUser.upVote(2);
        }
        this.userService.saveUser(votedUser);
        this.iVoteRepository.delete(vote);
    }

    private void deleteAnswerVote(Vote vote, String votingUsername) {
        int score = vote.getScore();
        User votedUser = this.userService.getUserById(vote.getQuestion().getAuthor().getUserId());
        if(score == 1) {
            votedUser.downVote(5);
        } else if(score == -1) {
            User votingUser = this.userService.getUserByUsername(votingUsername);
            votingUser.upVote(1);
            this.userService.saveUser(votingUser);
            votedUser.upVote(2);
        }
        this.userService.saveUser(votedUser);
        this.iVoteRepository.delete(vote);
    }

    private void addAnswerUpVote(Vote vote){
        this.iVoteRepository.save(vote);
        User votedUser = this.userService.getUserById(vote.getAnswer().getAuthor().getUserId());
        votedUser.upVote(10);
        this.userService.saveUser(votedUser);
    }

    private void addAnswerDownVote(Vote vote, String votingUsername){
        this.iVoteRepository.save(vote);
        User votedUser = this.userService.getUserById(vote.getAnswer().getAuthor().getUserId());
        User votingUser = this.userService.getUserByUsername(votingUsername);
        votedUser.downVote(2);
        votingUser.downVote(-1);
        this.userService.saveUser(votedUser);
        this.userService.saveUser(votingUser);
    }

    private void changeAnswerVote(Vote newVote, int newScore, String votingUsername){
        newVote.setScore(newScore);
        this.iVoteRepository.save(newVote);
        User votedUser = this.userService.getUserById(newVote.getAnswer().getAuthor().getUserId());
        if(newScore == 1) {
            votedUser.upVote(12);
            User votingUser = this.userService.getUserByUsername(votingUsername);
            votingUser.upVote(2);
            this.userService.saveUser(votingUser);
        }
        if(newScore == -1) {
            votedUser.downVote(12);
        }
        this.userService.saveUser(votedUser);
    }

    private void changeQuestionVote(Vote newVote, int newScore){
        newVote.setScore(newScore);
        this.iVoteRepository.save(newVote);
        User votedUser = this.userService.getUserById(newVote.getAnswer().getAuthor().getUserId());
        if(newScore == 1) {
            votedUser.upVote(7);
        }
        if(newScore == -1) {
            votedUser.downVote(-7);
        }
    }

    private void addQuestionUpVote(Vote vote) {
        this.iVoteRepository.save(vote);
        User votedUser = this.userService.getUserById(vote.getQuestion().getAuthor().getUserId());
        votedUser.upVote(5);
        this.userService.saveUser(votedUser);
    }

    private void addQuestionDownVote(Vote vote){
        this.iVoteRepository.save(vote);
        User votedUser = this.userService.getUserById(vote.getQuestion().getAuthor().getUserId());
        votedUser.downVote(2);
        this.userService.saveUser(votedUser);
    }


    public boolean hasUserAlreadyVotedQuestion(int questionId, int userId) {
        Vote vote = this.iVoteRepository.getVoteByQuestionQuestionIdAndUserUserId(questionId, userId).orElse(null);
        return vote != null;
    }

    private boolean hasUserAlreadyVotedAnswer(int answerId, int userId) {
        Vote vote = this.iVoteRepository.getVoteByAnswerIdAndUserUserId(answerId, userId).orElse(null);
        return vote != null;
    }

    public int getUpVoteForAnswer(int answerId) {
        return this.iVoteRepository.findAll().stream().filter(v -> v.getAnswer().getId() == answerId && v.getScore() == 1).toList().size();
    }

    public int getDownVoteForAnswer(int answerId) {
        return this.iVoteRepository.findAll().stream().filter(v -> v.getAnswer().getId() == answerId && v.getScore() == -1).toList().size();
    }

    public int getUpVoteForQuestion(int questionId) {
        return this.iVoteRepository.findAll().stream().filter(v -> v.getAnswer().getId() == questionId && v.getScore() == 1).toList().size();
    }

    public int getDownVoteForQuestion(int questionId) {
        return this.iVoteRepository.findAll().stream().filter(v -> v.getAnswer().getId() == questionId && v.getScore() == -1).toList().size();
    }

    private VoteDTO convertToDTO(Vote vote) {
        VoteDTO voteDTO = modelMapper.map(vote, VoteDTO.class);
        voteDTO.setUsername(vote.getUser().getUsername());
        voteDTO.setQuestionId(vote.getQuestion().getQuestionId());
        voteDTO.setAnswerId((vote.getAnswer().getId()));
        return voteDTO;
    }

    private Vote convertFromDTO(VoteDTO voteDTO) {
        Vote vote = modelMapper.map(voteDTO, Vote.class);
        vote.setUser((User) this.userService.getUserByUsername(voteDTO.getUsername()));
        vote.setQuestion(questionService.getQuestionById(voteDTO.getQuestionId()));
        vote.setAnswer(answerService.getAnswerById(voteDTO.getAnswerId()));
        return vote;
    }
}
