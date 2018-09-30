package by.epam.figures.creator;

import by.epam.figures.action.TriangleAction;
import by.epam.figures.entity.Triangle;

import by.epam.figures.entity.Point2D;
import by.epam.figures.exception.OutOfDoubleRangeException;
import by.epam.figures.exception.PointsFormLineException;
import by.epam.figures.repository.Repository;
import by.epam.figures.validator.TriangleValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains a method createTriangle which creates a new {@link Triangle} object
 * from three {@link Point2D} objects.
 */
public final class TriangleCreator {
    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(TriangleCreator.class);

    /**
     * Private constructor.
     */
    private TriangleCreator() {
        throw new AssertionError(
                "Creating an object of this class is not allowed");
    }

    /**
     * Method creates a new {@link Triangle} object with three incoming
     * point values and put it into the storage.
     * @param name is a triangle's name.
     * @param p1 is a first point of triangle.
     * @param p2 is a second point of triangle.
     * @param p3 is a third point of triangle.
     * @return a new triangle with given three points.
     * @throws PointsFormLineException is thrown when three given points
     * are on one line.
     */
    public static Triangle createTriangle(final String name,
                                      final Point2D p1,
                                      final Point2D p2,
                                      final Point2D p3)
            throws PointsFormLineException {

        Triangle triangle;
        if (TriangleValidator.pointsFormATriangle(p1, p2, p3)) {

            triangle = new Triangle(name, p1, p2, p3);
            LOGGER.log(Level.DEBUG, triangle + " has been created");

            try {
                TriangleAction action = new TriangleAction();

                triangle.setRegistrator(action.calcPerimeter(triangle),
                            action.calcSquare(triangle));
                Repository.add(triangle);

                LOGGER.log(Level.DEBUG,
                        "Triangle has been added to the storage.");

            } catch (OutOfDoubleRangeException ex) {
                LOGGER.log(Level.ERROR, ex.getMessage() + " Enable to put "
                        + triangle + " into the storage.");
            }

        } else {
            LOGGER.log(Level.ERROR, "Points ("
                    + p1.getX() + ", " + p1.getY() + "); ("
                    + p2.getX() + ", " + p2.getY() + "); ("
                    + p3.getX() + ", " + p3.getY()
                    + ") doesn't form a triangle");
            throw new PointsFormLineException();
        }

        return triangle;
    }
}



