package by.epam.figures.creator;

import by.epam.figures.entity.Point2D;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link PointCreatorTest} is a test for {@link PointCreator} class.
 */
public class PointCreatorTest {

    @DataProvider(name = "test1")
    public static Object[][] coordinates() {
        return new Object[][]{{2.0, 3.0}, {6.0, -56.0}, {0, 0}, {-122.99, -66.7}};
    }

    @Test (dataProvider = "test1")
    public void testCreatePoint(double x, double y) {

        Point2D point = PointCreator.createPoint(x, y);

        Assert.assertEquals(x, point.getX());
        Assert.assertEquals(y, point.getY());
    }
}