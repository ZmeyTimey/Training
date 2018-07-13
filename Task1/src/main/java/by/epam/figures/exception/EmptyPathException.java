package by.epam.figures.exception;

/**
 * Is thrown when an expected path to file is empty string.
 */
public class EmptyPathException extends FileReadingException {
    /**
     * Constructor for {@link EmptyPathException}.
     */
    public EmptyPathException() {
    }

    /**
     * Constructor for {@link EmptyPathException}.
     * @param message of exception.
     */
    public EmptyPathException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link EmptyPathException}.
     * @param message of exception.
     * @param cause of exception.
     */
    public EmptyPathException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link EmptyPathException}.
     * @param cause of exception.
     */
    public EmptyPathException(final Throwable cause) {
        super(cause);
    }
}
