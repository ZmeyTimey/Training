package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;
import by.epam.information_handling.entity.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is for parsing text through a splitting into a tree of elements.
 */
public class Parser {
    /**
     * Method divides a component of text of a full text
     * into constituent components.
     * @param textComponent is a divisible component of a text of a full text.
     * @param divider is a character or expression that separates the components
     *              of the text from each other.
     * @return a list of components.
     */
    private String[] divide(final String textComponent,
                                             final String divider) {
        return textComponent.split(divider);
    }

    public Component parseText(final String text) {

        Component textComponent = new Composite(text);
        String[] parts = divide(text, "\n");

        int counter = 0;
        while (counter < parts.length) {
            Component component = parseParagraph(parts[counter]);
            textComponent.add(component);
            counter++;
        }

        return textComponent;
    }

    private Component parseParagraph(final String paragraph) {

        final Pattern PATTERN = Pattern.compile("[\\w~><&^|()]+[!?.]+\\s");
        final Matcher MATCHER = PATTERN.matcher(paragraph);

        Component paragraphComponent = new Composite(paragraph);
        while (MATCHER.find()) {
            Component component = parseSentence(MATCHER.group());
            paragraphComponent.add(component);
        }

        return paragraphComponent;
    }

    private Component parseSentence(final String sentence) {
        Component sentenceComponent = new Composite(sentence);
        String[] parts = divide(sentence, "\\s+");
        int counter = 0;
        while (counter < parts.length) {
            Component childComponent = parseLexem(parts[counter]);
            sentenceComponent.add(childComponent);
            counter++;
        }

        return sentenceComponent;
    }

    private Component parseLexem(final String lexeme) {

        Component lexemeComponent = new Composite(lexeme);
        final Pattern PATTERN = Pattern.compile("[\\d~><&|^()]*");
        final Matcher MATCHER = PATTERN.matcher(lexeme);

        if (MATCHER.matches()) {
            Component childComponent = parseExpression(lexeme);
            lexemeComponent.add(childComponent);

        } else {
            if (lexeme.equals("-")) {
                Component childComponent = new Composite(lexeme);
                lexemeComponent.add(childComponent);
            } else {
                Component childComponent = parseWord(lexeme);
                lexemeComponent.add(childComponent);
            }
        }

        return lexemeComponent;
    }

    private Component parseWord(final String word) {
        Component wordComponent = new Composite(word);
        return wordComponent;
    }

    private Component parseExpression(final String expression) {
        Component expressionComponent = new Composite(expression);
        return expressionComponent;
    }
}
