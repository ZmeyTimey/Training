package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import by.epam.infohandling.entity.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is for parsing lexemes from text.
 */
public class LexemeParser implements Parser {
    /**
     * This variable indicates whether the parsing lexeme is a bit expression.
     * Introduced for ease of testing this class.
     */
    private boolean isBitExpression;

    /**
     * The getter for isBitExpression variable.
     * @return a value of isBitExpression.
     */
    boolean getBitExpression() {
        return isBitExpression;
    }

    /**
     * This method creates an instance of {@link Composite} class that stores
     * an input value of lexeme and sends a request for it's handling
     * to other parsers.
     * @param lexeme is a lexeme that needs to be parsed.
     * @return the {@link Composite} class object that contains
     * lexeme value and a list of it's components.
     */
    public Component handleRequest(final String lexeme) {

        final Pattern PATTERN = Pattern.compile("[\\d~><&|^()]*");
        final Matcher MATCHER = PATTERN.matcher(lexeme);

        Component lexemeComponent;
        Parser expressionParser = new BitExpressionParser();
        Parser notExpressionLexemeParser
                = new NotBitExpressionLexemeParser();

        if (MATCHER.matches()) {
            isBitExpression = true;
            lexemeComponent = expressionParser.handleRequest(lexeme);
        } else {
            isBitExpression = false;
            lexemeComponent = notExpressionLexemeParser.handleRequest(lexeme);
        }

         return lexemeComponent;
    }
}
