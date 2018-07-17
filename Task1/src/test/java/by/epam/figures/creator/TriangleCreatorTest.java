package by.epam.figures.creator;

import by.epam.figures.entity.Point2D;
import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.PointsFormLineException;
import by.epam.figures.repository.Repository;
import by.epam.figures.specification.SearchById;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link TriangleCreatorTest} is a test for {@link TriangleCreator} class.
 */
public class TriangleCreatorTest {

    /**
     * @return sets of three points which form a triangle.
     */
    @DataProvider(name = "test1")
    public static Object[][] points() {
        return new Object[][]
                {{new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0)},
                {new Point2D(2.0, 3.0),
                        new Point2D(698.0, 15500.0),
                        new Point2D(-89219.0, 3000.0)},
                {new Point2D(0.0, 0.0),
                        new Point2D(0.0, -333.0),
                        new Point2D(999.0, -333.0)}};
    }

    /**
     * @return sets of three points located on one line.
     */
    @DataProvider(name = "test2")
    public static Object[][] testOneLinePoints() {
        return new Object[][]
                {{new Point2D(2.0, 3.0),
                        new Point2D(2.0, 3.0),
                        new Point2D(2.0, 3.0)},
                {new Point2D(1.0, 1.0),
                        new Point2D(3.0, 3.0),
                        new Point2D(9.0, 9.0)},
                {new Point2D(-10.0, 3.0),
                        new Point2D(0.0, 3.0),
                        new Point2D(10.0, 3.0)},
                {new Point2D(-3.0, -21.6),
                        new Point2D(-3.0, 1.0),
                        new Point2D(-3.0, 22.5)}};
    }

    /**
     * @param pointA is a point A of a triangle supposed to be created.
     * @param pointB is a point B of a triangle supposed to be created.
     * @param pointC is a point C of a triangle supposed to be created.
     * @throws PointsFormLineException throws when all three points are located
     * on one line.
     */
    @Test (dataProvider = "test1")
    public final void testCreateTriangle(final Point2D pointA,
                                         final Point2D pointB,
                                         final Point2D pointC)
            throws PointsFormLineException {

        test(pointA, pointB, pointC);
    }

    /**
     * @param pointA is a point A of a triangle supposed to be created.
     * @param pointB is a point B of a triangle supposed to be created.
     * @param pointC is a point C of a triangle supposed to be created.
     * @throws PointsFormLineException throws when all three points are located
     * on one line.
     */
    @Test (dataProvider = "test2", expectedExceptions
            = PointsFormLineException.class)
    public final void testPointsFormLine(final Point2D pointA,
                                         final Point2D pointB,
                                         final Point2D pointC)
            throws PointsFormLineException {

        test(pointA, pointB, pointC);
    }

    /**
     * Private method containing actions which should be carried out
     * in both tests of this class.
     * @param pointA is a point A of a triangle supposed to be created.
     * @param pointB is a point B of a triangle supposed to be created.
     * @param pointC is a point C of a triangle supposed to be created.
     * @throws PointsFormLineException throws when all three points are located
     * on one line.
     */
    private final void test(final Point2D pointA,
                            final Point2D pointB,
                            final Point2D pointC)
        throws PointsFormLineException {

        Repository.removeAll();

        TriangleCreator.createTriangle("tr", pointA, pointB, pointC);
        Triangle triangle = SearchById.getTriangle(1);

        Assert.assertEquals(pointA, triangle.getPointA());
        Assert.assertEquals(pointB, triangle.getPointB());
        Assert.assertEquals(pointC, triangle.getPointC());
    }
}
