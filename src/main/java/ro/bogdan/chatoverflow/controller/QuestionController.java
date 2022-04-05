package ro.bogdan.chatoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.bogdan.chatoverflow.DTO.QuestionDTO;
import ro.bogdan.chatoverflow.service.QuestionService;

import java.util.List;

@Controller
@RequestMapping(value = "/questions")
@CrossOrigin()
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(method = RequestMethod.GET, value = "/all-questions")
    @ResponseBody
    private List<QuestionDTO> getQuestions() {
        return questionService.getQuestions();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    private List<QuestionDTO> findQuestionsByName(@RequestParam(name = "name") String name) {
        return questionService.getQuestionsByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find-question")
    @ResponseBody
    private QuestionDTO getQuestionById(@RequestParam(name = "id") Integer id) {
        return questionService.getQuestionDTOById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete-question")
    @ResponseBody
    private String deleteQuestion(@RequestParam(name = "id") Integer id) {
        try {
            questionService.deleteQuestion(id);

        } catch (Exception e) {

            return e.getMessage();
        }
        return "The delete was successful";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create-question")
    @ResponseBody
    private QuestionDTO createQuestion(@RequestBody QuestionDTO questionDTO) {
        return questionService.saveQuestion(questionDTO);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/save-question")
    @ResponseBody
    private QuestionDTO updateQuestion(@RequestBody QuestionDTO questionDTO) {
        return questionService.updateQuestion(questionDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find-by-tag")
    @ResponseBody
    private List<QuestionDTO> getQuestionsByTag(@RequestParam(name = "tag") String name) {
        return questionService.getQuestionsByTag(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find-by-user")
    @ResponseBody
    private List<QuestionDTO> getQuestionsByUser(@RequestParam(name = "username") String username) {
        return questionService.getQuestionsByUser(username);
    }
}
