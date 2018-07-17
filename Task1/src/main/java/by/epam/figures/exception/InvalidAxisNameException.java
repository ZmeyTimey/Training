package by.epam.figures.exception;

/**
 * Thrown when a name of coordinate axis received by a method is not valid.
 */
public class InvalidAxisNameException extends InvalidInputDataException {
    /**
     * Constructor for {@link InvalidAxisNameException}.
     */
    public InvalidAxisNameException() {
    }

    /**
     * Constructor for {@link InvalidAxisNameException}.
     * @param message of exception.
     */
    public InvalidAxisNameException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link InvalidAxisNameException}.
     * @param message of exception.
     * @param cause of exception.
     */
    public InvalidAxisNameException(final String message,
                                    final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link InvalidAxisNameException}.
     * @param cause of exception.
     */
    public InvalidAxisNameException(final Throwable cause) {
        super(cause);
    }
}
