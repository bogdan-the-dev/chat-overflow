package ro.bogdan.chatoverflow.DTO;

import ro.bogdan.chatoverflow.model.Answer;
import ro.bogdan.chatoverflow.model.Tag;

import java.util.Calendar;
import java.util.List;

public class QuestionDTO implements Comparable<QuestionDTO> {

    private int questionId;

    private String title;

    private String text;

    private Calendar creationDate;

    private int upVotes;

    private int downVotes;

    private String username;

    private int userScore;

    private List<String> tags;

    private List<AnswerDTO> answers;

    public QuestionDTO() {
    }

    public QuestionDTO(int questionId, String title, String text, Calendar creationDate, int upVotes, int downVotes) {
        this.questionId = questionId;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
    }

    public QuestionDTO(int questionId, String title, String text, Calendar creationDate, int upVotes, int downVotes, String username) {
        this.questionId = questionId;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.username = username;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    @Override
    public int compareTo(QuestionDTO o) {
        if (this.creationDate.after(o.getCreationDate()))
            return 1;
        else
            return 0;
    }
}
