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

    public Answer(int id, User author, String answerBody, Calendar date, boolean edited, int questionId) {
        this.id = id;
        this.author = author;
        this.answerBody = answerBody;
        this.date = date;
        this.edited = edited;
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

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}


