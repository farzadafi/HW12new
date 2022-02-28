package Service;

public class InvalidNationalException extends RuntimeException {
    public InvalidNationalException() {
    }

    public InvalidNationalException(String message) {
        super(message);
    }

    public InvalidNationalException(Throwable cause) {
        super(cause);
    }
}
