package by.epam.port.exception;

/**
 * This exception is thrown when the intended for the Ship class
 * instance creation data is not valid.
 */
public class InvalidShipDataException extends Exception {
    /**
     * Constructor for this exception class.
     */
    public InvalidShipDataException() {
    }

    /**
     * Constructor for this exception class.
     * @param message of the exception.
     */
    public InvalidShipDataException(final String message) {
        super(message);
    }

    /**
     * Constructor for this exception class.
     * @param message of the exception.
     * @param cause of the exception.
     */
    public InvalidShipDataException(final String message,
                                    final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for this exception class.
     * @param cause of the exception.
     */
    public InvalidShipDataException(final Throwable cause) {
        super(cause);
    }
}
