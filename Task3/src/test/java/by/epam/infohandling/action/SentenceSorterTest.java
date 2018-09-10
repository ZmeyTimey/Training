package by.epam.infohandling.action;

import by.epam.infohandling.entity.Component;
import by.epam.infohandling.parser.Parser;
import by.epam.infohandling.parser.TextParser;
import by.epam.infohandling.reader.Reader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * The test class for {@link SentenceSorter}.
 */
public class SentenceSorterTest {

    /**
     * The text that should be parsed.
     */
    private String text;

    /**
     * Reading the text from file.
     */
    @BeforeMethod
    public final void beforeMethod() {
        Reader reader = new Reader("/Text.txt");
        reader.readFile();
        text = reader.getReadText();
    }

    /**
     * Providing data which contains a list of sentences of the text that should be
     * received after sorting.
     * @return a list of sentences placed in the order they
     * should be placed after sorting.
     */
    @DataProvider(name = "test1")
    public static Object[][] sentences() {
        return new Object[][]
                {{"Bye.",
                "It is 1215 established "
                        + "fact that a reader will be of a page "
                        + "when looking at its layout.",
                "It is a long established fact that a reader will be "
                        + "distracted by the readable content of a page "
                        + "when looking at its layout.",
                "It has survived - not only (five) centuries, "
                        + "but also the leap into 52 electronic "
                        + "typesetting, remaining 0 essentially "
                        + "9 unchanged.",
                "The point of "
                        + "using 78 "
                        + "Ipsum is that it has a more-or-less normal "
                        + "distribution of letters, as opposed to using "
                        + "(Content here), content here', making it "
                        + "look like readable English.",
                "It was popularised "
                        + "in the 7 "
                        + "with the release of Letraset sheets "
                        + "containing Lorem Ipsum passages, and more "
                        + "recently with desktop publishing software "
                        + "like Aldus PageMaker including versions "
                        + "of Lorem Ipsum."
                }};
    }

    /**
     * Testing sort() method.
     * @param sentences is a list of sentences placed in the order
     *                   in which they should be placed after sorting.
     */
    @Test(dataProvider = "test1")
    public void sortTest(String[] sentences) {

        Sorter sentenceSorter = new SentenceSorter();
        Parser textParser = new TextParser();

        Component textComponent = textParser.handleRequest(text);

        List<Component> sortedSentencesList = sentenceSorter.sort(textComponent);
        int numberOfParagraphs = sortedSentencesList.size();
        String[] sortedSentencies = new String[numberOfParagraphs];

        for (int counter = 0; counter < numberOfParagraphs; counter++) {

            Component sentence = sortedSentencesList.get(counter);
            String value = (String) sentence.getValue();
            sortedSentencies[counter] = value;
        }

        Assert.assertEquals(sortedSentencies, sentences);
    }
}
