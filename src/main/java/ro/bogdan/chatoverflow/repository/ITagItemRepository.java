package ro.bogdan.chatoverflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.bogdan.chatoverflow.model.TagItem;

@Repository
public interface ITagItemRepository extends JpaRepository<TagItem, Integer> {

    Iterable<TagItem> findTagItemsByQuestionQuestionIdIs(Integer id);

    TagItem findTagItemByQuestionQuestionIdAndTagName(int id, String name);

}
