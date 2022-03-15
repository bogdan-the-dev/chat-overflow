package ro.bogdan.chatoverflow.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "question", schema = "chatoverflow")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private int questionId;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "question_author_id", referencedColumnName = "id")
    private User author;

    @Column(name = "text")
    private String text;

    @Column(name = "creation_date")
    private Calendar creationDate;

    @Column(name = "up_votes")
    private int upVotes;

    @Column(name = "down_votes")
    private int downVotes;

    public Question(int questionId, String title, User author, String text, Calendar creationDate, int upVotes, int downVotes) {
        this.questionId = questionId;
        this.title = title;
        this.author = author;
        this.text = text;
        this.creationDate = creationDate;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
    }

    public Question(){
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
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

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

}
