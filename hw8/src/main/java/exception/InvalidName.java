package exception;

public class InvalidName extends RuntimeException{
    public InvalidName() {
    }

    public InvalidName(String message) {
        super(message);
    }

    public InvalidName(String message, Throwable cause) {
        super(message, cause);
    }

}

