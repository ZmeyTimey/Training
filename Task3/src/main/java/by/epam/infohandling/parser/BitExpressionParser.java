package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import by.epam.infohandling.entity.Composite;

public class BitExpressionParser implements Parser {
    public Component handleRequest(final String expression) {
        Component expressionComponent = new Composite(expression);
        return expressionComponent;
    }
}
