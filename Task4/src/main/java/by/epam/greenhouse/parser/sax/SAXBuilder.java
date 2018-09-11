package by.epam.greenhouse.parser.sax;

import by.epam.greenhouse.entity.Flower;
import by.epam.greenhouse.parser.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

/**
 * Type SAX xml-file parser.
 */
public class SAXBuilder implements Builder {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SAXBuilder.class);

    /**
     * Set of {@link Flower} instances.
     */
    private Set<Flower> flowers;

    /**
     * Event handler for parsing flowers information.
     */
    private Handler handler;

    /**
     * Xml reader object.
     */
    private XMLReader xmlReader;

    /**
     * Constructor for this class.
     */
    public SAXBuilder() {

        handler = new Handler();

        try {
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(handler);

        } catch (SAXException e) {
            LOGGER.fatal(
                    "Exception occurred while processing the xml-file: " + e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Flower> getEntities() {
        return flowers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildSet(final InputStream fileInputStream) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Starting SAX parser!");
        }

        try {
            xmlReader.parse(new InputSource(fileInputStream));

        } catch (SAXException e) {

            LOGGER.error("SAX parser exception: " + e);
        } catch (IOException e) {
            LOGGER.error("Input/Оutput exception: " + e);
        }

        flowers = handler.getFlowers();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Parsing is successfully completed!");
        }
    }
}
