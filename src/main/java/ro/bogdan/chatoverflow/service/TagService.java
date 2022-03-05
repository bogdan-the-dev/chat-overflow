package ro.bogdan.chatoverflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.model.Tag;
import ro.bogdan.chatoverflow.repository.ITagRepository;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private ITagRepository iTagRepository;

    public List<Tag> findTags(){
        return (List<Tag>) iTagRepository.findAll();
    }
}
