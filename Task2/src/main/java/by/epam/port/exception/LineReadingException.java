package by.epam.port.exception;

/**
 * {@link LineReadingException} includes exceptions which emerge
 * during reading a line.
 */
public class LineReadingException extends FileReadingException {
    /**
     * Constructor for {@link LineReadingException}.
     */
    public LineReadingException() {
    }

    /**
     * Constructor for {@link LineReadingException}.
     * @param message of the exception.
     */
    public LineReadingException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link LineReadingException}.
     * @param message of the exception.
     * @param cause of the exception.
     */
    public LineReadingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link LineReadingException}.
     * @param cause of the exception.
     */
    public LineReadingException(final Throwable cause) {
        super(cause);
    }
}
