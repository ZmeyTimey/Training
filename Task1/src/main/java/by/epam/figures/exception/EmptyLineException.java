package by.epam.figures.exception;

/**
 * Created by Тимей on 05.07.2018.
 *
 */
public class EmptyLineException extends LineReadingException {
    public EmptyLineException() {
    }

    public EmptyLineException(String message) {
        super(message);
    }

    public EmptyLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyLineException(Throwable cause) {
        super(cause);
    }
}
