package ro.bogdan.chatoverflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.bogdan.chatoverflow.DTO.QuestionDTO;
import ro.bogdan.chatoverflow.model.Question;
import ro.bogdan.chatoverflow.model.Tag;
import ro.bogdan.chatoverflow.model.TagItem;
import ro.bogdan.chatoverflow.repository.ITagItemRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagItemService {
    @Autowired
    private ITagItemRepository iTagItemRepository;
    @Autowired
    private TagService tagService;

    public List<TagItem> findTagItems() {
        return (List<TagItem>) iTagItemRepository.findAll();
    }

    public void createAndSaveTagItems(ArrayList<String> tags, Question question) {
        for (String tagName : tags) {
            Tag tag = tagService.saveTag(tagName);
            saveTagItem(new TagItem(question, tag));
        }
    }

    public List<Question> getQuestionsByTag(String name) {
        ArrayList<Question> questions = new ArrayList<>();
        for (TagItem tagItem : iTagItemRepository.findAll()) {
            if (tagItem.getTag().getName().toLowerCase().contains(name.toLowerCase())) {
                questions.add(tagItem.getQuestion());
            }
        }
        ArrayList<Question> questionsSorted = (ArrayList<Question>) questions.stream().sorted(Comparator.comparing(Question::getCreationDate)).collect(Collectors.toList());
        Collections.reverse(questionsSorted);
        return questionsSorted;
    }

    public void updateTagItems(List<String> newTags, Question question) {
        List<String> currentTags = getTagsForQuestion(question.getQuestionId());
        ArrayList<String> createTags = (ArrayList<String>) newTags.stream().filter(element -> !currentTags.contains(element)).collect(Collectors.toList());
        ArrayList<String> tagsForDeletion = (ArrayList<String>) currentTags.stream().filter(element -> !newTags.contains(element)).collect(Collectors.toList());
        createAndSaveTagItems(createTags, question);
        for (String tag : tagsForDeletion) {
            deleteTagItem(question.getQuestionId(), tag);
        }
    }

    public List<String> getTagsForQuestion(int questionId) {
        ArrayList<String> tags = new ArrayList<>();
        for (TagItem tagItem : iTagItemRepository.findTagItemsByQuestionQuestionIdIs(questionId)) {
            tags.add(tagItem.getTag().getName());
        }
        return tags;
    }

    public void deleteTagItem(int questionId, String tag) {
        TagItem item = iTagItemRepository.findTagItemByQuestionQuestionIdAndTagName(questionId, tag);
        iTagItemRepository.delete(item);
    }

    public void deleteTagItem(int tagItemId) {
        TagItem item = iTagItemRepository.findById(tagItemId).orElse(null);
        if (item != null) {
            iTagItemRepository.delete(item);
        }
    }

    public TagItem saveTagItem(TagItem tagItem) {
        if (!iTagItemRepository.existsById(tagItem.getId())) {
            iTagItemRepository.save(tagItem);
        }
        return tagItem;
    }

}