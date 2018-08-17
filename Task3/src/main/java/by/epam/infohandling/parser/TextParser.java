package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import by.epam.infohandling.entity.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is for parsing text.
 */
public class TextParser implements Parser {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(WordParser.class);

    /**
     * This method creates an instance of {@link Composite} class that stores
     * an input value of text and sends a request for the handling
     * of parts of the text to other parsers.
     * @param text is a text that needs to be parsed.
     * @return the {@link Composite} class object that contains
     * text value and a list of it's components.
     */
    public Component handleRequest(final String text) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The text" + " is being parsed.");
        }

        Component textComponent = new Composite(text);
        String[] paragraphs = text.split("\n");
        ParagraphParser paragraphParser = new ParagraphParser();

        int counter = 0;
        while (counter < paragraphs.length) {
            Component childComponent
                    = paragraphParser.handleRequest(paragraphs[counter]);
            textComponent.add(childComponent);
            counter++;

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Paragraph " + (counter + 1) + " added");
            }
        }

        return textComponent;
    }
}
