package by.epam.figures.exception;

/**
 * Created by Тимей on 04.07.2018.
 *
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
