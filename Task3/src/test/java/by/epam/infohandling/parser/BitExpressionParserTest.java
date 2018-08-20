package by.epam.infohandling.parser;

import by.epam.infohandling.entity.Component;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The test class for {@link BitExpressionParser}.
 */
public class BitExpressionParserTest {

    /**
     * Providing data containing bit expressions and their values.
     * @return necessary data for the test.
     */
    @DataProvider(name = "test1")
    public static Object[][] data() {
        return new Object[][]
                {{"(10^5|1&2<<(2|5>>2&71))|1200", "1215"},
                {"13<<2", "52"},
                {"3>>5", "0"},
                {"~6&9|(3&4)", "9"},
                {"5|(1&2&(3|4&(10^5|6&47)|3)|2)|1", "7"},
                {"(~71&(2&3|(3|2&1>>2|2)&2)|(10&2))|78", "78"}};
    }

    /**
     * Testing handleRequest() method.
     * @param expression is a bit expression that should be parsed.
     * @param value is a value of the bit expression.
     */
    @Test(dataProvider = "test1")
    public void testHandleRequest(
            final String expression, String value) {

        Parser bitExpressionParser = new BitExpressionParser();
        Component expressionComponent = bitExpressionParser.handleRequest(expression);
        String calculatedExpression = (String) expressionComponent.getValue();

        Assert.assertEquals(calculatedExpression, value);
    }
}
