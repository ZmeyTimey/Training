package by.epam.port.exception;

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
     * @param message of the exception.
     */
    public FileReadingException(final String message) {
        super(message);
    }
    /**
     * Constructor for {@link FileReadingException}.
     * @param message of the exception.
     * @param cause of the exception.
     */
    public FileReadingException(final String message, final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constructor for {@link FileReadingException}.
     * @param cause of the exception.
     */
    public FileReadingException(final Throwable cause) {
        super(cause);
    }
}
