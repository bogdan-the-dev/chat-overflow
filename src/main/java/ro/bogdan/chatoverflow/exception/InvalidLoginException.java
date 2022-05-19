package ro.bogdan.chatoverflow.exception;

public class InvalidLoginException extends RuntimeException{

    public InvalidLoginException (String error) {
        super(error);
    }

    public InvalidLoginException() {
        super("The combination of username and password doesn't match");
    }
}
