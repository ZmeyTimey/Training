package by.epam.infohandling.parser;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The test class for {@link LexemeParser}.
 */
public class LexemeParserTest {

    /**
     * Providing data containing bit expressions.
     * @return an array of bit expressions.
     */
    @DataProvider(name = "test1")
    public static Object[] expressions() {
        return new Object[] {"13<<2", "3>>5", "~6&9|(3&4)",
                "5|(1&2&(3|4&(10^5|6&47)|3)|2)|1",
                "(~71&(2&3|(3|2&1>>2|2)&2)|(10&2))|78",
                "(10^5|1&2<<(2|5>>2&71))|1200"};
    }

    /**
     * Providing data containing not bit expression lexemes.
     * @return an array of not bit expression lexemes.
     */
    @DataProvider(name = "test2")
    public static Object[] notExpressions() {
        return new Object[] {"It", "survived", "-", "(five)",
                "typesetting,", "PageMaker", "English."};
    }

    /**
     * Checks if handleRequest() method correctly recognizes bit expressions.
     * @param lexeme is an input lexeme value.
     */
    @Test(dataProvider = "test1")
    public void testHandleRequestExpressions(String lexeme) {
        LexemeParser lexemeParser = new LexemeParser();
        lexemeParser.handleRequest(lexeme);
        Assert.assertTrue((lexemeParser.getBitExpression()));
    }

    /**
     * Checks if handleRequest() method correctly recognizes not bit expressions.
     * @param lexeme is an input lexeme value.
     */
    @Test(dataProvider = "test2")
    public void testHandleRequestNotExpressions(String lexeme) {
        LexemeParser lexemeParser = new LexemeParser();
        lexemeParser.handleRequest(lexeme);
        Assert.assertFalse((lexemeParser.getBitExpression()));
    }
}
