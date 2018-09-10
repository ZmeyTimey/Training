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
        int numberOfLexemes = lexemes.length;

        LexemeParser lexemeParser = new LexemeParser();

        int counter = 0;
        while (counter < numberOfLexemes) {

            String lexeme = lexemes[counter];
            Component childComponent
                    = lexemeParser.handleRequest(lexeme);
            sentenceComponent.add(childComponent);

            restoreSentence(sentenceComponent);

            counter++;

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Component "
                        + "'" + lexeme + "'" + " added");
            }
        }

        return sentenceComponent;
    }

    /**
     * This method restores the parsed sentence.
     * @param sentenceComponent is a {@link Component} instance
     *                      of the parsed sentence.
     */
    private void restoreSentence(final Component sentenceComponent) {

        StringBuilder builder = new StringBuilder();

        int numberOfLexemes = sentenceComponent.getNumberOfChildren();

        for (int counter = 0;
             counter < numberOfLexemes;
             counter++) {

            Component lexemeComponent
                    = (Component) sentenceComponent.getChild(counter);
            String lexeme
                    = (String) lexemeComponent.getValue();

            if (!(counter == 0)) {
                builder.append(" ");
            }

            builder.append(lexeme);
        }

        String restoredSentence = builder.toString();
        sentenceComponent.setValue(restoredSentence);
    }
}
