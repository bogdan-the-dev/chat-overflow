package ro.bogdan.chatoverflow.DTO;

public class VoteDTO {

    private int id;

    private String username;

    private int score;

    private int question_id;

    private int answer_id;

    public VoteDTO(int id, String username, int score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    public VoteDTO() {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }
}
