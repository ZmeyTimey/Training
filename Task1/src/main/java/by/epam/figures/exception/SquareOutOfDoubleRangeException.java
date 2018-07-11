package by.epam.figures.exception;

/**
 * {@link SquareOutOfDoubleRangeException} is thrown when a square of triangle is out of double type range.
 */
public class SquareOutOfDoubleRangeException extends OutOfDoubleRangeException {
    public SquareOutOfDoubleRangeException() {
    }

    public SquareOutOfDoubleRangeException(String message) {
        super(message);
    }

    public SquareOutOfDoubleRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SquareOutOfDoubleRangeException(Throwable cause) {
        super(cause);
    }
}
