package by.epam.figures.entity;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TriangleTest {

    @DataProvider(name = "test1")
    public static Object[][] triangleToString() {
        return new Object[][]
                {{"Triangle: (2.0, 3.0) (4.0, 1.0) (-1.0, -2.0)",
                new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0))},
                {"Triangle: (1.0, 2.0) (6.0E256, -8.0E256) (3.5, 7.6E-125)",
                new Triangle(
                        new Point2D(1.0, 2.0),
                        new Point2D(6.0e+256, -8.0e+256),
                        new Point2D(3.5, 7.6e-125))}};
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
                        new Point2D(-1.0, -2.0)), true},
                {new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0)),
                new Triangle(
                        new Point2D(1.0, 2.0),
                        new Point2D(6.0, 8.0),
                        new Point2D(3.5, 7.6)), false},
                {new Triangle(
                        new Point2D(367.9, 333.0),
                        new Point2D(4.9e+121, 1.0),
                        new Point2D(-1.0, -2.0e+165)),
                new Triangle(
                        new Point2D(-1.0, -2.0e+165),
                        new Point2D(367.9, 333.0),
                        new Point2D(4.9e+121, 1.0)), false}};
    }

    @DataProvider(name = "test3")
    public static Object[][] singleTriangle() {
        return new Object[][]
                {{new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0))},
                {new Triangle(
                        new Point2D(89.0, -5.0e+35),
                        new Point2D(4.3e-11, 1.0),
                        new Point2D(-11.0, -22.0))}};
    }

    @Test (dataProvider = "test1")
    public void testToString(String correctString, Triangle triangle) {

        Assert.assertEquals(correctString, triangle.toString());

    }

    @Test (dataProvider = "test2")
    public void testHashCode(Triangle triangle1, Triangle triangle2, boolean isEquals) {

            Assert.assertEquals(triangle1.hashCode() == triangle2.hashCode(), isEquals);
    }

    @Test (dataProvider = "test3")
    public void testSelfEquals(Triangle triangle) {

        Assert.assertEquals(triangle, triangle);
    }

    @Test (dataProvider = "test2")
    public void testEquals(Triangle triangle1, Triangle triangle2, boolean isEquals) {

        Assert.assertEquals(triangle1.equals(triangle2), isEquals);
    }
}