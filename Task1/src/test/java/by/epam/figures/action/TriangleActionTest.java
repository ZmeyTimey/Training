package by.epam.figures.action;

import by.epam.figures.entity.Point2D;
import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.OutOfDoubleRangeException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link TriangleActionTest} is a test for {@link TriangleAction} class.
 */
public class TriangleActionTest {
    /**
     * A coefficient that uses in rounding of numbers.
     */
    private static final int ROUND_COEFFICIENT = 4;
    /**
     * An object of testing class.
     */
    private TriangleAction action = new TriangleAction();

    /**
     * @return triangles and their perimeters.
     */
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

    /**
     * @return triangles with points that have out of double type range
     * coordinates.
     */
    @DataProvider(name = "test2")
    public static Object[][] perimetersOutOfDouble() {
        return new Object[][]
                {{new Triangle(
                        new Point2D(-1.6e-323, 2.1e+201),
                        new Point2D(1.6, 6.1),
                        new Point2D(5.6, 0.1))},
                {new Triangle(
                        new Point2D(1.8, 2.0e-180),
                        new Point2D(1.6, 6.0e+180),
                        new Point2D(-5.6e+180, 0.1))}};
    }

    /**
     * @return triangles and their square values.
     */
    @DataProvider(name = "test3")
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

    /**
     * @return triangles with points that have out of double type range
     * coordinates.
     */
    @DataProvider(name = "test4")
    public static Object[][] squaresOutOfDouble() {
        return new Object[][]
                {{new Triangle(
                        new Point2D(-1.6e-323, 2.1e+201),
                        new Point2D(1.6, 6.1),
                        new Point2D(5.6, 0.1))},
                {new Triangle(
                        new Point2D(1.8, 2.0e-180),
                        new Point2D(1.6, 6.0e+180),
                        new Point2D(-5.6e+180, 0.1))}};
    }

    /**
     * @return four triangles of different types and a boolean value
     * indicating whether each of them is right.
     */
    @DataProvider(name = "test5")
    public static Object[][] typesOfTrianglesRight() {
        return new Object[][]
                {{new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0)), false},
                {new Triangle(
                        new Point2D(6.0, 3.0),
                        new Point2D(6.0, 9.0),
                        new Point2D(-8.0, 3.0)), true},
                {new Triangle(
                        new Point2D(4.0, 5.0),
                        new Point2D(7.0, 1.0),
                        new Point2D(3.0, -6.0)), false},
                {new Triangle(
                        new Point2D(2.0, 0.0),
                        new Point2D(4.0, 0.0),
                        new Point2D(3.0, Math.sqrt(3))), false}};
    }

    /**
     * @return four triangles of different types and a boolean value
     * indicating whether each of them is oxygon.
     */
    @DataProvider(name = "test6")
    public static Object[][] typesOfTrianglesOxygon() {
        return new Object[][]
                {{new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0)), true},
                {new Triangle(
                        new Point2D(6.0, 3.0),
                        new Point2D(6.0, 9.0),
                        new Point2D(-8.0, 3.0)), false},
                {new Triangle(
                        new Point2D(4.0, 5.0),
                        new Point2D(7.0, 1.0),
                        new Point2D(3.0, -6.0)), false},
                {new Triangle(
                        new Point2D(2.0, 0.0),
                        new Point2D(4.0, 0.0),
                        new Point2D(3.0, Math.sqrt(3))), true}};
    }
    /**
     * @return four triangles of different types and a boolean value
     * indicating whether each of them is obtuse.
     */
    @DataProvider(name = "test7")
    public static Object[][] typesOfTrianglesObtuse() {
        return new Object[][]
                {{new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0)), false},
                {new Triangle(
                        new Point2D(6.0, 3.0),
                        new Point2D(6.0, 9.0),
                        new Point2D(-8.0, 3.0)), false},
                {new Triangle(
                        new Point2D(4.0, 5.0),
                        new Point2D(7.0, 1.0),
                        new Point2D(3.0, -6.0)), true},
                {new Triangle(
                        new Point2D(2.0, 0.0),
                        new Point2D(4.0, 0.0),
                        new Point2D(3.0, Math.sqrt(3))), false}};
    }
    /**
     * @return four triangles of different types and a boolean value
     * indicating whether each of them is regular.
     */
    @DataProvider(name = "test8")
    public static Object[][] typesOfTrianglesRegular() {
        return new Object[][]
                {{new Triangle(
                        new Point2D(2.0, 3.0),
                        new Point2D(4.0, 1.0),
                        new Point2D(-1.0, -2.0)), false},
                {new Triangle(
                        new Point2D(6.0, 3.0),
                        new Point2D(6.0, 9.0),
                        new Point2D(-8.0, 3.0)), false},
                {new Triangle(
                        new Point2D(4.0, 5.0),
                        new Point2D(7.0, 1.0),
                        new Point2D(3.0, -6.0)), false},
                {new Triangle(
                        new Point2D(2.0, 0.0),
                        new Point2D(4.0, 0.0),
                        new Point2D(3.0, Math.sqrt(3))), true}};
    }

    /**
     * @param perimeter is an incoming value of triangle's perimeter.
     * @param triangle is a {@link Triangle} object.
     * @throws OutOfDoubleRangeException is thrown when any double value
     * is out of double type range.
     */
    @Test (dataProvider = "test1")
    public final void testCalcPerimeter(final double perimeter,
                                  final Triangle triangle)
            throws OutOfDoubleRangeException {

        Assert.assertEquals(perimeter, action.calcPerimeter(triangle),
        ROUND_COEFFICIENT);
    }

    /**
     * @param triangle is a tested {@link Triangle} object.
     * @throws OutOfDoubleRangeException is thrown when any double value
     *      * is out of double type range.
     */
    @Test (dataProvider = "test2", expectedExceptions
            = OutOfDoubleRangeException.class)
    public final void testCalcPerimeterOutOfDouble(final Triangle triangle)
            throws OutOfDoubleRangeException {

        action.calcPerimeter(triangle);
    }

    /**
     * @param square is an incoming value of triangle's square.
     * @param triangle is a tested {@link Triangle} object.
     * @throws OutOfDoubleRangeException is thrown when any double value
     * is out of double type range.
     */
    @Test (dataProvider = "test3")
    public final void testCalcSquare(final double square,
                                     final Triangle triangle)
            throws OutOfDoubleRangeException {

        Assert.assertEquals(square, action.calcSquare(triangle),
                ROUND_COEFFICIENT);
    }

    /**
     * @param triangle is a tested {@link Triangle} object.
     * @throws OutOfDoubleRangeException is thrown when any double value
     * is out of double type range.
     */
    @Test (dataProvider = "test4", expectedExceptions
            = OutOfDoubleRangeException.class)
    public final void testCalcSquareOutOfDouble(final Triangle triangle)
            throws OutOfDoubleRangeException {

        action.calcSquare(triangle);
    }

    /**
     * @param triangle is a tested {@link Triangle} object.
     * @param isRight informs is a tested triangle right.
     * @throws OutOfDoubleRangeException is thrown when any double value
     * is out of double type range.
     */
    @Test (dataProvider = "test5")
    public final void testTriangleIsRight(final Triangle triangle,
                                          final boolean isRight)
            throws OutOfDoubleRangeException {

        Assert.assertEquals(action.triangleIsRight(triangle,
                ROUND_COEFFICIENT), isRight);
    }

    /**
     * @param triangle is a tested {@link Triangle} object.
     * @param isOxygon informs is a tested triangle an oxygon.
     * @throws OutOfDoubleRangeException is thrown when any double value
     * is out of double type range.
     */
    @Test (dataProvider = "test6")
    public final void testTriangleIsOxygon(final Triangle triangle,
                                           final boolean isOxygon)
            throws OutOfDoubleRangeException {

        Assert.assertEquals(action.triangleIsOxygon(triangle,
                ROUND_COEFFICIENT), isOxygon);
    }

    /**
     * @param triangle is a tested {@link Triangle} object.
     * @param isObtuse informs is a tested triangle obtuse.
     * @throws OutOfDoubleRangeException is thrown when any double value
     * is out of double type range.
     */
    @Test (dataProvider = "test7")
    public final void testTriangleIsObtuse(final Triangle triangle,
                                           final boolean isObtuse)
            throws OutOfDoubleRangeException {

        Assert.assertEquals(action.triangleIsObtuse(triangle,
                ROUND_COEFFICIENT), isObtuse);
    }

    /**
     * @param triangle is a tested {@link Triangle} object.
     * @param isRegular informs is a tested triangle obtuse.
     * @throws OutOfDoubleRangeException is thrown when any double value
     * is out of double type range.
     */
    @Test (dataProvider = "test8")
    public final void testTriangleIsRegular(final Triangle triangle,
                                            final boolean isRegular)
            throws OutOfDoubleRangeException {

        Assert.assertEquals(action.triangleIsRegular(triangle,
                ROUND_COEFFICIENT), isRegular);
    }
}
