package by.epam.figures.exception;

public class IncorrectFilePathException extends FileReadingException {
    public IncorrectFilePathException() {
    }

    public IncorrectFilePathException(String message) {
        super(message);
    }

    public IncorrectFilePathException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectFilePathException(Throwable cause) {
        super(cause);
    }
}
