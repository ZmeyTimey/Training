package by.epam.figures.exception;

import java.io.IOException;

/**
 * Created by Тимей on 04.07.2018.
 *
 */
public class FileIsAbsentException extends FileReadingException {
    public FileIsAbsentException() {
    }

    public FileIsAbsentException(String message) {
        super(message);
    }

    public FileIsAbsentException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIsAbsentException(Throwable cause) {
        super(cause);
    }
}
