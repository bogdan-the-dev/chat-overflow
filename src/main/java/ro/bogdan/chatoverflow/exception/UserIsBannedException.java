package ro.bogdan.chatoverflow.exception;

public class UserIsBannedException extends RuntimeException{
    public UserIsBannedException() {
        super("User is banned");
    }
}
