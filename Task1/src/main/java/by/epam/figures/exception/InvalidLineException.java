package by.epam.figures.exception;

/**
 * {@link InvalidLineException} is thrown when a read line is not valid.
 */

public class InvalidLineException extends LineReadingException {
    public InvalidLineException() {
    }

    public InvalidLineException(String message) {
        super(message);
    }

    public InvalidLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLineException(Throwable cause) {
        super(cause);
    }
}
