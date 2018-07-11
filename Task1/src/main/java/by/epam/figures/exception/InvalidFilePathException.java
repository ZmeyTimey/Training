package by.epam.figures.exception;

/**
 * {@link InvalidFilePathException} is thrown when a path to file is not correct.
 */
public class InvalidFilePathException extends FileReadingException {
    public InvalidFilePathException() {
    }

    public InvalidFilePathException(String message) {
        super(message);
    }

    public InvalidFilePathException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFilePathException(Throwable cause) {
        super(cause);
    }
}
