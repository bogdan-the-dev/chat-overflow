package ro.bogdan.chatoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.bogdan.chatoverflow.model.Tag;
import ro.bogdan.chatoverflow.service.TagService;

import java.util.List;

@Controller
@RequestMapping(value = "/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET, value = "/all-tags")
    @ResponseBody
    private List<Tag> getTags(){
        return tagService.getTags();
    }


}
