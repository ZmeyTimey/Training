package by.epam.port.exception;

/**
 * {@link InvalidFilePathException} is thrown when a path to file is not correct.
 */
public class InvalidFilePathException extends FileReadingException {
    /**
     * Constructor for {@link InvalidFilePathException}.
     */
    public InvalidFilePathException() {
    }

    /**
     * Constructor for {@link InvalidFilePathException}.
     * @param message of the exception.
     */
    public InvalidFilePathException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link InvalidFilePathException}.
     * @param message of the exception.
     * @param cause of the exception.
     */
    public InvalidFilePathException(final String message,
                                    final Throwable cause) {
        super(message, cause);
    }
    /**
     * Constructor for {@link InvalidFilePathException}.
     * @param cause of the exception.
     */
    public InvalidFilePathException(final Throwable cause) {
        super(cause);
    }
}
