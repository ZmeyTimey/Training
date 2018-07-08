package by.epam.figures.entity;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TriangleTest {

    @DataProvider(name = "test1")
    public static Object[][] triangleToString() {
        return new Object[][]{{"by.epam.figures.entity.Triangle: (2.0, 3.0) (4.0, 1.0) (-1.0, -2.0)",
                new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0))},
                {"by.epam.figures.entity.Triangle: (1.0, 2.0) (6.0, 8.0) (3.5, 7.6)",
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
                        new Point2D(1.0, 2.0),
                        new Point2D(6.0, 8.0),
                        new Point2D(3.5, 7.6))},
                {new Triangle(
                        new Point2D(89.0, -5.0),
                        new Point2D(4.3, 1.0),
                        new Point2D(-11.0, -22.0))}};
    }

    @Test (dataProvider = "test1")
    public void testToString(String correctString, Triangle triangle) {
        Assert.assertEquals(correctString, triangle.toString());
    }

    @Test (dataProvider = "test1")
    public void testHashCode(Triangle triangle1, Triangle triangle2) {
        Assert.assertEquals(triangle1.hashCode(), triangle2.hashCode());
    }

    @Test (dataProvider = "test3")
    public void testSelfEquals(Triangle triangle) {
        Assert.assertEquals(triangle, triangle);
    }

    @Test (dataProvider = "test1")
    public void testEquals1(Triangle triangle1, Triangle triangle2) {
        Assert.assertEquals(triangle1, triangle2);
    }

    @Test (dataProvider = "test1")
    public void testNotEquals(Triangle triangle1, Triangle triangle2) {
        Assert.assertNotEquals(triangle1, triangle2);
    }
}