package by.epam.figures.action;

import by.epam.figures.entity.Point2D;
import by.epam.figures.entity.Triangle;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Тимей on 06.07.2018.
 *
 */
public class CalculatorTest {

    private Calculator calculator = new Calculator();

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
                        new Point2D(-8.0, 3.0))}};
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
                        new Point2D(-8.0, 3.0))}};
    }

    @DataProvider(name = "test3")
    public static Object[][] rightOrNot() {
        return new Object[][]
                {{ new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0))},
                {new Triangle(
                        new Point2D(6.0, 3.0),
                        new Point2D(6.0, 9.0),
                        new Point2D(-8.0, 3.0))}};
    }

    @Test (dataProvider = "test1")
    public void testCalculatePerimeter(double perimeter, Triangle triangle) {

        Assert.assertEquals(perimeter, calculator.calculatePerimeter(triangle));
    }

    @Test (dataProvider = "test2")
    public void testCalculateSquare(double square, Triangle triangle) {

        Assert.assertEquals(square, calculator.calculateSquare(triangle));
    }

    @Test (dataProvider = "test3")
    public void TriangleIsNotRight(Triangle triangle) {

        Assert.assertFalse(calculator.triangleIsRight(triangle));
    }

    @Test (dataProvider = "test3")
    public void TriangleIsRight(Triangle triangle) {

        Assert.assertTrue(calculator.triangleIsRight(triangle));
    }
}