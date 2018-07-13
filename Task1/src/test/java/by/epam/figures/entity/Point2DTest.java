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

    @DataProvider(name = "test1")
    public static Object[][] pointToString() {

        return new Object[][]{{"Point: x = 1.0 y = 2.0", new Point2D(1.0, 2.0)},
                {"Point: x = 6.8E175 y = -7.9E-177", new Point2D(6.8e+175, -7.9e-177)}};
    }

    @DataProvider(name = "test2")
    public static Object[][] pointsPairs() {

        return new Object[][]{{new Point2D(1.0, 2.0), new Point2D(1.0, 2.0), true},
                {new Point2D(5.6e+300, -1.5e+256), new Point2D(5.6e+300, -1.5e+256), true},
                {new Point2D(1.0, 2.0), new Point2D(2.0, 1.0), false},
                {new Point2D(1.0, 2.0), new Point2D(6.8, -7.9), false}};
    }

    @DataProvider(name = "test3")
    public static Object[][] singlePoints() {

        return new Object[][]{{new Point2D(1.0, 2.0)},
                {new Point2D(-56.0, 324.6)}};
    }

    @Test (dataProvider = "test1")
    public void testToString(String correctString, Point2D point) {

        Assert.assertEquals(correctString, point.toString());
    }

    @Test (dataProvider = "test2")
    public void testHashCode(Point2D point1, Point2D point2, boolean isEquals) {

            Assert.assertEquals(point1.hashCode() == point2.hashCode(), isEquals);
    }

    @Test (dataProvider = "test3")
    public void testSelfEquals(Point2D point1) {

        Assert.assertEquals(point1, point1);
    }

    @Test (dataProvider = "test2")
    public void testEquals(Point2D point1, Point2D point2, boolean isEquals) {

            Assert.assertEquals(point1.equals(point2), isEquals);
    }
}