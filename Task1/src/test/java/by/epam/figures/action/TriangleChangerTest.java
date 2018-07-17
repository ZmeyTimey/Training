package by.epam.figures.action;

import by.epam.figures.creator.TriangleCreator;
import by.epam.figures.entity.Point2D;
import by.epam.figures.entity.Registrator;
import by.epam.figures.exception.InvalidInputDataException;
import by.epam.figures.exception.InvalidPointNameException;
import by.epam.figures.exception.PointsFormLineException;
import by.epam.figures.repository.Repository;
import by.epam.figures.specification.SearchById;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * A test for {@link TriangleChanger} class.
 */
public class TriangleChangerTest {
    /**
     * A list of {@link Registrator} objects that would be compared with
     * registrators after changing triangles which are
     * connected with them.
     */
    List<Registrator> regList = new ArrayList<>();

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

        regList.add(SearchById.getRegistrator(1));
        regList.add(SearchById.getRegistrator(2));
        regList.add(SearchById.getRegistrator(3));
    }

    /**
     * @return data which is necessary for starting method
     * changeData().
     */
    @DataProvider(name = "test1")
    public static Object[][] inputData() {
        return new Object[][]
                {{1, 'A', 'X', 6.0},
                {2, 'B', 'Y', 600.0},
                {3, 'C', 'X', -1.5e+100}};
    }

    /**
     * Changes coordinates of adjusted triangle's point.
     * @param id is triangle's id.
     * @param pointName is a name of changing point (A, B or C).
     * @param newValueX is a new value of X-coordinate of the point.
     * @param newValueY is a new value of Y-coordinate of the point.
     * @throws InvalidPointNameException is thrown when when data received
     * by the method is not valid.
     */
    @Test (dataProvider = "test1")
    public void testChangeData(final int id, final char pointName,
                               final double newValueX,
                               final double newValueY)
            throws InvalidInputDataException {

        TriangleChanger.changeData(id, pointName, newValueX, newValueY);
        System.out.println(regList.get(id - 1));
        System.out.println(SearchById.getRegistrator(id));

        Assert.assertNotEquals(regList.get(id - 1),
                SearchById.getRegistrator(id));
    }

    @Test
    public void testChangeData1() {
    }
}
