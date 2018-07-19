package by.epam.figures.exception;

/**
 * {@link PointsFormLineException} is thrown when three points
 * which are received to {@link by.epam.figures.creator.TriangleCreator}
 * are on one line.
 */
public class PointsFormLineException extends Exception {
    /**
     * Constructor for {@link PointsFormLineException}.
     */
    public PointsFormLineException() {
    }

    /**
     * Constructor for {@link PointsFormLineException}.
     * @param message of the exception.
     */
    public PointsFormLineException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link PointsFormLineException}.
     * @param message of the exception.
     * @param cause of the exception.
     */
    public PointsFormLineException(final String message,
                                   final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link PointsFormLineException}.
     * @param cause of the exception.
     */
    public PointsFormLineException(final Throwable cause) {
        super(cause);
    }
}
