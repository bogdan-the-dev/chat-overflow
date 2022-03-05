package ro.bogdan.chatoverflow.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tag", schema="chatoverflow")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tag_id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "tag")
    @JsonManagedReference
    private List<TagItem> tags;

    public Tag(int id, String name, List<TagItem> tags) {
        this.id = id;
        this.name = name;
        this.tags = tags;
    }

    public Tag(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TagItem> getTags() {
        return tags;
    }

    public void setTags(List<TagItem> tags) {
        this.tags = tags;
    }
}
