package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import by.epam.infohandling.entity.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is for parsing paragraphs of text.
 */
public class ParagraphParser implements Parser {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(WordParser.class);

    /**
     * This method creates an instance of {@link Composite} class that stores
     * an input value of paragraph and sends a request for the handling
     * of parts of the paragraph to other parsers.
     * @param paragraph is a paragraph that needs to be parsed.
     * @return the {@link Composite} class object that contains
     * paragraph value and a list of it's components.
     */
    public Component handleRequest(final String paragraph) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Paragraph\n" + "'" + paragraph + "'"
                    + "\nis being parsed.");
        }

        final Pattern PATTERN = java.util.regex.Pattern.compile(
                "[\\w~><&^|(),'\\-\\s]+[!?.]+\\s*");
        final Matcher MATCHER = PATTERN.matcher(paragraph);

        Component paragraphComponent = new Composite(paragraph);
        SentenceParser sentenceParser = new SentenceParser();

        while (MATCHER.find()) {
            String sentence = MATCHER.group();
            Component childComponent = sentenceParser.handleRequest(sentence);
            paragraphComponent.add(childComponent);

            restoreParagraph(paragraphComponent);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Component " + "'" + sentence + "'" + " added");
            }
        }

        return paragraphComponent;
    }

    /**
     * This method restores the parsed paragraph.
     * @param paragraphComponent is a {@link Component} instance
     *                      of the parsed sentence.
     */
    private void restoreParagraph(final Component paragraphComponent) {

        StringBuilder builder = new StringBuilder();

        int numberOfSentences
                = paragraphComponent.getNumberOfChildren();

        for (int counter = 0;
             counter < numberOfSentences;
             counter++) {

            Component sentenceComponent
                    = (Component) paragraphComponent.getChild(counter);
            String sentence
                    = (String) sentenceComponent.getValue();

            builder.append(sentence);

            if (!(counter == (numberOfSentences - 1))) {
                builder.append(" ");
            }
        }

        String restoredSentence = builder.toString();
        paragraphComponent.setValue(restoredSentence);
    }
}
