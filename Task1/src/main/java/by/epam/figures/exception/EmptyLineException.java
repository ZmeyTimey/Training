package by.epam.figures.exception;

/**
 * {@link EmptyLineException} is thrown when a line which is being read
 * is empty.
 */
public class EmptyLineException extends LineReadingException {
    /**
     * Constructor for {@link EmptyLineException}.
     */
    public EmptyLineException() {
    }

    /**
     * Constructor for {@link EmptyLineException}.
     * @param message of the exception.
     */
    public EmptyLineException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link EmptyLineException}.
     * @param message of the exception.
     * @param cause of the exception.
     */
    public EmptyLineException(final String message, final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constructor for {@link EmptyLineException}.
     * @param cause of the exception.
     */
    public EmptyLineException(final Throwable cause) {
        super(cause);
    }
}
