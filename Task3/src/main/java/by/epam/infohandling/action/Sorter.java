package by.epam.infohandling.action;

import by.epam.infohandling.entity.Component;

import java.util.List;

/**
 * This interface is for realization sorting components of text.
 */
public interface Sorter {

    /**
     * Method sorts some components of an input text.
     * @param text is an input text.
     * @return the list of sorted {@link Component} instances.
     */
    List<Component> sort(Component text);
}
