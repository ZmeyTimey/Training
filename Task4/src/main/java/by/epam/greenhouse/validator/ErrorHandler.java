package by.epam.greenhouse.validator;

import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


/**
 * This class is for handling errors occurring during xml-file validation.
 */
public class ErrorHandler extends DefaultHandler {

    /**
     * {@link Logger} class object for making logs.
     */
    private final Logger LOGGER;

    /**
     * Constructor for this class.
     * @throws IOException is thrown when an input/output error occurs.
     */
    public ErrorHandler() throws IOException {

        LOGGER = LogManager.getLogger(ErrorHandler.class.getName());
        }

    public void warning(final SAXParseException e) {

        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn(getLineAddress(e) + "-" + e.getMessage());
        }
    }

    public void error(final SAXParseException e) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(getLineAddress(e) + " - " + e.getMessage());
        }
    }

    public void fatalError(final SAXParseException e) {
        if (LOGGER.isFatalEnabled()) {
            LOGGER.fatal(getLineAddress(e) + " - " + e.getMessage());
        }
    }

    private String getLineAddress(final SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
