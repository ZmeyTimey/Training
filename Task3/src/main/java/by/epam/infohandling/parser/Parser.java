package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;

/**
 * Interface is for use in classes that handle the text parts.
 */
public interface Parser {
    /**
     * Abstract method for realization handling the text parts.
     * @param textPart is a part of text that needs to be parsed.
     * @return the {@link Component} class object that contains handled value.
     */
    Component handleRequest(String textPart);
}
