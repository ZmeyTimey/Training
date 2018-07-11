package by.epam.figures.parser;

import by.epam.figures.entity.Point2D;
import by.epam.figures.exception.OutOfDoubleRangeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link LineParserTest} is a test for class {@link LineParser}.
 */
public class LineParserTest {

    private static final Logger LOGGER = LogManager.getLogger(LineParserTest.class);

    private LineParser parser = new LineParser();

    @DataProvider(name = "test1")
    public static Object[][] points() {
        return new Object[][]
                {{"2.0, 3.0; 4.0, 1.0; -1.0, -2.0",
                        new Point2D(2.0, 3.0), new Point2D(4.0, 1.0), new Point2D(-1.0, -2.0)},
                {"1.0, 1.0; 3.0, 3.0; 9.0, 9.0",
                        new Point2D(1.0, 1.0), new Point2D(2.0, 3.0), new Point2D(9.0, 9.0)},
                {"4.0, 6.9; 56.7, 1.8e+387; 1.0, 6.7",
                        new Point2D(1.0, 1.0), new Point2D(2.0, 3.0), new Point2D(9.0, 9.0)},
                {"-3.0, 2.6; 50.0, 2.1; 2.5e-324, 6.7",
                        new Point2D(-3.0, 2.6), new Point2D(50.0, 2.1), new Point2D(2.5e-324, 6.7)}};
    }


    @Test (dataProvider = "test1")
    public void testParseData(String line, Point2D point1, Point2D point2, Point2D point3) {
        LOGGER.log(Level.DEBUG, "LineParser test is started with incoming data:\n Line: " + line+ "\n "
                + point1 + "\n " + point2 + "\n " + point3);
        try {
            parser.parseData(line);

            Assert.assertEquals(point1, parser.getPointA());
            Assert.assertEquals(point2, parser.getPointB());
            Assert.assertEquals(point3, parser.getPointC());

            LOGGER.log(Level.DEBUG, "The created points coincide with the declared values");

        } catch(OutOfDoubleRangeException ex) {
            LOGGER.log(Level.ERROR, ex.getMessage() + " A point can't be created.");
        } catch (AssertionError error) {
            LOGGER.log(Level.DEBUG, "The created points do not coincide with the declared values");
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }
}