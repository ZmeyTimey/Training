package by.epam.figures.action;

import by.epam.figures.entity.Point2D;
import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.OutOfDoubleRangeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * {@link TriangleActionTest} is a test for class {@link TriangleAction}.
 */
public class TriangleActionTest {

    private static final int ROUND_COEFFICIENT = 4;
    private static final Logger LOGGER = LogManager.getLogger(TriangleActionTest.class);

    private TriangleAction action = new TriangleAction();

    @DataProvider(name = "test1")
    public static Object[][] perimeters() {
        return new Object[][]
                {{14.4903, new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0))},
                {35.2315, new Triangle(
                        new Point2D(6.0, 3.0),
                        new Point2D(6.0, 9.0),
                        new Point2D(-8.0, 3.0))},
                {Infinity, new Triangle(
                        new Point2D(-1.6e-323, 2.1e+201),
                        new Point2D(1.6, 6.1),
                        new Point2D(5.6, 0.1))}};
    }

    @DataProvider(name = "test2")
    public static Object[][] squares() {
        return new Object[][]
                {{8.0, new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0))},
                {42.0, new Triangle(
                        new Point2D(6.0, 3.0),
                        new Point2D(6.0, 9.0),
                        new Point2D(-8.0, 3.0))},
                {Infinity, new Triangle(
                        new Point2D(-1.6e-323, 2.1e+201),
                        new Point2D(1.6, 6.1),
                        new Point2D(5.6, 0.1))}};
    }

    @DataProvider(name = "test3")
    public static Object[][] typesOfTriangles() {
        return new Object[][]
                {{new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0))},
                {new Triangle(
                        new Point2D(6.0, 3.0),
                        new Point2D(6.0, 9.0),
                        new Point2D(-8.0, 3.0))},
                {new Triangle(
                        new Point2D(4.0, 5.0),
                        new Point2D(7.0, 1.0),
                        new Point2D(3.0, -6.0))},
                {new Triangle(
                        new Point2D(2.0, 0.0),
                        new Point2D(4.0, 0.0),
                        new Point2D(3.0, Math.sqrt(3)))}};
    }

    @Test (dataProvider = "test1")
    public void testCalcPerimeter(double perimeter, Triangle triangle) {

        LOGGER.log(Level.DEBUG, "Perimeter calculating test is started with incoming data:\n"
                + triangle + "\nPerimeter = " + perimeter);

        try {
            Assert.assertEquals(perimeter, action.calcPerimeter(triangle), ROUND_COEFFICIENT);
        } catch (OutOfDoubleRangeException ex) {
            LOGGER.log(Level.ERROR, ex.getMessage());
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test2")
    public void testCalcSquare(double square, Triangle triangle) {

        LOGGER.log(Level.DEBUG, "Square calculating test is started with incoming data:\n"
                + triangle + "\nSquare = " + square);

        try {
            Assert.assertEquals(square, action.calcSquare(triangle), ROUND_COEFFICIENT);
        } catch(OutOfDoubleRangeException ex) {
            LOGGER.log(Level.ERROR, ex.getMessage());
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test3")
    public void testTriangleIsRight(Triangle triangle) {

        LOGGER.log(Level.DEBUG, "Is triangle right checking test is started with incoming data:\n" + triangle);

        try {
            Assert.assertTrue(action.triangleIsRight(triangle, ROUND_COEFFICIENT));
            LOGGER.log(Level.DEBUG, triangle + " is right");
        } catch(OutOfDoubleRangeException ex) {
            LOGGER.log(Level.ERROR, ex.getMessage());
        } catch (AssertionError error) {
            LOGGER.log(Level.DEBUG, triangle + " is not right");
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test3")
    public void testTriangleIsOxygon(Triangle triangle) {

        LOGGER.log(Level.DEBUG, "Is triangle oxygon checking test is started with incoming data:\n" + triangle);

        try {
            Assert.assertTrue(action.triangleIsOxygon(triangle, ROUND_COEFFICIENT));
            LOGGER.log(Level.DEBUG, triangle + " is oxygon");
        } catch(OutOfDoubleRangeException ex) {
            LOGGER.log(Level.ERROR, ex.getMessage());
        } catch(AssertionError error) {
            LOGGER.log(Level.DEBUG, triangle + " is not oxygon");
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test3")
    public void testTriangleIsObtuse(Triangle triangle) {

        LOGGER.log(Level.DEBUG, "Is triangle obtuse checking test is started with incoming data:\n" + triangle);

        try {
            Assert.assertTrue(action.triangleIsObtuse(triangle, ROUND_COEFFICIENT));
            LOGGER.log(Level.DEBUG, triangle + " is obtuse");
        } catch(OutOfDoubleRangeException ex) {
            LOGGER.log(Level.ERROR, ex.getMessage());
        } catch(AssertionError error) {
            LOGGER.log(Level.DEBUG, triangle + " is not obtuse");
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test3")
    public void testTriangleIsRegular(Triangle triangle) {

        LOGGER.log(Level.DEBUG, "Is triangle regular checking test is started with incoming data:\n" + triangle);

        try {
            Assert.assertTrue(action.triangleIsRegular(triangle, ROUND_COEFFICIENT));
            LOGGER.log(Level.DEBUG, triangle + " is regular");
        } catch(OutOfDoubleRangeException ex) {
            LOGGER.log(Level.ERROR, ex.getMessage());
        } catch(AssertionError error) {
            LOGGER.log(Level.DEBUG, triangle + " is not regular");
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }
}