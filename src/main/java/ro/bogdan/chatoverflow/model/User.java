package ro.bogdan.chatoverflow.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", schema = "chatoverflow")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "account_verified")
    private boolean accountVerified;

    @Column(name = "account_blocked")
    private  boolean accountBlocked;

    @Column(name = "account_banned")
    private boolean accountBanned;

    @Column(name = "two_factor_authentication")
    private boolean twoFA;

    @Column(name = "score")
    private int score;

    @ManyToOne
    @JoinColumn(name = "user_role_id", referencedColumnName = "role_id")
    private Role role;

    @OneToMany(mappedBy = "author")
    private List<Answer> answers;

    @OneToMany(mappedBy = "author")
    private List<Question> questions;

    public User(int id, String username, String email, String passwordHash, boolean accountVerified, boolean accountBlocked, boolean accountBanned, boolean twoFA, int score, Role role, List<Answer> answers, List<Question> questions) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.accountVerified = accountVerified;
        this.accountBlocked = accountBlocked;
        this.accountBanned = accountBanned;
        this.twoFA = twoFA;
        this.score = score;
        this.role = role;
        this.answers = answers;
        this.questions = questions;
    }

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    public boolean isAccountBlocked() {
        return accountBlocked;
    }

    public void setAccountBlocked(boolean accountBlocked) {
        this.accountBlocked = accountBlocked;
    }

    public boolean isAccountBanned() {
        return accountBanned;
    }

    public void setAccountBanned(boolean accountBanned) {
        this.accountBanned = accountBanned;
    }

    public boolean isTwoFA() {
        return twoFA;
    }

    public void setTwoFA(boolean twoFA) {
        this.twoFA = twoFA;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question){
        this.questions.add(question);
    }

    public void deleteQuestion(Question question){
        this.questions.remove(question);
    }

    public void addAnswer(Answer answer){
        this.answers.add(answer);
    }

    public void deleteAnswer(Answer answer){
        this.answers.remove(answer);
    }
}

