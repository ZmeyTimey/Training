package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;
import by.epam.information_handling.entity.Composite;

/**
 * This class is for parsing text.
 */
public class TextParser implements BaseParser {

    /**
     * This method creates an instance of {@link Composite} class that stores
     * an input value of text and sends a request for the handling
     * of parts of the text to other parsers.
     * @param text is a text that needs to be handled.
     * @return the {@link Composite} class object that contains handled value.
     */
    public Component handleRequest(final String text) {

        Component textComponent = new Composite(text);
        String[] paragraphs = text.split("\n");
        ParagraphParser paragraphParser = new ParagraphParser();

        int counter = 0;
        while (counter < paragraphs.length) {
            Component childComponent
                    = paragraphParser.handleRequest(paragraphs[counter]);
            textComponent.add(childComponent);
            counter++;
        }

        return textComponent;
    }
}
