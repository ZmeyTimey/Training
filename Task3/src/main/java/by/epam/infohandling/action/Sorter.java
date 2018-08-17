package by.epam.infohandling.action;

import by.epam.infohandling.entity.Component;

/**
 * This interface is for realization sorting components of text.
 */
public interface Sorter {

    /**
     * Method sorts some components of an input text.
     * @param text is an input text.
     */
    void sort(Component text);
}
