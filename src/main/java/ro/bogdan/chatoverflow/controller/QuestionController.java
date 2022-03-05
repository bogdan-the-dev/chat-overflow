package ro.bogdan.chatoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.bogdan.chatoverflow.model.Question;
import ro.bogdan.chatoverflow.service.QuestionService;

import java.util.List;

@Controller
@RequestMapping(value = "/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping(method = RequestMethod.GET, value = "/all-questions")
    @ResponseBody
    private List<Question> getQuestions(){
        return questionService.getQuestions();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    private List<Question> findQuestionsByName(@RequestParam(name = "name") String name){
        return questionService.getQuestionsByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find-question")
    @ResponseBody
    private Question getQuestionById(@RequestParam(name = "id") Integer id){
        return questionService.getQuestionById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete-question")
    @ResponseBody
    private String deleteQuestion(@RequestParam(name = "id") Integer id){
        try {
            questionService.deleteQuestion(id);

        }catch (Exception e){
            return e.getMessage();
        }
        return "The delete was successful";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create-question")
    @ResponseBody
    private Question createQuestion(@RequestBody Question question){
        return questionService.saveQuestion(question);
    }

}