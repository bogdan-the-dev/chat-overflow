package ro.bogdan.chatoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.bogdan.chatoverflow.DTO.AnswerDTO;
import ro.bogdan.chatoverflow.service.AnswerService;

@Controller
@RequestMapping(value = "/answers")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    @ResponseBody
    private AnswerDTO createAnswer(@RequestBody AnswerDTO answerDTO) {
        return answerService.saveAnswer(answerDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find-answer")
    @ResponseBody
    private AnswerDTO findAnswerById(@RequestParam(name = "id") int id) {
        return answerService.getAnswerDTOById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/edit")
    @ResponseBody
    private AnswerDTO updateAnswer(@RequestBody AnswerDTO answerDTO) {
        return answerService.updateAnswer(answerDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    @ResponseBody
    private String deleteAnswer(@RequestParam("id") int id) {
        try {
            answerService.deleteAnswer(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Answer deleted";
    }

}
