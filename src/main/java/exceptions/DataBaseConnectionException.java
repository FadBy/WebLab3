package exceptions;

public class DataBaseConnectionException extends RuntimeException {
    public DataBaseConnectionException() {
    }

    public DataBaseConnectionException(String message) {
        super(message);
    }
}
