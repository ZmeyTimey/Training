package by.epam.figures.exception;

/**
 * Thrown when data received by a method is not valid.
 */
public class InvalidInputDataException extends Exception {
    /**
     * Constructor fot {@link InvalidInputDataException}.
     */
    public InvalidInputDataException() {
    }

    /**
     * Constructor for {@link InvalidInputDataException}.
     * @param message of exception.
     */
    public InvalidInputDataException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link InvalidInputDataException}.
     * @param message of exception.
     * @param cause of exception.
     */
    public InvalidInputDataException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link InvalidInputDataException}.
     * @param cause of exception.
     */
    public InvalidInputDataException(final Throwable cause) {
        super(cause);
    }
}
