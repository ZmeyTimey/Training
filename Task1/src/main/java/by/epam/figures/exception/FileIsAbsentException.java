package by.epam.figures.exception;

/**
 * {@link EmptyLineException} is thrown when a file is not found by {@link by.epam.figures.reader.Reader}.
 */
public class FileIsAbsentException extends FileReadingException {
    public FileIsAbsentException() {
    }

    public FileIsAbsentException(String message) {
        super(message);
    }

    public FileIsAbsentException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIsAbsentException(Throwable cause) {
        super(cause);
    }
}
