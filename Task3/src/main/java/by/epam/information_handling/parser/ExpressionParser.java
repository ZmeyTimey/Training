package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;
import by.epam.information_handling.entity.Composite;

public class ExpressionParser implements BaseParser {
    public Component handleRequest(final String expression) {
        Component expressionComponent = new Composite(expression);
        return expressionComponent;
    }
}
