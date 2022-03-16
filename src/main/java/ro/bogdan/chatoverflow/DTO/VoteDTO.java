package ro.bogdan.chatoverflow.DTO;

public class VoteDTO {


    private String username;

    private int score;

    private Integer questionId;

    private Integer answerId;

    public VoteDTO(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public VoteDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }
}
