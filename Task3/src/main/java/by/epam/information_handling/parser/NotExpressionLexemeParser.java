package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;
import by.epam.information_handling.entity.Composite;

/**
 * This class is for parsing lexemes which are not expressions.
 */
public class NotExpressionLexemeParser implements BaseParser {
    public Component handleRequest(final String lexeme) {
        Component lexemeComponent = new Composite(lexeme);
        return lexemeComponent;
    }
}
