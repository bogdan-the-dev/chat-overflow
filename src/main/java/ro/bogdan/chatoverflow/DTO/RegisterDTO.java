package ro.bogdan.chatoverflow.DTO;

public class RegisterDTO {

    private String username;

    private String password;

    private String passwordValidation;

    private String email;

    public RegisterDTO(String username, String password, String passwordValidation, String email) {
        this.username = username;
        this.password = password;
        this.passwordValidation = passwordValidation;
        this.email = email;
    }

    public RegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordValidation() {
        return passwordValidation;
    }

    public void setPasswordValidation(String passwordValidation) {
        this.passwordValidation = passwordValidation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
