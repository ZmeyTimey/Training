package by.epam.port.exception;

/**
 * {@link URIConvertException} is thrown when a URL link
 * is doesn't convert to URI.
 */
public class URIConvertException extends InvalidFilePathException {
    /**
     * Constructor for {@link InvalidFilePathException}.
     */
    public URIConvertException() {
    }

    /**
     * Constructor for {@link InvalidFilePathException}.
     * @param message of the exception.
     */
    public URIConvertException(final String message) {
        super(message);
    }

    /**
     * Constructor for {@link InvalidFilePathException}.
     * @param message of the exception.
     * @param cause of the exception.
     */
    public URIConvertException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for {@link InvalidFilePathException}.
     * @param cause of the exception.
     */
    public URIConvertException(final Throwable cause) {
        super(cause);
    }
}
