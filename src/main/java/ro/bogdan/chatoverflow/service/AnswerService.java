package ro.bogdan.chatoverflow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.DTO.AnswerDTO;
import ro.bogdan.chatoverflow.DTO.QuestionDTO;
import ro.bogdan.chatoverflow.model.Answer;
import ro.bogdan.chatoverflow.model.User;
import ro.bogdan.chatoverflow.repository.IAnswerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private IAnswerRepository iAnswerRepository;
    @Autowired
    private UserService userService;

    private VoteService voteService;

    @Autowired
    public void setVoteService(VoteService voteService) {
        this.voteService = voteService;
    }

    private final ModelMapper modelMapper = new ModelMapper();

    public Answer getAnswerById(int id) {
        return this.iAnswerRepository.findById(id).orElse(null);
    }

    public List<AnswerDTO> getAnswersByQuestionId(int id){
        List<Answer> answers = (List<Answer>) this.iAnswerRepository.findAnswersByQuestionIdIs(id);
        ArrayList<AnswerDTO> answerDTOS = new ArrayList<>();
        for(Answer answer: answers){
            answerDTOS.add(convertToAnswerDTO(answer));
        }
        Collections.sort(answerDTOS);
        return answerDTOS;
    }

    public AnswerDTO saveAnswer(AnswerDTO answerDTO) {
        Answer answer = getAnswerFromAnswerDTO(answerDTO);
        this.iAnswerRepository.save(answer);
        return convertToAnswerDTO(answer);
    }

    public AnswerDTO getAnswerDTOById(int id) {
        return convertToAnswerDTO(this.iAnswerRepository.findById(id).orElse(null));
    }

    public AnswerDTO updateAnswer(AnswerDTO answerDTO) {
        Answer editedAnswer = getAnswerFromAnswerDTO(answerDTO);
        Answer originalAnswer = this.iAnswerRepository.findById(editedAnswer.getId()).orElse(null);
        if(originalAnswer != null) {
            originalAnswer.setAnswerBody(editedAnswer.getAnswerBody());
            originalAnswer.setEdited(true);
            this.iAnswerRepository.save(originalAnswer);
        }
        return convertToAnswerDTO(originalAnswer);
    }

    public void deleteAnswer(int id) throws Exception{
        try {
            iAnswerRepository.delete(this.getAnswerById(id));
        } catch (Exception e) {
            throw new Exception("Delete failed, answer not found");
        }
    }

    private AnswerDTO convertToAnswerDTO(Answer answer){
        if(answer != null){
            AnswerDTO answerDTO = modelMapper.map(answer, AnswerDTO.class);
            answerDTO.setUsername(answer.getAuthor().getUsername());
            answerDTO.setUserScore(answer.getAuthor().getScore());
            answerDTO.setUpVotes(voteService.getUpVoteForAnswer(answer.getId()));
            answerDTO.setDownVotes(voteService.getDownVoteForAnswer(answer.getId()));
            return  answerDTO;
        }
        return null;
    }

    private List<AnswerDTO> getAnswerDTOFromQuestionDTO(QuestionDTO questionDTO){
        return questionDTO.getAnswers();
    }

    private Answer getAnswerFromAnswerDTO(AnswerDTO answerDTO){
        if(answerDTO != null){
            Answer answer =  modelMapper.map(answerDTO, Answer.class);
            answer.setAuthor((User) userService.getUserByUsername(answerDTO.getUsername()));
            return  answer;
        }
        return null;
    }

}
