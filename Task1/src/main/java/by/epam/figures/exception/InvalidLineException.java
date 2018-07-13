package by.epam.figures.exception;

/**
 * {@link InvalidLineException} is thrown when a read line is not valid.
 */

public class InvalidLineException extends LineReadingException {
    /**
     * Constructor for {@link InvalidLineException}.
     */
    public InvalidLineException() {
    }

    /**
     * Constructor for {@link InvalidLineException}.
     * @param message of exception.
     */
    public InvalidLineException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link InvalidLineException}.
     * @param message of exception.
     * @param cause of exception.
     */
    public InvalidLineException(final String message, final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constructor for {@link InvalidLineException}.
     * @param cause of exception.
     */
    public InvalidLineException(final Throwable cause) {
        super(cause);
    }
}
