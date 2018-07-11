package by.epam.figures.exception;

/**
 * {@link FileReadingException} includes exceptions which emerge during reading from file.
 */
public class FileReadingException extends Exception {
    public FileReadingException() {
    }

    public FileReadingException(String message) {
        super(message);
    }

    public FileReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReadingException(Throwable cause) {
        super(cause);
    }
}
