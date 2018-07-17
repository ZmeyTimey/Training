package by.epam.figures.action;

import by.epam.figures.creator.PointCreator;
import by.epam.figures.entity.Point2D;
import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.InvalidAxisNameException;
import by.epam.figures.exception.InvalidInputDataException;
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
     * Changes coordinate of adjusted triangle's point.
     * @param id is triangle's id.
     * @param pointName is a name of changing point (A, B or C).
     * @param axis is an axis name of coordinate of a point (X or Y).
     * @param newValue is a new value of adjusted coordinate.
     * @throws InvalidInputDataException is thrown when when data received
     * by the method is not valid.
     */
    public static void changeData(final int id, final char pointName,
                                  final char axis, final double newValue)
            throws InvalidInputDataException {

        Triangle triangle = SearchById.getTriangle(id);
        LOGGER.log(Level.DEBUG, "Changing point " + pointName + ", coordinate "
                + axis + " of " + triangle);

        switch (pointName) {
            case 'A':
                changePointCoordinate(triangle.getPointA(), axis, newValue);
                break;
            case 'B':
                changePointCoordinate(triangle.getPointB(), axis, newValue);
                break;
            case'C':
                changePointCoordinate(triangle.getPointC(), axis, newValue);
                break;
            default:
                LOGGER.log(Level.WARN, "Invalid point name!");
                throw new InvalidPointNameException();
        }
    }

    /**
     * Subsidiary method for setting a concrete coordinate of a point value.
     * @param point is a point whose coordinate should be changed.
     * @param axis is an axis name of coordinate of a point (X or Y).
     * @param newValue is a new value of adjusted coordinate.
     * @throws InvalidAxisNameException when a name of coordinate axis received
     * by the method is not valid.
     */
    private static void changePointCoordinate(final Point2D point,
                                              final char axis,
                                              final double newValue)
            throws InvalidAxisNameException {

        switch (axis) {
            case 'X':
                point.setX(newValue);
                break;
            case 'Y':
                point.setY(newValue);
                break;
            default:
                LOGGER.log(Level.WARN, "Invalid coordinate axis name");
                throw new InvalidAxisNameException();
        }
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
                triangle.getPointB().setX(newValueX);
                triangle.getPointB().setY(newValueY);
                break;
            case'C':
                triangle.getPointC().setX(newValueX);
                triangle.getPointC().setY(newValueY);
                break;
            default:
                LOGGER.log(Level.WARN, "Invalid point name!");
                throw new InvalidPointNameException();
        }
    }
}
