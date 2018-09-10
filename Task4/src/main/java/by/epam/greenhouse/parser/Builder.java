package by.epam.greenhouse.parser;

import by.epam.greenhouse.entity.Flower;

import java.io.InputStream;
import java.util.Set;

/**
 * The interface for realization the Builder pattern.
 */
public interface Builder {

    /**
     * Builds a set of entities from xml-file.
     * @param fileInputStream is xml-file input stream.
     */
    void buildSet(InputStream fileInputStream);

    /**
     * Gets a set of entities.
     * @return a set of entities.
     */
    Set<Flower> getEntities();
}
