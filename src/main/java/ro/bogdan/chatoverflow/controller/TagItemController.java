package ro.bogdan.chatoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.bogdan.chatoverflow.model.Question;
import ro.bogdan.chatoverflow.model.TagItem;
import ro.bogdan.chatoverflow.service.TagItemService;

import java.util.List;

@Controller
@RequestMapping(value = "/tags")
public class TagItemController {
    @Autowired
    private TagItemService tagItemService;

    @RequestMapping(method = RequestMethod.GET, value = "/tagitems")
    @ResponseBody
    private List<TagItem> getTagItems(){
        return tagItemService.findTagItems();
    }

    @RequestMapping("/find")
    @ResponseBody
    private List<Question> getQuestionsByTag(@RequestParam("name") String tagName) {
        return tagItemService.getQuestionsByTag(tagName);
    }

}
