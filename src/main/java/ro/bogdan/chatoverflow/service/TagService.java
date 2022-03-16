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

    public List<Tag> getTags() {
        return (List<Tag>) iTagRepository.findAll();
    }

    public void deleteTag(Integer id) throws Exception {
        try {
            iTagRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Delete failed, tag not found!");
        }
    }

    public Tag saveTag(String name) {
        if (!iTagRepository.existsTagByName(name)) {
            iTagRepository.save(new Tag(name));
        }
        return iTagRepository.findTagByName(name).get();
    }
}
