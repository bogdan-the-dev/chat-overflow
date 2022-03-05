package ro.bogdan.chatoverflow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="tag_item", schema = "chatoverflow")
public class TagItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    @JsonBackReference
    private Question question;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_name_id", referencedColumnName = "tag_id")
    @JsonBackReference
    private Tag tag;

    public TagItem(int id, Question question, Tag tag) {
        this.id = id;
        this.question = question;
        this.tag = tag;
    }

    public TagItem(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
