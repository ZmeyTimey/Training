package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;
import by.epam.information_handling.entity.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is for parsing paragraphs of text.
 */
public class ParagraphParser implements BaseParser {

    /**
     * This method creates an instance of {@link Composite} class that stores
     * an input value of paragraph and sends a request for the handling
     * of parts of the paragraph to other parsers.
     * @param paragraph is a paragraph that needs to be handled.
     * @return the {@link Composite} class object that contains handled value.
     */
    public Component handleRequest(final String paragraph) {

        final Pattern PATTERN = java.util.regex.Pattern.compile(
                "[\\w~><&^|(),'\\-\\s]+[!?.]+\\s*");
        final Matcher MATCHER = PATTERN.matcher(paragraph);

        Component paragraphComponent = new Composite(paragraph);
        SentenceParser sentenceParser = new SentenceParser();

        while (MATCHER.find()) {
            String sentence = MATCHER.group();
            Component childComponent = sentenceParser.handleRequest(sentence);
            paragraphComponent.add(childComponent);
        }

        return paragraphComponent;
    }
}
