package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;
import by.epam.information_handling.reader.Reader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The test class for {@link ParagraphParser}.
 */
public class ParagraphParserTest {

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
                        + "of Lorem Ipsum."},
                {"It is a long established fact that a "
                        + "reader will be distracted by "
                        + "the readable content of a page "
                        + "when looking at its layout. "
                        + "The point of using "
                        + "(~71&(2&3|(3|2&1>>2|2)&2)|10&2))|78 "
                        + "Ipsum is that it has a more-or-less "
                        + "normal distribution of letters, "
                        + "as opposed to using (Content here), "
                        + "content here', making it look like "
                        + "readable English."},
                {"It is (^5|1&2<<(2|5>>2&71))|1200 established "
                        + "fact that a reader will be of a page "
                        + "when looking at its layout."},
                {"Bye."}};
    }

    /**
     * Testing handleRequest() method.
     * @param paragraph is paragraph that should be parsed.
     */
    @Test (dataProvider = "test1")
    public void testHandleRequest(String paragraph) {
        ParagraphParser parser = new ParagraphParser();
        Component paragraphComponent = parser.handleRequest(paragraph);

        int counter = 0;
        while (counter < paragraphComponent.getNumberOfChildren()) {
            Component childComponent = (Component) paragraphComponent.getChild(counter);
            String value = (String) childComponent.getValue();
            System.out.println(value);
            counter++;
        }
    }
}
