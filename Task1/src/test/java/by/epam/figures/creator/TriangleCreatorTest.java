package by.epam.figures.creator;

import by.epam.figures.entity.Point2D;
import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.PointsFormLineException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link TriangleCreatorTest} is a test for {@link TriangleCreator} class.
 */
public class TriangleCreatorTest {

    @DataProvider(name = "test1")
    public static Object[][] points() {
        return new Object[][]
                {{new Point2D(2.0, 3.0), new Point2D(4.0, 1.0), new Point2D(-1.0, -2.0)},
                {new Point2D(2.0, 3.0), new Point2D(698.0, 15500.0), new Point2D(-89219.0, 3000.0)},
                {new Point2D(0.0, 0.0), new Point2D(0.0, -333.0), new Point2D(999.0, -333.0)}};
    }

    @DataProvider(name = "test2")
    public static Object[][] testOneLinePoints() {
        return new Object[][]
                {{new Point2D(2.0, 3.0), new Point2D(2.0, 3.0), new Point2D(2.0, 3.0)},
                        {new Point2D(1.0, 1.0), new Point2D(3.0, 3.0), new Point2D(9.0, 9.0)},
                        {new Point2D(-10.0, 3.0), new Point2D(0.0, 3.0), new Point2D(10.0, 3.0)},
                        {new Point2D(-3.0, -21.6), new Point2D(-3.0, 1.0), new Point2D(-3.0, 22.5)}};
    }

    @Test (dataProvider = "test1")
    public void testCreateTriangle(Point2D pointA, Point2D pointB, Point2D pointC) throws PointsFormLineException {

        Triangle triangle = TriangleCreator.createTriangle(pointA, pointB, pointC);

        Assert.assertEquals(pointA, triangle.getPointA());
        Assert.assertEquals(pointB, triangle.getPointB());
        Assert.assertEquals(pointC, triangle.getPointC());
    }

    @Test (dataProvider = "test2", expectedExceptions = PointsFormLineException.class)
    public void testPointsFormLine(Point2D pointA, Point2D pointB, Point2D pointC) throws PointsFormLineException {

        Triangle triangle = TriangleCreator.createTriangle(pointA, pointB, pointC);

        Assert.assertEquals(pointA, triangle.getPointA());
        Assert.assertEquals(pointB, triangle.getPointB());
        Assert.assertEquals(pointC, triangle.getPointC());
    }
}