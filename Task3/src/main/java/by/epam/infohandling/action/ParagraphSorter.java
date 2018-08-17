package by.epam.infohandling.action;

import by.epam.infohandling.entity.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is for sorting paragraphs by the number of sentences.
 */
public class ParagraphSorter implements Sorter {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ParagraphSorter.class);

    /**
     * Method sorts paragraphs of an input text by the number of sentences.
     * @param text is an input text.
     */
    public void sort(final Component text) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Paragraphs of the text are being sorted.");
        }

        int numberOfParagraphs = text.getNumberOfChildren();
        int i = numberOfParagraphs - 1;

        while (i > 0) {
            int j = 0;

            while (j < i) {

                Component paragraph = (Component) text.getChild(j);
                Component nextParagraph = (Component) text.getChild(j + 1);

                int numberOfSentences = paragraph.getNumberOfChildren();
                int nextNumberOfSentences = nextParagraph.getNumberOfChildren();

                if (numberOfSentences > nextNumberOfSentences) {

                    text.replace(j, nextParagraph);
                    text.replace(j + 1, paragraph);
                }
                j++;
            }
            i--;
        }


        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Sorting completed.");
        }
    }
}
