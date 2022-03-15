package ro.bogdan.chatoverflow.DTO;

import java.util.Calendar;

public class AnswerDTO {

    private int id;

    private String username;

    private String answerBody;

    private Calendar date;

    private boolean edited;

    private int upVotes;

    private int downVotes;

    private int questionId;

    public AnswerDTO(int id, String answerBody, Calendar date, boolean edited, int upVotes, int downVotes, int questionId) {
        this.id = id;
        this.answerBody = answerBody;
        this.date = date;
        this.edited = edited;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.questionId = questionId;
    }

    public AnswerDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
