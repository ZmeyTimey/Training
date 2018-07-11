package by.epam.figures.exception;

/**
 * {@link URIConvertException} is thrown when a URL link is doesn't convert to URI.
 */
public class URIConvertException extends InvalidFilePathException {
    public URIConvertException() {
    }

    public URIConvertException(String message) {
        super(message);
    }

    public URIConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public URIConvertException(Throwable cause) {
        super(cause);
    }
}
