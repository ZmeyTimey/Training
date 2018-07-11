package by.epam.figures.creator;

import by.epam.figures.entity.Point2D;

/**
 * Contains method createPoint which creates a new {@link Point2D} object.
 */
public class PointCreator {

    public static Point2D createPoint(double x, double y) {
        return new Point2D(x, y);
    }
}
