package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The test class for {@link LexemeParser}.
 */
public class LexemeParserTest {

    @DataProvider(name = "test1")
    public static Object[] expressions() {
        return new Object[] {"13<<2", "3>>5", "~6&9|(3&4)",
                "5|(1&2&(3|4&(^5|6&47)|3)|2)|1)",
                "(~71&(2&3|(3|2&1>>2|2)&2)|10&2))|78",
                "(^5|1&2<<(2|5>>2&71))|1200"};
    }

    @DataProvider(name = "test2")
    public static Object[] notExpressions() {
        return new Object[] {"It", "survived", "-", "(five)",
                "typesetting,", "PageMaker", "English."};
    }

    @Test(dataProvider = "test1")
    public void testHandleRequestExpressions(String lexeme) {
        LexemeParser lexemeParser = new LexemeParser();
        lexemeParser.handleRequest(lexeme);
        Assert.assertTrue((lexemeParser.getChildIsExpression()));
    }

    @Test(dataProvider = "test2")
    public void testHandleRequestNotExpressions(String lexeme) {
        LexemeParser lexemeParser = new LexemeParser();
        lexemeParser.handleRequest(lexeme);
        Assert.assertFalse((lexemeParser.getChildIsExpression()));
    }
}
