package by.epam.figures.creator;

import by.epam.figures.entity.Point2D;
import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.PointsFormLineException;
import by.epam.figures.reader.ReaderTest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link TriangleCreatorTest} is a test for {@link PointCreator} and {@link TriangleCreator} classes.
 */
public class TriangleCreatorTest {

    private static final Logger LOGGER = LogManager.getLogger(TriangleCreatorTest.class);

    @DataProvider(name = "test1")
    public static Object[][] coordinates() {
        return new Object[][]{{2.0, 3.0}, {6.0, -56.0}, {0, 0}, {-1.99, -66.7}};
    }

    @DataProvider(name = "test2")
    public static Object[][] points() {
        return new Object[][]
                {{new Point2D(2.0, 3.0), new Point2D(4.0, 1.0), new Point2D(-1.0, -2.0)},
                        {new Point2D(6.0, 3.0), new Point2D(6.0, 9.0), new Point2D(-8.0, 3.0)},
                        {new Point2D(1.0, 1.0), new Point2D(3.0, 3.0), new Point2D(9.0, 9.0)}};
    }

    @Test (dataProvider = "test1")
    public void testCreatePoint(double x, double y) {

        LOGGER.log(Level.DEBUG, "Point creating test is started with incoming data:\n"
                + "coordinate X = " + x + ", coordinate Y = " + y);

        Point2D point = PointCreator.createPoint(x, y);

        Assert.assertEquals(x, point.getX());
        Assert.assertEquals(y, point.getY());

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test2")
    public void testCreateTriangle(Point2D pointA, Point2D pointB, Point2D pointC) {

        LOGGER.log(Level.DEBUG, "Triangle creating test is started with incoming data:\n"
                + "A = " + pointA + "; B = " + pointB + "; C = " + pointC);

        try {
            Triangle triangle = TriangleCreator.createTriangle(pointA, pointB, pointC);

            Assert.assertEquals(pointA, triangle.getPointA());
            Assert.assertEquals(pointB, triangle.getPointB());
            Assert.assertEquals(pointC, triangle.getPointC());

            LOGGER.log(Level.DEBUG, "Triangle is successfully created");

        } catch (PointsFormLineException ex) {
            LOGGER.log(Level.ERROR, ex.getMessage());
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }
}