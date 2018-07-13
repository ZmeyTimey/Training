package by.epam.figures.exception;

/**
 * {@link OutOfDoubleRangeException} is thrown when a number
 * is out of double type range.
 */
public class OutOfDoubleRangeException extends Exception {
    /**
     * Constructor for {@link OutOfDoubleRangeException}.
     */
    public OutOfDoubleRangeException() {
    }

    /**
     * Constructor for {@link OutOfDoubleRangeException}.
     * @param message of exception.
     */
    public OutOfDoubleRangeException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link OutOfDoubleRangeException}.
     * @param message of exception.
     * @param cause of exception.
     */
    public OutOfDoubleRangeException(final String message,
                                     final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link OutOfDoubleRangeException}.
     * @param cause of exception.
     */
    public OutOfDoubleRangeException(final Throwable cause) {
        super(cause);
    }
}
