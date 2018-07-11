package by.epam.figures.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link Point2DTest} is a test for class {@link Point2D}.
 */
public class Point2DTest {

    private static final Logger LOGGER = LogManager.getLogger(Point2D.class);

    @DataProvider(name = "test1")
    public static Object[][] pointToString() {

        return new Object[][]{{"Point: x = 1.0 y = 2.0", new Point2D(1.0, 2.0)},
                {"Point: x = 6.8 y = -7.9", new Point2D(6.8, -7.9)}};
    }

    @DataProvider(name = "test2")
    public static Object[][] pointsPairs() {

        return new Object[][]{{new Point2D(1.0, 2.0), new Point2D(1.0, 2.0)},
                {new Point2D(1.0, 2.0), new Point2D(2.0, 1.0)},
                {new Point2D(1.0, 2.0), new Point2D(6.8, -7.9)}};
    }

    @DataProvider(name = "test3")
    public static Object[][] singlePoints() {

        return new Object[][]{{new Point2D(1.0, 2.0)},
                {new Point2D(-56.0, 324.6)}};
    }

    @Test (dataProvider = "test1")
    public void testToString(String correctString, Point2D point) {

        LOGGER.log(Level.DEBUG, "toString test correct test is started with incoming data:\n"
        + "String: " + correctString + "\n" + point);

        Assert.assertEquals(correctString, point.toString());

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test2")
    public void testHashCode(Point2D point1, Point2D point2) {

        LOGGER.log(Level.DEBUG, "HashCode test is started with incoming data:\n"
                + "1: " + point1 + "\n" + "2: " + point2);

        try {
            Assert.assertEquals(point1.hashCode(), point2.hashCode());
            LOGGER.log(Level.DEBUG, "Hash codes are equal");
        } catch(AssertionError error) {
            LOGGER.log(Level.DEBUG, "Hash codes are not equal");
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test3")
    public void testSelfEquals(Point2D point1) {

        LOGGER.log(Level.DEBUG, "SelfEquals test is started with incoming data:\n" + point1);

        Assert.assertEquals(point1, point1);

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test2")
    public void testEquals(Point2D point1, Point2D point2) {

        LOGGER.log(Level.DEBUG, "Equal test is started with incoming data:\n"
                + "1: " + point1 + "\n" + "2: " + point2);

        try {
            Assert.assertEquals(point1, point2);
            LOGGER.log(Level.DEBUG, "Points are equal");
        } catch(AssertionError error) {
            LOGGER.log(Level.DEBUG, "Points are not equal");
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }
}