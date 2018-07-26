package by.epam.figures.action;

import by.epam.figures.creator.TriangleCreator;
import by.epam.figures.entity.Point2D;
import by.epam.figures.exception.InvalidPointNameException;
import by.epam.figures.exception.PointsFormLineException;
import by.epam.figures.repository.Repository;
import by.epam.figures.specification.SearchById;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * A test for {@link TriangleChanger} class.
 */
public class TriangleChangerTest {

    /**
     * Creating triangles and adding their registrators
     * into the list of registrators.
     * @throws PointsFormLineException throws when all three points are located
     * on one line.
     */
    @BeforeMethod
    public final void beforeMethod() throws PointsFormLineException {

        Repository.removeAll();

        TriangleCreator.createTriangle("tr1",
                new Point2D(2.0, 3.0),
                new Point2D(4.0, 1.0),
                new Point2D(-1.0, -2.0));
        TriangleCreator.createTriangle("tr2",
                new Point2D(1.0, 2.0),
                new Point2D(6.0, 8.0),
                new Point2D(3.5, 7.6));
        TriangleCreator.createTriangle("tr1",
                new Point2D(6.0, 3.0),
                new Point2D(6.0, 9.0),
                new Point2D(-8.0, 3.0));
    }

    /**
     * @return data which is necessary for starting
     * changeData() method.
     */
    @DataProvider(name = "test1")
    public static Object[][] inputData() {
        return new Object[][]
                {{1, 'A', 6.0, 2.0},
                {2, 'B', 6.0, 600.0},
                {3, 'C', -8000.0, 18.0}};
    }

    /**
     * @return data with new coordinate values which coincide
     * with initial coordinate values.
     */
    @DataProvider(name = "test2")
    public static Object[][] equalInputData() {
        return new Object[][]
                {{1, 'A', 2.0, 3.0},
                {2, 'B', 6.0, 8.0},
                {3, 'C', -8, 3.0}};
    }

    /**
     * @return data for changeData() method
     * with invalid point names.
     */
    @DataProvider(name = "test3")
    public static Object[][] invalidPointNameData() {
        return new Object[][]
                {{1, 'a', 6.0, 2.0},
                {2, 'h', 6.0, 600.0},
                {3, '2', -8000.0, 18.0}};
    }

    /**
     * Tests changeData() method with valid input data.
     * @param id is triangle's id.
     * @param pointName is a name of changing point (A, B or C).
     * @param newValueX is a new value of X-coordinate of the point.
     * @param newValueY is a new value of Y-coordinate of the point.
     * @throws InvalidPointNameException is thrown when point name received
     * by the method is not valid.
     */
    @Test (dataProvider = "test1")
    public void testChangeData(final int id, final char pointName,
                               final double newValueX,
                               final double newValueY)
            throws InvalidPointNameException {

        double perimeter = SearchById.getTriangle(id).getRegistrator()
                .getPerimeter();
        double square = SearchById.getTriangle(id).getRegistrator().
                getSquare();

        TriangleChanger.changeData(id, pointName, newValueX, newValueY);

        Assert.assertNotEquals(perimeter,
                SearchById.getTriangle(id).getRegistrator().getPerimeter());
        Assert.assertNotEquals(square,
                SearchById.getTriangle(id).getRegistrator().getSquare());
    }

    /**
     * Tests changeData() method with input data which includes
     * coordinate values which coincide with initial coordinate values.
     * @param id is triangle's id.
     * @param pointName is a name of changing point (A, B or C).
     * @param newValueX is a new value of X-coordinate of the point.
     * @param newValueY is a new value of Y-coordinate of the point.
     * @throws InvalidPointNameException is thrown when point name received
     * by the method is not valid.
     */
    @Test (dataProvider = "test2")
    public void testChangeData2(final int id, final char pointName,
                                final double newValueX,
                                final double newValueY)
            throws InvalidPointNameException {

        double perimeter = SearchById.getTriangle(id).getRegistrator()
                .getPerimeter();
        double square = SearchById.getTriangle(id).getRegistrator().
                getSquare();

        TriangleChanger.changeData(id, pointName, newValueX, newValueY);

        Assert.assertEquals(perimeter,
                SearchById.getTriangle(id).getRegistrator().getPerimeter());
        Assert.assertEquals(square,
                SearchById.getTriangle(id).getRegistrator().getSquare());
    }

    /**
     * Tests changeData() method with input data which includes invalid point name.
     * @param id is triangle's id.
     * @param pointName is a name of changing point (A, B or C).
     * @param newValueX is a new value of X-coordinate of the point.
     * @param newValueY is a new value of Y-coordinate of the point.
     * @throws InvalidPointNameException is thrown when point name received
     * by the method is not valid.
     */
    @Test (dataProvider = "test3", expectedExceptions = InvalidPointNameException.class)
    public void testChangeData3(final int id, final char pointName,
                                final double newValueX,
                                final double newValueY) throws InvalidPointNameException {

        double perimeter = SearchById.getTriangle(id).getRegistrator()
                .getPerimeter();
        double square = SearchById.getTriangle(id).getRegistrator().
                getSquare();

        TriangleChanger.changeData(id, pointName, newValueX, newValueY);
    }
}
