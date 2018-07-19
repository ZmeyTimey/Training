package by.epam.figures.action;

import by.epam.figures.creator.PointCreator;
import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.InvalidPointNameException;
import by.epam.figures.specification.SearchById;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TriangleChanger {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(TriangleChanger.class);

    /**
     * Changes coordinates of adjusted triangle's point.
     * @param id is triangle's id.
     * @param pointName is a name of changing point (A, B or C).
     * @param newValueX is a new value of X-coordinate of the point.
     * @param newValueY is a new value of Y-coordinate of the point.
     * @throws InvalidPointNameException is thrown when point name received
     * by the method is not valid.
     */
    public static void changeData(final int id, final char pointName,
                                  final double newValueX,
                                  final double newValueY)
            throws InvalidPointNameException {

        Triangle triangle = SearchById.getTriangle(id);

        switch (pointName) {
            case 'A':
                triangle.setPointA(PointCreator
                        .createPoint(newValueX, newValueY));
                break;
            case 'B':
                triangle.setPointB(PointCreator
                        .createPoint(newValueX, newValueY));
                break;
            case'C':
                triangle.setPointC(PointCreator
                        .createPoint(newValueX, newValueY));
                break;
            default:
                LOGGER.log(Level.WARN, "Invalid point name!");
                throw new InvalidPointNameException();
        }
    }
}
