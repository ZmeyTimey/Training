package by.epam.figures.entity;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link Point2DTest} is a test for class {@link Point2D}.
 */
public class Point2DTest {
    /**
     * @return {@link Point2D} objects and their string expressions.
     */
    @DataProvider(name = "test1")
    public static Object[][] pointToString() {

        return new Object[][]{{"Point: x = 1.0 y = 2.0", new Point2D(1.0, 2.0)},
                {"Point: x = 6.8E175 y = -7.9E-177",
                        new Point2D(6.8e+175, -7.9e-177)}};
    }

    /**
     * @return pairs of {@link Point2D} objects which can be equal or not.
     */
    @DataProvider(name = "test2")
    public static Object[][] pointsPairs() {

        return new Object[][]{{new Point2D(1.0, 2.0),
                new Point2D(1.0, 2.0), true},
                {new Point2D(5.6e+300, -1.5e+256),
                        new Point2D(5.6e+300, -1.5e+256), true},
                {new Point2D(1.0, 2.0),
                        new Point2D(2.0, 1.0), false},
                {new Point2D(1.0, 2.0),
                        new Point2D(6.8, -7.9), false}};
    }

    /**
     * @return single {@link Point2D} objects.
     */
    @DataProvider(name = "test3")
    public static Object[][] singlePoints() {

        return new Object[][]{{new Point2D(1.0, 2.0)},
                {new Point2D(-56.0, 324.6)}};
    }

    /**
     * @param correctString is a string expression of point.
     * @param point is tested {@link Point2D} object.
     */
    @Test (dataProvider = "test1")
    public final void testToString(final String correctString,
                                   final Point2D point) {

        Assert.assertEquals(correctString, point.toString());
    }

    /**
     * @param point1 is a first of comparing points.
     * @param point2 is a second of comparing points.
     * @param isEquals informs is the points' hash codes equal.
     */
    @Test (dataProvider = "test2")
    public final void testHashCode(final Point2D point1,
                             final Point2D point2,
                             final boolean isEquals) {

            Assert.assertEquals(point1.hashCode()
                    == point2.hashCode(), isEquals);
    }

    /**
     * @param point is a tested {@link Point2D} object.
     */
    @Test (dataProvider = "test3")
    public final void testSelfEquals(final Point2D point) {

        Assert.assertEquals(point, point);
    }

    /**
     * @param point1 is a first of comparing points.
     * @param point2 is a second of comparing points.
     * @param isEquals informs is the points' hash codes equal.
     */
    @Test (dataProvider = "test2")
    public final void testEquals(final Point2D point1,
                                 final Point2D point2,
                                 final boolean isEquals) {

            Assert.assertEquals(point1.equals(point2), isEquals);
    }
}
