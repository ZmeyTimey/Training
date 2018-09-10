package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The test class for {@link SentenceParser}.
 */
public class SentenceParserTest {

    /**
     * Providing data containing sentences and their lexemes.
     * @return necessary data for the test.
     */
    @DataProvider(name = "test1")
    public static Object[][] sentences() {
        return new Object[][]
                {{"It has survived - not only (five) " +
                        "centuries, but also the leap " +
                        "into 13<<2 electronic typesetting, " +
                        "remaining 3>>5 essentially " +
                        "~6&9|(3&4) unchanged.",
                "It", "has", "survived", "-", "not", "only", "(five)",
                "centuries,", "but", "also", "the", "leap", "into",
                "52", "electronic", "typesetting,", "remaining",
                "0", "essentially", "9", "unchanged."},

                {"It was popularised in the " +
                        "5|(1&2&(3|4&(10^5|6&47)|3)|2)|1 " +
                        "with the release of Letraset " +
                        "sheets containing Lorem Ipsum " +
                        "passages, and more recently with " +
                        "desktop publishing software like " +
                        "Aldus PageMaker including versions " +
                        "of Lorem Ipsum.",
                "It", "was", "popularised", "in", "the", "7",
                "with", "the", "release", "of", "Letraset", "sheets",
                "containing", "Lorem", "Ipsum", "passages,", "and",
                "more", "recently", "with", "desktop", "publishing",
                "software", "like", "Aldus", "PageMaker", "including",
                "versions", "of", "Lorem", "Ipsum."}};
    }

    /**
     * Testing handleRequest() method.
     * @param sentence is paragraph that should be parsed.
     * @param lexemes is an array of lexemes of the sentence.
     */
    @Test(dataProvider = "test1")
    public void testHandleRequest(String sentence, Object[] lexemes) {
        Parser sentenceParser = new SentenceParser();
        Component sentenceComponent = sentenceParser.handleRequest(sentence);

        int childrenNumber = sentenceComponent.getNumberOfChildren();
        Object[] childrenValues = new String[childrenNumber];

        int counter = 0;
        while (counter < childrenNumber) {

            Component childComponent = (Component) sentenceComponent.getChild(counter);
            Object value = childComponent.getValue();
            childrenValues[counter] = value;

            counter++;
        }

        Assert.assertEquals(childrenValues, lexemes);
    }
}
