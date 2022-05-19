package ro.bogdan.chatoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.bogdan.chatoverflow.DTO.VoteDTO;
import ro.bogdan.chatoverflow.service.VoteService;

@Controller
@RequestMapping("/vote")
@CrossOrigin()
public class VoteController {

    @Autowired
    private VoteService voteService;

    @RequestMapping(value = "/get-vote", method = RequestMethod.POST)
    @ResponseBody
    private VoteDTO getVote(@RequestBody VoteDTO voteDTO) {
        return this.voteService.getVote(voteDTO);
    }

    @RequestMapping(value = "/add-vote", method = RequestMethod.POST)
    @ResponseBody
    private VoteDTO addVote(@RequestBody VoteDTO voteDTO) {
        this.voteService.addVote(voteDTO);
        return voteDTO;
    }

    @RequestMapping(value = "/change-vote", method = RequestMethod.PUT)
    @ResponseBody
    private VoteDTO chaneVote(@RequestBody VoteDTO voteDTO) {
        this.voteService.changeVote(voteDTO);
        return voteDTO;
    }

    @RequestMapping(value = "/delete-vote", method = RequestMethod.POST)
    @ResponseBody
    private VoteDTO deleteVote(@RequestBody VoteDTO voteDTO) {
        this.voteService.deleteVote(voteDTO);
        return voteDTO;
    }

}
