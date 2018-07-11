package by.epam.figures.creator;

import by.epam.figures.entity.Triangle;

import by.epam.figures.entity.Point2D;
import by.epam.figures.exception.PointsFormLineException;
import by.epam.figures.validator.TriangleValidator;

/**
 * Contains a method createTriangle which creates a new {@link Triangle} object from three {@link Point2D} objects.
 */
public class TriangleCreator {

    public static Triangle createTriangle(Point2D p1, Point2D p2, Point2D p3) throws PointsFormLineException {
        if (TriangleValidator.pointsFormATriangle(p1, p2, p3)) {
            return new Triangle(p1, p2, p3);
        } else {
            throw new PointsFormLineException("Points ("
                    + p1.getX() + ", " + p1.getY() + "); ("
                    + p2.getX() + ", " + p2.getY() + "); ("
                    + p3.getX() + ", " + p3.getY() + ") doesn't form a triangle");
        }
    }
}



