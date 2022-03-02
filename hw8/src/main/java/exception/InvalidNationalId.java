package exception;

public class InvalidNationalId extends RuntimeException {
    public InvalidNationalId() {
    }

    public InvalidNationalId(String message) {
        super(message);
    }

    public InvalidNationalId(String message, Throwable cause) {
        super(message, cause);
    }

}

