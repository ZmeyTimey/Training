package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The test class for {@link WordParser}.
 */
public class WordParserTest {

    /**
     * Providing data containing words and their letters.
     * @return necessary data for the test.
     */
    @DataProvider(name = "test1")
    public static Object[][] data() {
        return new Object[][] {{"It", 'I', 't'},
                {"typesetting", 't', 'y', 'p', 'e', 's', 'e', 't', 't', 'i', 'n', 'g'},
                {"PageMaker", 'P', 'a', 'g', 'e', 'M', 'a', 'k', 'e','r'}};
    }

    /**
     * Testing handleRequest() method.
     * @param word is a word that should be parsed.
     * @param letters is an array of letters of the word.
     */
    @Test(dataProvider = "test1")
    public void testHandleRequest(final String word, Object[] letters) {
        Parser wordParser = new WordParser();
        Component wordComponent = wordParser.handleRequest(word);

        int childrenNumber = wordComponent.getNumberOfChildren();
        Object[] childrenValues = new Object[childrenNumber];
        int counter = 0;
        while (counter < childrenNumber) {
            Component childComponent = (Component) wordComponent.getChild(counter);
            Object value = childComponent.getValue();
            childrenValues[counter] = value;
            counter++;
        }
        Assert.assertEquals(childrenValues, letters);
    }
}
