package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;
import by.epam.information_handling.entity.Composite;

/**
 * This class is for parsing sentences of text.
 */
public class SentenceParser implements BaseParser {
    /**
     * This method creates an instance of {@link Composite} class that stores
     * an input value of sentence and sends a request for the handling
     * of parts of the sentence to other parsers.
     * @param sentence is a sentence that needs to be handled.
     * @return the {@link Composite} class object that contains handled value.
     */
    public Component handleRequest(final String sentence) {

        Component sentenceComponent = new Composite(sentence);
        String[] lexemes = sentence.split(" ");
        LexemeParser lexemeParser = new LexemeParser();

        int counter = 0;
        while (counter < lexemes.length) {
            Component childComponent
                    = lexemeParser.handleRequest(lexemes[counter]);
            sentenceComponent.add(childComponent);
            counter++;
        }
        return sentenceComponent;
    }
}
