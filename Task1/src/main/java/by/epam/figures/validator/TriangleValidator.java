package by.epam.figures.validator;

import by.epam.figures.entity.Point2D;

/**
 * {@link TriangleValidator} checks are three points form a triangle
 * or they are on one line.
 */
public class TriangleValidator {
    /**
     * @param p1 is a point A of supposed triangle.
     * @param p2 is a point B of supposed triangle.
     * @param p3 is a point C of supposed triangle.
     * @return conditions indicating that three points form a triangle.
     */
    public static boolean pointsFormATriangle(final Point2D p1,
                                              final Point2D p2,
                                              final Point2D p3) {
        return !(((p3.getX() - p1.getX()) * (p2.getY() - p1.getY()))
                == ((p3.getY() - p1.getY()) * (p2.getX() - p1.getX())));
    }
}
