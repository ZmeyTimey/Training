package by.epam.figures.creator;

import by.epam.figures.entity.Point2D;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains method createPoint which creates a new {@link Point2D} object.
 */
public class PointCreator {
    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(PointCreator.class);

    /**
     *
     * @param x is X-coordinate of a point.
     * @param y is Y-coordinate of a point.
     * @return a new point with given coordinates.
     */
    public static Point2D createPoint(final double x, final double y) {

        Point2D point = new Point2D(x, y);

        LOGGER.log(Level.DEBUG, point + " is created");
        return point;
    }
}
