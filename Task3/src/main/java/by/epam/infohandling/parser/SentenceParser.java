package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import by.epam.infohandling.entity.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is for parsing sentences of text.
 */
public class SentenceParser implements Parser {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(WordParser.class);

    /**
     * This method creates an instance of {@link Composite} class that stores
     * an input value of sentence and sends a request for the handling
     * of parts of the sentence to other parsers.
     * @param sentence is a sentence that needs to be parsed.
     * @return the {@link Composite} class object that contains
     * sentence value and a list of it's components.
     */
    public Component handleRequest(final String sentence) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Sentence " + "'" + sentence + "'"
                    + " is being parsed.");
        }

        Component sentenceComponent = new Composite(sentence);
        String[] lexemes = sentence.split(" ");
        LexemeParser lexemeParser = new LexemeParser();

        int counter = 0;
        while (counter < lexemes.length) {
            String lexeme = lexemes[counter];
            Component childComponent
                    = lexemeParser.handleRequest(lexeme);
            sentenceComponent.add(childComponent);
            counter++;

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Component " + "'" + lexeme + "'" + " added");
            }
        }

        return sentenceComponent;
    }
}
