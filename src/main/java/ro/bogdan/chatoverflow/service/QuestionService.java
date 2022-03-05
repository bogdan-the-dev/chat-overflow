package ro.bogdan.chatoverflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.model.Question;
import ro.bogdan.chatoverflow.repository.IQuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private IQuestionRepository iQuestionRepository;

    public List<Question> getQuestions(){
        return (List<Question>) iQuestionRepository.findAll();
    }

    public List<Question> getQuestionsByName(String name){
        return (List<Question>) iQuestionRepository.findQuestionsByTitleContainingIgnoreCase(name);
    }

    public Question getQuestionById(Integer id){
        Optional<Question> question = iQuestionRepository.findById(id);
        return question.orElse(null);
    }

    public void deleteQuestion(Integer id) throws Exception {
        try {
            iQuestionRepository.delete(this.getQuestionById(id));
        }catch (Exception e){
            throw new Exception("Delete failed, question not found");
        }
    }

    public Question saveQuestion(Question question){
        return iQuestionRepository.save(question);
    }

}
