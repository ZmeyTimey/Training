package by.epam.figures.exception;

/**
 * {@link EmptyLineException} is thrown when a line which is being read is empty.
 */
public class EmptyLineException extends LineReadingException {
    public EmptyLineException() {
    }

    public EmptyLineException(String message) {
        super(message);
    }

    public EmptyLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyLineException(Throwable cause) {
        super(cause);
    }
}
