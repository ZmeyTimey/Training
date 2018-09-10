package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import by.epam.infohandling.entity.Composite;
import by.epam.infohandling.entity.Leaf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is for parsing lexemes which are not bit expressions.
 */
public class NotBitExpressionLexemeParser implements Parser {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NotBitExpressionLexemeParser.class);

    /**
     * This method creates an instance of {@link Component} that stores
     * an input value of lexeme and sends a request for it's handling
     * to other parsers.
     * @param lexeme is a lexeme that should be parsed.
     * @return {@link Composite} or {@link Leaf} class instance that contains
     * lexeme value and list of it's components.
     */
    public Component handleRequest(final String lexeme) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Lexeme "
                    + "'" + lexeme + "'"
                    + " is being parsed.");
        }

        final Pattern PATTERN = Pattern.compile("[,.!?()]+");
        final Matcher MATCHER = PATTERN.matcher(lexeme);

        Component lexemeComponent = new Composite(lexeme);

        if (lexeme.equals("-")) {
            char dash = lexeme.charAt(0);
            Component childComponent = new Leaf(dash);
            lexemeComponent.add(childComponent);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Component " + "'" + dash + "'" + " added");
            }

        } else {
            Parser wordParser = new WordParser();

            if (MATCHER.find()) {
                tokenizeLexeme(lexemeComponent);
            } else {
                Component childComponent = wordParser.handleRequest(lexeme);
                lexemeComponent.add(childComponent);

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Component "
                            + "'" + lexeme + "'" + " added");
                }
            }
        }

        return lexemeComponent;
    }

    /**
     * This method separates a word from punctuation marks in an input lexeme
     * and adds all the words and punctuation marks into {@link Component}
     * instance's children list.
     * @param lexemeComponent is an input {@link Component} instance
     * that contains lexeme.
     */
    private void tokenizeLexeme(final Component lexemeComponent) {

        String word = (String) lexemeComponent.getValue();
        Parser wordParser = new WordParser();
        List<Object> punctuationMarkList = new ArrayList<>();

        char firstChar = word.charAt(0);

        if (firstChar == '(') {
            Component childComponent = new Leaf(firstChar);
            lexemeComponent.add(childComponent);
            word = word.substring(1);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Component " + "'" + firstChar + "'" + " added");
            }
        }

        int lastCharNumber = word.length() - 1;
        char lastChar = word.charAt(lastCharNumber);

        while ((lastChar == '?') || (lastChar == '!') || (lastChar == '.')
                || (lastChar == ')') || (lastChar == ',')) {

            char previousChar = word.charAt(lastCharNumber - 1);

            if (lastChar == '.' && previousChar == '.') {
                String ellipsis = "...";
                punctuationMarkList.add(ellipsis);
                word = word.replaceAll("\\.", "");

            } else {
                punctuationMarkList.add(lastChar);
                word = word.substring(0, lastCharNumber);
            }

            lastCharNumber = word.length() - 1;
            lastChar = word.charAt(lastCharNumber);
        }

        Component wordComponent = wordParser.handleRequest(word);
        lexemeComponent.add(wordComponent);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Component " + "'" + word + "'" + " added");
        }

        int counter = punctuationMarkList.size() - 1;
        while (counter >= 0) {
            Object punctuationMark = punctuationMarkList.get(counter);
            Component punctuationMarkComponent = new Leaf(punctuationMark);
            lexemeComponent.add(punctuationMarkComponent);
            counter--;

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Component "
                        + "'" + punctuationMark + "'" + " added");
            }
        }
    }
}
