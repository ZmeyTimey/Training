package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;
import by.epam.information_handling.entity.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is for parsing lexemes in text.
 */
public class LexemeParser implements BaseParser {

    private boolean childIsExpression;
    public boolean getChildIsExpression() {
        return childIsExpression;
    }
    /**
     * This method creates an instance of {@link Composite} class that stores
     * an input value of lexeme and sends a request for it's handling
     * to other parsers.
     * @param lexeme is a lexeme that needs to be handled.
     * @return the {@link Composite} class object that contains handled value.
     */
    public Component handleRequest(final String lexeme) {

        final Pattern PATTERN = Pattern.compile("[\\d~><&|^()]*");
        final Matcher MATCHER = PATTERN.matcher(lexeme);

        Component lexemeComponent = new Composite(lexeme);
        BaseParser expressionParser = new ExpressionParser();
        BaseParser notExpressionLexemeParser
                = new NotExpressionLexemeParser();

        if (MATCHER.matches()) {
            childIsExpression = true;
            Component childComponent = expressionParser.handleRequest(lexeme);
            lexemeComponent.add(childComponent);
        } else {
            childIsExpression = false;
            Component childComponent
                    = notExpressionLexemeParser.handleRequest(lexeme);
            lexemeComponent.add(childComponent);
        }

         return lexemeComponent;
    }
}
