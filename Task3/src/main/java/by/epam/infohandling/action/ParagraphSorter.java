package by.epam.infohandling.action;

import by.epam.infohandling.entity.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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
     * @param text is a text that should be sorted.
     * @return a list of sorted paragraphs.
     */
    public List<Component> sort(final Component text) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Paragraphs of the text are being sorted.");
        }

        List<Component> paragraphList = createParagraphList(text);

        int numberOfParagraphs = paragraphList.size();

        for (int i = numberOfParagraphs - 1; i > 0; i--) {

            for (int j = 0; j < i; j++) {

                Component paragraph = paragraphList.get(j);
                Component nextParagraph = paragraphList.get(j + 1);

                int numberOfSentences = paragraph.getNumberOfChildren();
                int nextNumberOfSentences = nextParagraph.getNumberOfChildren();

                if (numberOfSentences > nextNumberOfSentences) {

                    paragraphList.set(j, nextParagraph);
                    paragraphList.set(j + 1, paragraph);
                }
            }
        }

        String newTextValue = buildTextValue(text, numberOfParagraphs);
        text.setValue(newTextValue);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Sorting completed.");
        }

        return paragraphList;
    }

    /**
     * This method creates a list of paragraphs of the text.
     * @param text is {@link Component} instance which contains text.
     * @return a list of {@link Component} instances which contain
     * paragraphs of the text.
     */
    private List<Component> createParagraphList(final Component text) {

        List<Component> paragraphList = new ArrayList<>();
        int numberOfParagraphs = text.getNumberOfChildren();

        for (int paragraphCounter = 0;
             paragraphCounter < numberOfParagraphs;
             paragraphCounter++) {

            Component paragraph
                    = (Component) text.getChild(paragraphCounter);

            paragraphList.add(paragraph);
        }

        return paragraphList;
    }

    /**
     * This method builds a new value of the text after it has been sorted.
     * @param text is the text with sorted paragraphs.
     * @param numberOfParagraphs is the number of paragraphs of the text.
     * @return new text value.
     */
    private String buildTextValue(final Component text,
                                  final int numberOfParagraphs) {

        int counter = 0;
        String newTextValue;
        StringBuilder builder = new StringBuilder();

        while (counter < numberOfParagraphs) {

            Component paragraph = (Component) text.getChild(counter);
            String paragraphValue = (String) paragraph.getValue();

            if (counter != 0) {
                builder.append("\n");
            }
            builder.append(paragraphValue);
            counter++;
        }

        newTextValue = builder.toString();
        return newTextValue;
    }
}
