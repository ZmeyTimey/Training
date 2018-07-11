package by.epam.figures.exception;

/**
 * {@link PointsFormLineException} is thrown when three points which are received to
 * {@link by.epam.figures.creator.TriangleCreator} are on one line.
 */
public class PointsFormLineException extends Exception {
    public PointsFormLineException() {
    }

    public PointsFormLineException(String message) {
        super(message);
    }

    public PointsFormLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public PointsFormLineException(Throwable cause) {
        super(cause);
    }
}
