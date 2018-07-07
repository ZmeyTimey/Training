package by.epam.figures.exception;

/**
 * Created by Тимей on 05.07.2018.
 *
 */
public class IncorrectLineException extends LineReadingException {
    public IncorrectLineException() {
    }

    public IncorrectLineException(String message) {
        super(message);
    }

    public IncorrectLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectLineException(Throwable cause) {
        super(cause);
    }
}
