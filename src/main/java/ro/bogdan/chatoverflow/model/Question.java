package ro.bogdan.chatoverflow.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "question", schema = "chatoverflow")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "question_author_id", referencedColumnName = "id")
    private User author;

    @Column(name = "text")
    private String text;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "score")
    private int score;

    @OneToMany(mappedBy = "question")
    @JsonManagedReference
    private List<TagItem> tagItem;

    public Question(int id, String title, User author, String text, Date creationDate, int score, List<TagItem> tagItem) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.text = text;
        this.creationDate = creationDate;
        this.score = score;
        this.tagItem = tagItem;
    }

    public Question(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<TagItem> getTagItem() {
        return tagItem;
    }

    public void setTagItem(List<TagItem> tagItem) {
        this.tagItem = tagItem;
    }
}
