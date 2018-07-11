package by.epam.figures.exception;

/**
 * {@link SideOutOfDoubleRangeException} is thrown when a side of triangle is out of double type range.
 */
public class SideOutOfDoubleRangeException extends OutOfDoubleRangeException {
    public SideOutOfDoubleRangeException() {
    }

    public SideOutOfDoubleRangeException(String message) {
        super(message);
    }

    public SideOutOfDoubleRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SideOutOfDoubleRangeException(Throwable cause) {
        super(cause);
    }
}
