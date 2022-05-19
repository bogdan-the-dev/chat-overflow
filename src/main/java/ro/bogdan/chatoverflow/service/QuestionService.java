package ro.bogdan.chatoverflow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.DTO.QuestionDTO;
import ro.bogdan.chatoverflow.model.Question;
import ro.bogdan.chatoverflow.model.User;
import ro.bogdan.chatoverflow.repository.IQuestionRepository;

import java.util.*;

@Service
public class QuestionService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private IQuestionRepository iQuestionRepository;
    @Autowired
    private TagItemService tagItemService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserService userService;
    @Autowired
    private VoteService voteService;

    public List<QuestionDTO> getQuestions() {
        List<Question> questions = (List<Question>) iQuestionRepository.findAllByOrderByCreationDateDesc();
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            questionDTOS.add(covertToDTO(question));
        }
        return questionDTOS;
    }

    public Question getQuestionById(int question_id) {
        return this.iQuestionRepository.findById(question_id).orElse(null);
    }

    public List<QuestionDTO> getQuestionsByName(String name) {
        List<Question> questions = (List<Question>) iQuestionRepository.findQuestionsByTitleContainingIgnoreCaseOrderByCreationDateDesc(name);
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            questionDTOS.add(covertToDTO(question));
        }
        return questionDTOS;
    }

    public QuestionDTO getQuestionDTOById(Integer id) {
        return covertToDTO(getQuestionById(id));
    }

    private Question getQuestionById(Integer id) {
        Optional<Question> question = iQuestionRepository.findById(id);
        return question.orElse(null);
    }

    public void deleteQuestion(Integer id) throws Exception {
        try {
            // tagItemService.deleteAllTagsFromQuestion(id);
            iQuestionRepository.delete(this.getQuestionById(id));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Delete failed, question not found");
        }
    }

    public List<QuestionDTO> getQuestionsByUser(String username) {
        User user = userService.getUserByUsername(username);
        List<Question> questions = (List<Question>) iQuestionRepository.findAllByAuthorUserIdIsOrderByCreationDateDesc(user.getUserId());
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
        for(Question question: questions) {
            questionDTOS.add(covertToDTO(question));
        }
        return questionDTOS;
    }

    public QuestionDTO saveQuestion(QuestionDTO questionDTO) {
        Question question = iQuestionRepository.save(getQuestionFromDTO(questionDTO));
        tagItemService.createAndSaveTagItems((ArrayList<String>) questionDTO.getTags(), question);
        return covertToDTO(question);
    }

    public void saveQuestion(Question question) {
        this.iQuestionRepository.save(question);
    }

    public List<QuestionDTO> getQuestionsByTag(String tag) {
        List<Question> questions = tagItemService.getQuestionsByTag(tag);
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            questionDTOS.add(covertToDTO(question));
        }
        Collections.sort(questionDTOS);
        return questionDTOS;
    }

    public QuestionDTO updateQuestion(QuestionDTO questionDTO) {
        Question questionFromDTO = getQuestionFromDTO(questionDTO);
        Question initialQuestion = iQuestionRepository.findById(questionFromDTO.getQuestionId()).orElse(null);
        if (initialQuestion != null) {
            initialQuestion.setText(questionDTO.getText());
            initialQuestion.setTitle(questionDTO.getTitle());
        }
        tagItemService.updateTagItems(questionDTO.getTags(), questionFromDTO);
        this.saveQuestion(initialQuestion);
        return covertToDTO(iQuestionRepository.findById(questionDTO.getQuestionId()).get());
    }

    private QuestionDTO covertToDTO(Question question) {
        if (question != null) {
            QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
            modelMapper.map(question.getAuthor(), questionDTO);
            questionDTO.setUserScore(question.getAuthor().getScore());
            questionDTO.setTags(tagItemService.getTagsForQuestion(question.getQuestionId()));
            questionDTO.setAnswers(answerService.getAnswersByQuestionId(question.getQuestionId()));
            questionDTO.setUpVotes(voteService.getUpVoteForQuestion(question.getQuestionId()));
            questionDTO.setDownVotes(voteService.getDownVoteForQuestion(question.getQuestionId()));
            return questionDTO;
        }
        return null;
    }

    private Question getQuestionFromDTO(QuestionDTO questionDTO) {
        if (questionDTO != null) {
            Question question = modelMapper.map(questionDTO, Question.class);
            question.setAuthor(userService.getUserByUsername(questionDTO.getUsername()));
            return question;
        }
        return null;
    }
}
