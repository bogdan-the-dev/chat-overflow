package ro.bogdan.chatoverflow.DTO;

import ro.bogdan.chatoverflow.model.Role;

public class AuthenticationDTO {
    int userId;
    String username;
    Role role;
    String errorMessage;

    public AuthenticationDTO(int userId,String username, Role role, String errorMessage) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.errorMessage = errorMessage;
    }

    public AuthenticationDTO(int userId, String username, Role role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public AuthenticationDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public AuthenticationDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
