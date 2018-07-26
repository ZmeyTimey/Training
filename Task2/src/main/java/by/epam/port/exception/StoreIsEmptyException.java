package by.epam.port.exception;

/**
 * This exception ts thrown when a ship tries to load container
 * from the store when it's empty.
 */
public class StoreIsEmptyException extends LoadingException {

    /**
     * Constructor for this exception class.
     */
    public StoreIsEmptyException() {
    }

    /**
     * Constructor for this exception class.
     * @param message of the exception.
     */
    public StoreIsEmptyException(final String message) {
        super(message);
    }

    /**
     * Constructor for this exception class.
     * @param message of the exception.
     * @param cause of the exception.
     */

    public StoreIsEmptyException(final String message,
                                final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for this exception class.
     * @param cause of the exception.
     */
    public StoreIsEmptyException(final Throwable cause) {
        super(cause);
    }
}
