package by.epam.infohandling.action;

import by.epam.infohandling.entity.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for sorting sentences.
 */
public class SentenceSorter implements Sorter {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ParagraphSorter.class);

    /**
     * This method sorts all the sentences in the text by the length
     * of lexemes they contain.
     *
     * @param text is an input text.
     */
    public List<Component> sort(final Component text) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Sentences of the text are being sorted.");
        }

        List<Component> sentenceList = createSentenceList(text);

        int numberOfSentences = sentenceList.size();

        for (int i = numberOfSentences - 1; i > 0; i--) {
            
            for (int j = 0; j < i; j++) {

                Component sentenceComponent = sentenceList.get(j);
                Component nextSentenceComponent = sentenceList.get(j + 1);

                int lexemesLength = calculateLength(sentenceComponent);
                int nextLexemesLength = calculateLength(nextSentenceComponent);

                if (lexemesLength > nextLexemesLength) {

                    sentenceList.set(j, nextSentenceComponent);
                    sentenceList.set(j + 1, sentenceComponent);
                }
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Sorting completed.");
        }

        return sentenceList;
    }

    /**
     * This method creates a list of sentences of the text.
     * @param text is {@link Component} instance which contains text.
     * @return a list of {@link Component} instances with sentences of the text.
     */
    private List<Component> createSentenceList(final Component text) {

        List<Component> sentenceList = new ArrayList<>();

        int numberOfParagraphs = text.getNumberOfChildren();

        for (int paragraphCounter = 0;
             paragraphCounter < numberOfParagraphs;
             paragraphCounter++) {

            Component paragraph
                    = (Component) text.getChild(paragraphCounter);

            int numberOfSentences = paragraph.getNumberOfChildren();

            for (int sentenceCounter = 0;
                 sentenceCounter < numberOfSentences;
                 sentenceCounter++) {

                Component sentence
                        = (Component) paragraph.getChild(sentenceCounter);

                sentenceList.add(sentence);
            }
        }
        return sentenceList;
    }

    /**
     * This method calculates the summary length of all the lexemes in sentence.
     * @param sentence is a sentence from the text.
     * @return the summary length of all the lexemes in sentence.
     */
    private int calculateLength(final Component sentence) {

        int allLexemesLength = 0;
        int lexemeNumber = sentence.getNumberOfChildren();

        for (int counter = 0; counter < lexemeNumber; counter++) {

            Component lexemeComponent = (Component) sentence.getChild(counter);
            Object lexeme = lexemeComponent.getValue();

            if (lexeme.getClass() == char.class) {
                lexeme = lexeme.toString();
            }

            int lexemeLength = ((String) lexeme).length();
            allLexemesLength += lexemeLength;
        }

        return allLexemesLength;
    }
}
