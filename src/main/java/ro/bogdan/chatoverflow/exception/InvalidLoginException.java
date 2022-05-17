package ro.bogdan.chatoverflow.exception;

public class InvalidLoginException extends RuntimeException{

    public InvalidLoginException (String error) {
        super(error);
    }
}
