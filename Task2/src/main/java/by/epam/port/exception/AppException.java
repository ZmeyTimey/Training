package by.epam.port.exception;

/**
 * It is thrown when any exception occurs during program execution.
 */
public class AppException extends Exception {

    /**
     * Constructor of this exception class.
     */
    public AppException() {
    }

    /**
     * Constructor of this exception class.
     * @param message of the exception.
     */
    public AppException(final String message) {
        super(message);
    }

    /**
     * Constructor of this exception class.
     * @param message of the exception.
     * @param cause of the exception.
     */
    public AppException(final String message,
                        final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor of this exception class.
     * @param cause of the exception.
     */
    public AppException(final Throwable cause) {
        super(cause);
    }
}
