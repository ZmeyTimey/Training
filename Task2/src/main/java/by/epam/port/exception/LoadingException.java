package by.epam.port.exception;

/**
 * This exception is thrown when during loading the nominal volume
 * of the store is exceeded or the store is empty.
 */
public class LoadingException extends Exception {

    /**
     * Constructor for this exception class.
     */
    public LoadingException() {
        }

    /**
     * Constructor for this exception class.
     * @param message of the exception.
     */
    public LoadingException(final String message) {
        super(message);
    }

    /**
     * Constructor for this exception class.
     * @param message of the exception.
     * @param cause of the exception.
     */

    public LoadingException(final String message,
                            final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for this exception class.
     * @param cause of the exception.
     */
    public LoadingException(final Throwable cause) {
        super(cause);
    }
}
