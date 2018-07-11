package by.epam.figures.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TriangleTest {

    private static final Logger LOGGER = LogManager.getLogger(TriangleTest.class);

    @DataProvider(name = "test1")
    public static Object[][] triangleToString() {
        return new Object[][]
                {{"Triangle: (2.0, 3.0) (4.0, 1.0) (-1.0, -2.0)",
                new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0))},
                {"Triangle: (1.0, 2.0) (6.0, 8.0) (3.5, 7.6)",
                new Triangle(
                        new Point2D(1.0, 2.0),
                        new Point2D(6.0, 8.0),
                        new Point2D(3.5, 7.6))}};
    }

    @DataProvider(name = "test2")
    public static Object[][] trianglesPairs() {
        return new Object[][]
                {{new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0)),
                new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0))},
                {new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0)),
                new Triangle(
                        new Point2D(1.0, 2.0),
                        new Point2D(6.0, 8.0),
                        new Point2D(3.5, 7.6))}};
    }

    @DataProvider(name = "test3")
    public static Object[][] singleTriangle() {
        return new Object[][]
                {{new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0))},
                {new Triangle(
                        new Point2D(89.0, -5.0),
                        new Point2D(4.3, 1.0),
                        new Point2D(-11.0, -22.0))}};
    }

    @Test (dataProvider = "test1")
    public void testToString(String correctString, Triangle triangle) {

        LOGGER.log(Level.DEBUG, "toString test correct test is started with incoming data:\n"
                + "String: " + correctString + "\n" + triangle);

        Assert.assertEquals(correctString, triangle.toString());

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test2")
    public void testHashCode(Triangle triangle1, Triangle triangle2) {

        LOGGER.log(Level.DEBUG, "HashCode test is started with incoming data:\n"
                + "1: " + triangle1 + "\n" + "2: " + triangle2);

        try {
            Assert.assertEquals(triangle1.hashCode(), triangle2.hashCode());
            LOGGER.log(Level.DEBUG, "Hash codes are equal");
        } catch(AssertionError error) {
            LOGGER.log(Level.DEBUG, "Hash codes are not equal");
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test3")
    public void testSelfEquals(Triangle triangle) {

        LOGGER.log(Level.DEBUG, "SelfEquals test is started with incoming data:\n" + triangle);

        Assert.assertEquals(triangle, triangle);

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test2")
    public void testEquals(Triangle triangle1, Triangle triangle2) {

        LOGGER.log(Level.DEBUG, "Equal test is started with incoming data:\n"
                + "1: " + triangle1 + "\n" + "2: " + triangle2);

        try {
            Assert.assertEquals(triangle1, triangle2);
            LOGGER.log(Level.DEBUG, "Points are equal");
        } catch(AssertionError error) {
            LOGGER.log(Level.DEBUG, "Points are not equal");
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }
}