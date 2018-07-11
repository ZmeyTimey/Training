package by.epam.figures.exception;

/**
 * {@link LineReadingException} includes exceptions which emerge during reading a line.
 */
public class LineReadingException extends Exception {
    public LineReadingException() {
    }

    public LineReadingException(String message) {
        super(message);
    }

    public LineReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LineReadingException(Throwable cause) {
        super(cause);
    }
}
