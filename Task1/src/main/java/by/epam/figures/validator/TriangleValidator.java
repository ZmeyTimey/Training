package by.epam.figures.validator;

import by.epam.figures.entity.Point2D;

/**
 * Created by Тимей on 29.06.2018.
 *
 */
public class TriangleValidator {

    public static boolean pointsFormATriangle(Point2D p1, Point2D p2, Point2D p3) {

        return ! (((p3.getX() - p1.getX()) / (p2.getX() - p1.getX()))
                == ((p3.getY() - p1.getY()) / (p2.getY() - p1.getY())));
    }
}
