package ro.bogdan.chatoverflow.DTO;

import ro.bogdan.chatoverflow.model.Role;

public class AuthenticationDTO {
    String username;
    Role role;
    String errorMessage;

    public AuthenticationDTO(String username, Role role, String errorMessage) {
        this.username = username;
        this.role = role;
        this.errorMessage = errorMessage;
    }

    public AuthenticationDTO(String username, Role role) {
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
