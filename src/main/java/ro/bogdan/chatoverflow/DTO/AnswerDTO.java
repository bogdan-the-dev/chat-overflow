package ro.bogdan.chatoverflow.DTO;

import java.util.Calendar;

public class AnswerDTO implements Comparable<AnswerDTO>{

    private int id;

    private String username;

    private String answerBody;

    private Calendar date;

    private boolean edited;

    private int upVotes;

    private int downVotes;

    private int questionId;

    private int userScore;

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

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    @Override
    public int compareTo(AnswerDTO o) {
        int currentSum = this.upVotes - this.downVotes;
        int otherSum = o.upVotes - this.downVotes;
        if(currentSum - otherSum > 0)
            return 1;
        else
            return 0;
    }
}
