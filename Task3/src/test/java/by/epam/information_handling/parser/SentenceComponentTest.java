package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The test class for {@link SentenceParser}.
 */
public class SentenceComponentTest {

    @DataProvider(name = "test1")
    public static Object[][] sentences() {
        return new Object[][]
                {{"It has survived - not only (five) " +
                        "centuries, but also the leap " +
                        "into 13<<2 electronic typesetting, " +
                        "remaining 3>>5 essentially " +
                        "~6&9|(3&4) unchanged."},
                {"It was popularised in the " +
                        "5|(1&2&(3|4&(^5|6&47)|3)|2)|1) " +
                        "with the release of Letraset " +
                        "sheets containing Lorem Ipsum " +
                        "passages, and more recently with " +
                        "desktop publishing software like " +
                        "Aldus PageMaker including versions " +
                        "of Lorem Ipsum."}};
    }

    /**
     * Testing handleRequest() method.
     * @param sentence is paragraph that should be parsed.
     */
    @Test(dataProvider = "test1")
    public void testHandleRequest(String sentence) {
        SentenceParser parser = new SentenceParser();
        Component sentenceComponent = parser.handleRequest(sentence);

        int counter = 0;
        while (counter < sentenceComponent.getNumberOfChildren()) {
            Component childComponent = (Component) sentenceComponent.getChild(counter);
            String value = (String) childComponent.getValue();
            System.out.println(value);
            counter++;
        }
    }
}
