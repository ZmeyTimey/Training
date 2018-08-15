package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;
import by.epam.information_handling.entity.Composite;

public class WordParser implements BaseParser {
    public Component handleRequest(final String word) {
        Component wordComponent = new Composite(word);
        return wordComponent;
    }
}
