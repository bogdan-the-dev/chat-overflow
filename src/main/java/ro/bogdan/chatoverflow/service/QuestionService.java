package ro.bogdan.chatoverflow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.DTO.QuestionDTO;
import ro.bogdan.chatoverflow.model.Question;
import ro.bogdan.chatoverflow.model.User;
import ro.bogdan.chatoverflow.repository.IQuestionRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public List<QuestionDTO> getQuestions(){
        List<Question> questions = (List<Question>) iQuestionRepository.findAllByOrderByCreationDateDesc();
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
        for(Question question: questions){
            questionDTOS.add(covertToDTO(question));
        }
        return questionDTOS;
    }

    public List<QuestionDTO> getQuestionsByName(String name){
        List<Question> questions = (List<Question>)iQuestionRepository.findQuestionsByTitleContainingIgnoreCaseOrderByCreationDateDesc(name);
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
        for(Question question: questions){
            questionDTOS.add(covertToDTO(question));
        }
        return questionDTOS;
    }

    public QuestionDTO getQuestionDTOById(Integer id){
        return covertToDTO(getQuestionById(id));
    }

    private Question getQuestionById(Integer id){
        Optional<Question> question = iQuestionRepository.findById(id);
        return question.orElse(null);
    }

    public void deleteQuestion(Integer id) throws Exception {
        try {
            tagItemService.deleteAllTagsFromQuestion(id);
            iQuestionRepository.delete(this.getQuestionById(id));
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Delete failed, question not found");
        }
    }

    public QuestionDTO saveQuestion(QuestionDTO questionDTO){
        Question question = iQuestionRepository.save(getQuestionFromDTO(questionDTO));
        tagItemService.createAndSaveTagItems((ArrayList<String>) questionDTO.getTags(), question);
        return covertToDTO(question);
    }

    public List<QuestionDTO> getQuestionsByTag(String tag) {
        List<Question> questions = tagItemService.getQuestionsByTag(tag);
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
        for(Question question: questions) {
            questionDTOS.add(covertToDTO(question));
        }
        Collections.sort(questionDTOS);
        return questionDTOS;
    }

    public QuestionDTO updateQuestion(QuestionDTO questionDTO) {
        Question questionFromDTO = getQuestionFromDTO(questionDTO);
        Question initialQuestion = iQuestionRepository.findById(questionFromDTO.getQuestionId()).orElse(null);
        if(initialQuestion != null) {
            initialQuestion.setText(questionDTO.getText());
            initialQuestion.setTitle(questionDTO.getTitle());
        }
        tagItemService.updateTagItems(questionDTO.getTags(), questionFromDTO);
        return covertToDTO(iQuestionRepository.findById(questionDTO.getQuestionId()).get());
    }

    private QuestionDTO covertToDTO(Question question){
        if (question != null){
            QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
            modelMapper.map(question.getAuthor(), questionDTO);
            questionDTO.setTags(tagItemService.getTagsForQuestion(question.getQuestionId()));
            questionDTO.setAnswers(answerService.getAnswersByQuestionId(question.getQuestionId()));
            return questionDTO;
        }
       return null;
    }

    private Question getQuestionFromDTO(QuestionDTO questionDTO){
        if(questionDTO != null){
            Question question = modelMapper.map(questionDTO, Question.class);
            question.setAuthor(userService.getUserByUsername(questionDTO.getUsername()).get(0));
            return question;
        }
        return null;
    }
}
