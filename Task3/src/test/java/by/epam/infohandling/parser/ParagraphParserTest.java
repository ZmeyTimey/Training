package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The test class for {@link ParagraphParser}.
 */
public class ParagraphParserTest {

    /**
     * Providing data containing paragraphs and their sentences.
     * @return necessary data for the test.
     */
    @DataProvider(name = "test1")
    public static Object[][] paragraphs() {
        return new Object[][]
                {{"It has survived - not only (five) centuries, "
                        + "but also the leap into 13<<2 electronic "
                        + "typesetting, remaining 3>>5 essentially "
                        + "~6&9|(3&4) unchanged. It was popularised "
                        + "in the 5|(1&2&(3|4&(^5|6&47)|3)|2)|1) "
                        + "with the release of Letraset sheets "
                        + "containing Lorem Ipsum passages, and more "
                        + "recently with desktop publishing software "
                        + "like Aldus PageMaker including versions "
                        + "of Lorem Ipsum.",
                "It has survived - not only (five) centuries, but also "
                        + "the leap into 13<<2 electronic typesetting, "
                        + "remaining 3>>5 essentially ~6&9|(3&4) unchanged. ",
                "It was popularised in the 5|(1&2&(3|4&(^5|6&47)|3)|2)|1) " +
                        "with the release of Letraset sheets containing " +
                        "Lorem Ipsum passages, and more recently with " +
                        "desktop publishing software like Aldus PageMaker " +
                        "including versions of Lorem Ipsum."},

                {"It is (^5|1&2<<(2|5>>2&71))|1200 established "
                        + "fact that a reader will be of a page "
                        + "when looking at its layout.",
                "It is (^5|1&2<<(2|5>>2&71))|1200 established "
                        + "fact that a reader will be of a page "
                        + "when looking at its layout."}};
    }

    /**
     * Testing handleRequest() method.
     * @param paragraph is paragraph that should be parsed.
     * @param sentences is an array of sentences of the paragraph.
     */
    @Test (dataProvider = "test1")
    public void testHandleRequest(String paragraph, String[] sentences) {

        Parser paragraphParser = new ParagraphParser();
        Component paragraphComponent = paragraphParser.handleRequest(paragraph);

        int childrenNumber = paragraphComponent.getNumberOfChildren();
        Object[] childrenValues = new String[childrenNumber];

        int counter = 0;
        while (counter < childrenNumber) {
            Component childComponent = (Component) paragraphComponent.getChild(counter);
            String value = (String) childComponent.getValue();
            childrenValues[counter] = value;
            counter++;
        }

        Assert.assertEquals(childrenValues, sentences);
    }
}
