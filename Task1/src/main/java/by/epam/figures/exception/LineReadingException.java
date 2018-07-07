package by.epam.figures.exception;

/**
 * Created by Тимей on 05.07.2018.
 *
 */
public class LineReadingException extends Exception {
    public LineReadingException() {
    }

    public LineReadingException(String message) {
        super(message);
    }

    public LineReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public LineReadingException(Throwable cause) {
        super(cause);
    }
}
