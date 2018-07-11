package by.epam.figures.exception;

/**
 * {@link PerimeterOutOfDoubleRangeException} is thrown when a perimeter of triangle is out of double type range.
 */
public class PerimeterOutOfDoubleRangeException extends OutOfDoubleRangeException {
    public PerimeterOutOfDoubleRangeException() {
    }

    public PerimeterOutOfDoubleRangeException(String message) {
        super(message);
    }

    public PerimeterOutOfDoubleRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PerimeterOutOfDoubleRangeException(Throwable cause) {
        super(cause);
    }
}
