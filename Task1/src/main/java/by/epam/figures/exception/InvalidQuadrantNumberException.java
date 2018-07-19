package by.epam.figures.exception;

/**
 * Thrown when input value of quadrant is not in the range from 1 to 4.
 */
public class InvalidQuadrantNumberException extends Exception {

    /**
     * Constructor for {@link InvalidQuadrantNumberException}.
     */
    public InvalidQuadrantNumberException() {
    }

    /**
     * Constructor for {@link InvalidQuadrantNumberException}.
     * @param message of the exception.
     */
    public InvalidQuadrantNumberException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link InvalidQuadrantNumberException}.
     * @param message of the exception.
     * @param cause of the exception.
     */
    public InvalidQuadrantNumberException(final String message,
                                          final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link InvalidQuadrantNumberException}.
     * @param cause of the exception.
     */
    public InvalidQuadrantNumberException(final Throwable cause) {
        super(cause);
    }
}
