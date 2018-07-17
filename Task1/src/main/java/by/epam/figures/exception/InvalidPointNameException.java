package by.epam.figures.exception;
/**
 * Thrown when a name of point received by a method is not valid.
 */
public class InvalidPointNameException extends InvalidInputDataException {
    /**
     * Constructor for {@link InvalidPointNameException}.
     */
    public InvalidPointNameException() {
    }

    /**
     * Constuctor for {@link InvalidPointNameException}.
     * @param message of exception.
     */
    public InvalidPointNameException(final String message) {
        super(message);
    }

    /**
     * Constructor of {@link InvalidPointNameException}.
     * @param message of exception.
     * @param cause of exception.
     */
    public InvalidPointNameException(final String message,
                                     final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor of {@link InvalidPointNameException}.
     * @param cause of exception.
     */
    public InvalidPointNameException(final Throwable cause) {
        super(cause);
    }
}
