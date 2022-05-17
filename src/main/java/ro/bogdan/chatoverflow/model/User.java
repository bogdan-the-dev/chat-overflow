package ro.bogdan.chatoverflow.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", schema = "chatoverflow")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "account_verified")
    private boolean accountVerified;

    @Column(name = "account_blocked")
    private boolean accountBlocked;

    @Column(name = "account_banned")
    private boolean accountBanned;

    @Column(name = "two_factor_authentication")
    private boolean twoFA;

    @Column(name = "score")
    private int score;

    @ManyToOne
    @JoinColumn(name = "user_role_id", referencedColumnName = "role_id")
    private Role role;

    public User(String username, String email, String passwordHash, boolean accountVerified, boolean accountBlocked, boolean accountBanned, boolean twoFA, int score, Role role) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.accountVerified = accountVerified;
        this.accountBlocked = accountBlocked;
        this.accountBanned = accountBanned;
        this.twoFA = twoFA;
        this.score = score;
        this.role = role;
    }

    public User(String username, String email, String passwordHash, Role role) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.accountVerified = false;
        this.accountBlocked = false;
        this.accountBanned = false;
        this.twoFA = false;
        this.score = 0;
        this.role = role;
    }

    public User(int userId, String username, String email, String passwordHash, boolean accountVerified, boolean accountBlocked, boolean accountBanned, boolean twoFA, int score, Role role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.accountVerified = accountVerified;
        this.accountBlocked = accountBlocked;
        this.accountBanned = accountBanned;
        this.twoFA = twoFA;
        this.score = score;
        this.role = role;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void upVote(int score) {
        this.score += score;
    }

    public void downVote(int score) {
        this.score -= score;
    }

}

