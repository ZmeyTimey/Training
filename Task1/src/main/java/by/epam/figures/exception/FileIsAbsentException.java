package by.epam.figures.exception;

/**
 * {@link EmptyLineException} is thrown when a file is not found by
 * {@link by.epam.figures.reader.Reader}.
 */
public class FileIsAbsentException extends FileReadingException {
    /**
     * Constructor for {@link FileIsAbsentException}.
     */
    public FileIsAbsentException() {
    }

    /**
     * Constructor for {@link FileIsAbsentException}.
     * @param message of the exception.
     */
    public FileIsAbsentException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link FileIsAbsentException}.
     * @param message of the exception.
     * @param cause of the exception.
     */
    public FileIsAbsentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link FileIsAbsentException}.
     * @param cause of the exception.
     */
    public FileIsAbsentException(final Throwable cause) {
        super(cause);
    }
}
