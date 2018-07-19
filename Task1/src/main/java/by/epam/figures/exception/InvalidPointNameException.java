package by.epam.figures.exception;
/**
 * Thrown when a name of point received by a method is not valid.
 */
public class InvalidPointNameException extends Exception {
    /**
     * Constructor for {@link InvalidPointNameException}.
     */
    public InvalidPointNameException() {
    }

    /**
     * Constuctor for {@link InvalidPointNameException}.
     * @param message of the exception.
     */
    public InvalidPointNameException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link InvalidPointNameException}.
     * @param message of the exception.
     * @param cause of the exception.
     */
    public InvalidPointNameException(final String message,
                                     final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link InvalidPointNameException}.
     * @param cause for the exception.
     */
    public InvalidPointNameException(final Throwable cause) {
        super(cause);
    }
}
