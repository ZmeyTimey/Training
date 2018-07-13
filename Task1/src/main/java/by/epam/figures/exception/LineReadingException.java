package by.epam.figures.exception;

/**
 * {@link LineReadingException} includes exceptions which emerge
 * during reading a line.
 */
public class LineReadingException extends Exception {
    /**
     * Constructor for {@link LineReadingException}.
     */
    public LineReadingException() {
    }

    /**
     * Constructor for {@link LineReadingException}.
     * @param message of exception.
     */
    public LineReadingException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link LineReadingException}.
     * @param message of exception.
     * @param cause of exception.
     */
    public LineReadingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link LineReadingException}.
     * @param cause of exception.
     */
    public LineReadingException(final Throwable cause) {
        super(cause);
    }
}
