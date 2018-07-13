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

    /**
     * @return a string with a data and the points that had to be created on the basis of this data.
     */
    @DataProvider(name = "test1")
    public static Object[][] correctPoints() {
        return new Object[][]
                {{"2.0, 3.0; 4.0, -1.0; -1.0, -2.0",
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, -1.0),
                        new Point2D(-1.0, -2.0)},
                {"1.0, 1.0; 2.0, 3.0; 9.0, 9.0",
                        new Point2D(1.0, 1.0),
                        new Point2D(2.0, 3.0),
                        new Point2D(9.0, 9.0)},
                {"-450.0, -345.0; 2789.0, 1.7e+308; -1.7e+308, 9700.5",
                        new Point2D(-450.0, -345.0),
                        new Point2D(2789.0, 1.7e+308),
                        new Point2D(-1.7e+308, 9700.5)}};
    }

    /**
     * @return strings with a data and the points which's coordinates
     * don't coincide with this data.
     */
    @DataProvider(name = "test2")
    public static Object[][] notCoincidePoints() {
        return new Object[][]
                {{"2.0, 3.0; 4.0, 1.0; -1.0, -2.0",
                        new Point2D(3.0, 2.0), new Point2D(1.0, 4.0), new Point2D(-2.0, -1.0)},
                {"2.0, 3.0; 4.0, 1.0; -1.0, -2.0",
                        new Point2D(4.0, 1.0), new Point2D(-1.0, -2.0), new Point2D(2.0, 3.0)}};
    }

    /**
     * @return strings with invalid data.
     */
    @DataProvider(name = "test3")
    public static Object[][] outOfDoublePoints() {
        return new Object[][]
                {{"1.8e+308, 3.0; 2.0, 3.0; 2.0, 3.0"},
                {"1.0, 1.0; 3.0, -1.8e+308; 9.0, 9.0"},
                {"1.0, 1.0; 6.0e+375, -1.8e+410; 9.0, 9.0"}};
    }

    /**
     * @param line is a string supposed to be parsed.
     * @param point1 is a first point.
     * @param point2 is a second point.
     * @param point3 is a third point.
     * @throws OutOfDoubleRangeException is thrown when any double value
     * is out of double type range.
     */
    @Test (dataProvider = "test1")
    public final void testParseData(final String line,
                              final Point2D point1,
                              final Point2D point2,
                              final Point2D point3)
            throws OutOfDoubleRangeException {

            parser.parseData(line);

            Assert.assertEquals(point1, parser.getPointA());
            Assert.assertEquals(point2, parser.getPointB());
            Assert.assertEquals(point3, parser.getPointC());
    }

    /**
     * @param line is a string supposed to be parsed.
     * @param point1 is a first point.
     * @param point2 is a second point.
     * @param point3 is a third point.
     * @throws OutOfDoubleRangeException is thrown when any double value
     * is out of double type range.
     */
    @Test (dataProvider = "test2")
    public final void testParseUnmatchedData(final String line,
                              final Point2D point1,
                              final Point2D point2,
                              final Point2D point3)
            throws OutOfDoubleRangeException {

        parser.parseData(line);

        Assert.assertNotEquals(point1, parser.getPointA());
        Assert.assertNotEquals(point2, parser.getPointB());
        Assert.assertNotEquals(point3, parser.getPointC());
    }

    /**
     * @param line is a string supposed to be parsed.
     * @throws OutOfDoubleRangeException is thrown when any double value
     * is out of double type range.
     */
    @Test (dataProvider = "test3", expectedExceptions
            = OutOfDoubleRangeException.class)
    public final void testParseInvalidData(final String line)
            throws OutOfDoubleRangeException {
            parser.parseData(line);
    }
}