package ro.bogdan.chatoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.bogdan.chatoverflow.model.Question;
import ro.bogdan.chatoverflow.model.TagItem;
import ro.bogdan.chatoverflow.service.TagItemService;

import java.util.List;

@Controller
@RequestMapping(value = "/tags")
@CrossOrigin()
public class TagItemController {
    @Autowired
    private TagItemService tagItemService;

    @RequestMapping(method = RequestMethod.GET, value = "/tagitems")
    @ResponseBody
    private List<TagItem> getTagItems() {
        return tagItemService.findTagItems();
    }

    @RequestMapping("/find")
    @ResponseBody
    private List<Question> getQuestionsByTag(@RequestParam("name") String tagName) {
        return tagItemService.getQuestionsByTag(tagName);
    }

}
