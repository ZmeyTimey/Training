package by.epam.figures.exception;

/**
 * {@link FileReadingException} includes exceptions which emerge
 * during reading from file.
 */
public class FileReadingException extends Exception {
    /**
     * Constructor for {@link FileReadingException}.
     */
    public FileReadingException() {
    }

    /**
     * Constructor for {@link FileReadingException}.
     * @param message of exception.
     */
    public FileReadingException(final String message) {
        super(message);
    }
    /**
     * Constructor for {@link FileReadingException}.
     * @param message of exception.
     * @param cause of exception.
     */
    public FileReadingException(final String message, final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constructor for {@link FileReadingException}.
     * @param cause of exception.
     */
    public FileReadingException(final Throwable cause) {
        super(cause);
    }
}
