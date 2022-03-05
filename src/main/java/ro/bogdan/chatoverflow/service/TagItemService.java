package ro.bogdan.chatoverflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.model.TagItem;
import ro.bogdan.chatoverflow.repository.ITagItemRepository;

import java.util.List;

@Service
public class TagItemService {
    @Autowired
    private ITagItemRepository iTagItemRepository;

    public List<TagItem> findTagItems(){
        return (List<TagItem>) iTagItemRepository.findAll();
    }
}
