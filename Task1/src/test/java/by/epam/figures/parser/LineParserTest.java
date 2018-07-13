package by.epam.figures.parser;

import by.epam.figures.entity.Point2D;
import by.epam.figures.exception.OutOfDoubleRangeException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link LineParserTest} is a test for {@link LineParser} class.
 */
public class LineParserTest {

    private LineParser parser = new LineParser();

    @DataProvider(name = "test1")
    public static Object[][] correctPoints() {
        return new Object[][]
                {{"2.0, 3.0; 4.0, -1.0; -1.0, -2.0",
                        new Point2D(2.0, 3.0), new Point2D(4.0, -1.0), new Point2D(-1.0, -2.0)},
                {"1.0, 1.0; 2.0, 3.0; 9.0, 9.0",
                        new Point2D(1.0, 1.0), new Point2D(2.0, 3.0), new Point2D(9.0, 9.0)},
                {"-450.0, -345.0; 2789.0, 1.7e+308; -1.7e+308, 9700.5",
                        new Point2D(-450.0, -345.0),
                        new Point2D(2789.0, 1.7e+308),
                        new Point2D(-1.7e+308, 9700.5)}};
    }

    @DataProvider(name = "test2")
    public static Object[][] notCoincidePoints() {
        return new Object[][]
                {{"2.0, 3.0; 4.0, 1.0; -1.0, -2.0",
                        new Point2D(3.0, 2.0), new Point2D(1.0, 4.0), new Point2D(-2.0, -1.0)},
                {"2.0, 3.0; 4.0, 1.0; -1.0, -2.0",
                        new Point2D(4.0, 1.0), new Point2D(-1.0, -2.0), new Point2D(2.0, 3.0)}};
    }

    @DataProvider(name = "test3")
    public static Object[][] OutOfDoublePoints() {
        return new Object[][]
                {{"1.8e+308, 3.0; 2.0, 3.0; 2.0, 3.0"},
                {"1.0, 1.0; 3.0, -1.8e+308; 9.0, 9.0"},
                {"1.0, 1.0; 6.0e+375, -1.8e+410; 9.0, 9.0"}};
    }

    @Test (dataProvider = "test1")
    public void testParseData(String line,
                              Point2D point1,
                              Point2D point2,
                              Point2D point3) throws OutOfDoubleRangeException {

            parser.parseData(line);

            Assert.assertEquals(point1, parser.getPointA());
            Assert.assertEquals(point2, parser.getPointB());
            Assert.assertEquals(point3, parser.getPointC());
    }

    @Test (dataProvider = "test2")
    public void testParseUnmatchedData(String line,
                              Point2D point1,
                              Point2D point2,
                              Point2D point3) throws OutOfDoubleRangeException {

        parser.parseData(line);

        Assert.assertNotEquals(point1, parser.getPointA());
        Assert.assertNotEquals(point2, parser.getPointB());
        Assert.assertNotEquals(point3, parser.getPointC());
    }

    @Test (dataProvider = "test3", expectedExceptions = OutOfDoubleRangeException.class)
    public void testParseInvalidData(String line) throws OutOfDoubleRangeException {
            parser.parseData(line);
    }
}