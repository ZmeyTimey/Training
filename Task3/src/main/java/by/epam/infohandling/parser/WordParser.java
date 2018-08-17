package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import by.epam.infohandling.entity.Composite;
import by.epam.infohandling.entity.Leaf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is for parsing words from text.
 */
public class WordParser implements Parser {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(WordParser.class);

    /**
     * This method creates an instance of {@link Composite} class that stores
     * an input value of word and creates {@link Leaf} instances for every
     * letter of this word.
     * @param word is a word that needs to be parsed.
     * @return the {@link Composite} class instance that contains
     * word value and a list of it's letters.
     */
    public Component handleRequest(final String word) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Word " + "'" + word + "'" + " is being parsed.");
        }

        Component wordComponent = new Composite(word);

        int counter = 0;
        while (counter < word.length()) {
            char letter = word.charAt(counter);
            Component childComponent = new Leaf(letter);
            wordComponent.add(childComponent);
            counter++;

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Component " + "'" + letter + "'" + " added");
            }
        }

        return wordComponent;
    }
}
