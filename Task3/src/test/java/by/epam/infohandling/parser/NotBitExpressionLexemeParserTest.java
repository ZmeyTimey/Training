package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The test class for {@link NotBitExpressionLexemeParser}.
 */
public class NotBitExpressionLexemeParserTest {

    /**
     * Providing data containing lexemes and their child components.
     * @return necessary data for the test.
     */
    @DataProvider(name = "test1")
    public static Object[][] data() {
        return new Object[][] {{"-", '-'},
                {"typesetting,", "typesetting", ','},
                {"English.", "English", '.'},
                {"(five)", '(', "five", ')'},
                {"(Content", '(', "Content"},
                {"here)", "here", ')'},
                {"here),", "here", ')', ','},
                {"here...", "here", "..."}};
    }

    /**
     * Testing handleRequest() method.
     * @param lexeme is a lexeme that should be parsed.
     * @param components is an array of child components of the lexeme.
     */
    @Test (dataProvider = "test1")
    public void testHandleRequest(final String lexeme, Object[] components) {

        Parser notBitExpressionLexemeParser = new NotBitExpressionLexemeParser();
        Component lexemeComponent = notBitExpressionLexemeParser.handleRequest(lexeme);

        int childrenNumber = lexemeComponent.getNumberOfChildren();
        Object[] childrenValues = new Object[childrenNumber];

        int counter = 0;
        while (counter < childrenNumber) {
            Component childComponent = (Component) lexemeComponent.getChild(counter);
            Object value = childComponent.getValue();
            childrenValues[counter] = value;
            counter++;
        }
        Assert.assertEquals(childrenValues, components);
    }

}
