package by.epam.figures.entity;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



/**
 * Created by Тимей on 08.07.2018.
 *
 */
public class Point2DTest {

    @DataProvider(name = "test1")
    public static Object[][] pointToString() {
        return new Object[][]{{"by.epam.figures.entity.Point2D: x = 1.0 y = 2.0", new Point2D(1.0, 2.0)},
                {"by.epam.figures.entity.Point2D: x = 6.8 y = -7.9", new Point2D(6.8, -7.9)}};
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
                {new Point2D(89.0, 780.0)},
                {new Point2D(6.8, -7.9)},
                {new Point2D(-56.0, 324.6)}};
    }

    @Test (dataProvider = "test1")
    public void testToString(String correctString, Point2D point1) {
        Assert.assertEquals(correctString, point1.toString());
    }

    @Test (dataProvider = "test2")
    public void testHashCode(Point2D point1, Point2D point2) {
        Assert.assertEquals(point1.hashCode(), point2.hashCode());
    }

    @Test (dataProvider = "test3")
    public void testSelfEquals(Point2D point1) {
        Assert.assertEquals(point1, point1);
    }

    @Test (dataProvider = "test2")
    public void testEquals1(Point2D point1, Point2D point2) {
        Assert.assertEquals(point1, point2);
    }

    @Test (dataProvider = "test2")
    public void testNotEquals(Point2D point1, Point2D point2) {
        Assert.assertNotEquals(point1, point2);
    }
}