package by.epam.greenhouse.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * This class is for XML-file validating.
 */
public class Validator {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Validator.class);

    /**
     * Methods validates xml file against xsd schema.
     * @param filePath is a path to the file that should be validated.
     * @param schemaPath is a path to the xsd-scheme to the xml-file.
     */
    public static void validate(final String filePath,
                                  final String schemaPath) {

        File fileXML = new File(filePath);
        File schemaFile = new File(schemaPath);

        Schema schema;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);

        try {
            schema = factory.newSchema(schemaFile);
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            parserFactory.setSchema(schema);
            SAXParser parser = parserFactory.newSAXParser();
            DefaultHandler handler;

                handler = new ErrorHandler();
                parser.parse(fileXML, handler);

        } catch (SAXException e) {

            if (LOGGER.isFatalEnabled()) {
                LOGGER.fatal(
                        "Enable to create schema for xml-file validation)");
            }
        } catch (ParserConfigurationException e) {

            if (LOGGER.isFatalEnabled()) {
                LOGGER.fatal(
                        "An error occurred while creating the parser object");
            }
        } catch (IOException e) {
            if (LOGGER.isFatalEnabled()) {
                LOGGER.fatal("An error in parser operation occurred ");
            }
        }
    }
}
