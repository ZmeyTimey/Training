package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;

/**
 * Interface is for use in classes that handle the text parts.
 */
public interface BaseParser {
    /**
     * Abstract method for realization handling the text parts.
     * @param textPart is a part of text that needs to be handled.
     * @return the {@link Component} class object that contains handled value.
     */
    Component handleRequest(String textPart);
}
