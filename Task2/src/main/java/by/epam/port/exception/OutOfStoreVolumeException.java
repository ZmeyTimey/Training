package by.epam.port.exception;

/**
 * This exception is thrown when during loading the nominal volume
 * of the store is exceeded.
 */
public class OutOfStoreVolumeException extends LoadingException {

    /**
     * Constructor for this exception class.
     */
    public OutOfStoreVolumeException() {
    }

    /**
     * Constructor for this exception class.
     * @param message of the exception.
     */
    public OutOfStoreVolumeException(final String message) {
        super(message);
    }

    /**
     * Constructor for this exception class.
     * @param message of the exception.
     * @param cause of the exception.
     */

    public OutOfStoreVolumeException(final String message,
                                final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for this exception class.
     * @param cause of the exception.
     */
    public OutOfStoreVolumeException(final Throwable cause) {
        super(cause);
    }
}
