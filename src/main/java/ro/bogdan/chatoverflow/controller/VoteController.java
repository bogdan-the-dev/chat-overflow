package ro.bogdan.chatoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.bogdan.chatoverflow.DTO.VoteDTO;
import ro.bogdan.chatoverflow.service.VoteService;

@Controller
@RequestMapping("/vote")
@CrossOrigin()
public class VoteController {

    @Autowired
    private VoteService voteService;

    @RequestMapping(value = "/add-vote", method = RequestMethod.POST)
    private void addVote(@RequestBody VoteDTO voteDTO) {
        this.voteService.addVote(voteDTO);
    }

    @RequestMapping(value = "/change-vote", method = RequestMethod.PUT)
    private void chaneVote(@RequestBody VoteDTO voteDTO) {
        this.voteService.changeVote(voteDTO);
    }

    @RequestMapping(value = "/delete-vote", method = RequestMethod.DELETE)
    private void deleteVote(@RequestBody VoteDTO voteDTO) {
        this.voteService.deleteVote(voteDTO);
    }

}
