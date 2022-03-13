package ro.bogdan.chatoverflow.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "answer", schema = "chatoverflow")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "question_id")
    private int questionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_author_id")
    private User author;

    @Column(name = "body")
    private String answerBody;

    @Column(name = "date")
    private Calendar date;

    @Column(name = "edited")
    private boolean edited;

    @Column(name = "up_votes")
    private int upVotes;

    @Column(name = "down_votes")
    private int downVotes;

    public Answer(int id, User author, String answerBody, Calendar date, boolean edited, int upVotes, int downVotes, int questionId) {
        this.id = id;
        this.author = author;
        this.answerBody = answerBody;
        this.date = date;
        this.edited = edited;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.questionId = questionId;
    }

    public Answer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAnswerBody() {
        return answerBody;
    }

    public void setAnswerBody(String answerBody) {
        this.answerBody = answerBody;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
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

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}


