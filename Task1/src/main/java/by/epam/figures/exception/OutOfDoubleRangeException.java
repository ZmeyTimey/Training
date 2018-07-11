package by.epam.figures.exception;

/**
 * {@link OutOfDoubleRangeException} is thrown when a number is out of double type range.
 */
public class OutOfDoubleRangeException extends Exception {

    public OutOfDoubleRangeException() {
    }

    public OutOfDoubleRangeException(String message) {
        super(message);
    }

    public OutOfDoubleRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfDoubleRangeException(Throwable cause) {
        super(cause);
    }
}
