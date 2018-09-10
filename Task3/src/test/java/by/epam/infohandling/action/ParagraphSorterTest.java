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
 * The test class for {@link ParagraphSorter}
 */
public class ParagraphSorterTest {

    /**
     * The text that should be sorted.
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
     * Providing data which contains a list of paragraphs of the text that should be
     * received after sorting.
     * @return a list of paragraphs placed in the order they should be placed after sorting.
     */
    @DataProvider(name = "test1")
    public static Object[][] sortedParagraphs() {
        return new Object[][]
                {{"It is 1215 established "
                        + "fact that a reader will be of a page "
                        + "when looking at its layout.",
                "Bye.",
                "It has survived - not only (five) centuries, "
                        + "but also the leap into 52 electronic "
                        + "typesetting, remaining 0 essentially "
                        + "9 unchanged. It was popularised "
                        + "in the 7 "
                        + "with the release of Letraset sheets "
                        + "containing Lorem Ipsum passages, and more "
                        + "recently with desktop publishing software "
                        + "like Aldus PageMaker including versions "
                        + "of Lorem Ipsum.",
                "It is a long established fact that a reader will be "
                        + "distracted by the readable content of a page "
                        + "when looking at its layout. The point of "
                        + "using 78 "
                        + "Ipsum is that it has a more-or-less normal "
                        + "distribution of letters, as opposed to using "
                        + "(Content here), content here', making it "
                        + "look like readable English."}};
    }

    /**
     * Testing sort() method.
     * @param paragraphs is a list of paragraphs placed in the order
     *                   in which they should be placed after sorting.
     */
    @Test(dataProvider = "test1")
    public void sortTest(String[] paragraphs) {

        Sorter paragraphSorter = new ParagraphSorter();
        Parser textParser = new TextParser();

        Component textComponent = textParser.handleRequest(text);

        List<Component> sortedParagraphsList = paragraphSorter.sort(textComponent);
        int numberOfParagraphs = sortedParagraphsList.size();
        String[] sortedParagraphs = new String[numberOfParagraphs];

        for (int counter = 0; counter < numberOfParagraphs; counter++) {

            Component paragraph = sortedParagraphsList.get(counter);
            String value = (String) paragraph.getValue();
            sortedParagraphs[counter] = value;
        }

        Assert.assertEquals(sortedParagraphs, paragraphs);
    }
}
