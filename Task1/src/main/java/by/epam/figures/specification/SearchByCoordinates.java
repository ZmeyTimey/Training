package by.epam.figures.specification;

import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.InvalidQuadrantNumberException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for searching triangles in the storage which are
 * fully located in adjusted quadrant.
 */
public class SearchByCoordinates extends Specification {

    /**
     * Means that there is only four quadrants on the coordinate plane.
     */
    private static final int NUMBER_OF_QUADRANTS = 4;

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SearchByCoordinates.class);

    /**
     * Method searches triangle's whose all the points
     * are in adjusted quadrant.
     * @param quadrant is an adjusted quadrant.
     * @return list of triangles that satisfy the conditions.
     * @throws InvalidQuadrantNumberException when input value of quadrant
     * is not in the range from 1 to 4.
     */
    public static List<Triangle> getTrianglesInQuadrant(final int quadrant)
            throws InvalidQuadrantNumberException {

        List<Triangle> initialList = createTriangleList();
        List<Triangle> resultList = new ArrayList<>();


        if (quadrant >= 1 && quadrant <= NUMBER_OF_QUADRANTS) {
            initialList.forEach(triangle -> {
                if (isInQuadrant(quadrant, triangle)) {
                    resultList.add(triangle);
                }
            });
            return resultList;

        } else {
            LOGGER.log(Level.ERROR, "Invalid quadrant number!");
            throw new InvalidQuadrantNumberException();
        }
    }

    /**
     * Method verifies whether the triangle is in the first quadrant.
     * @param triangle is the triangle which should be verified.
     * @return boolean value is the triangle in the first quadrant.
     */
    private static boolean isInFirstQuadrant(final Triangle triangle) {

        return (triangle.getPointA().getX() > 0
                && triangle.getPointA().getY() > 0)
                && (triangle.getPointB().getX() > 0
                && triangle.getPointB().getY() > 0)
                && (triangle.getPointC().getX() > 0
                && triangle.getPointC().getY() > 0);
    }

    /**
     * Method verifies whether the triangle is in the second quadrant.
     * @param triangle is the triangle which should be verified.
     * @return boolean value is the triangle in the second quadrant.
     */

    private static boolean isInSecondQuadrant(final Triangle triangle) {

        return (triangle.getPointA().getX() < 0
                && triangle.getPointA().getY() > 0)
                && (triangle.getPointB().getX() < 0
                && triangle.getPointB().getY() > 0)
                && (triangle.getPointC().getX() < 0
                && triangle.getPointC().getY() > 0);
    }

    /**
     * Method verifies whether the triangle is in the third quadrant.
     * @param triangle is the triangle which should be verified.
     * @return boolean value is the triangle in the third quadrant.
     */
    private static boolean isInThirdQuadrant(final Triangle triangle) {

        return (triangle.getPointA().getX() < 0
                && triangle.getPointA().getY() < 0)
                && (triangle.getPointB().getX() < 0
                && triangle.getPointB().getY() < 0)
                && (triangle.getPointC().getX() < 0
                && triangle.getPointC().getY() < 0);
    }

    /**
     * Method verifies whether the triangle is in the fourth quadrant.
     * @param triangle is the triangle which should be verified.
     * @return boolean value is the triangle in the fourth quadrant.
     */
    private static boolean isInFourthQuadrant(final Triangle triangle) {

        return (triangle.getPointA().getX() > 0
                && triangle.getPointA().getY() < 0)
                && (triangle.getPointB().getX() > 0
                && triangle.getPointB().getY() < 0)
                && (triangle.getPointC().getX() > 0
                && triangle.getPointC().getY() < 0);
    }

    /**
     * Method verifies whether the triangle is in the adjusted quadrant.
     * @param quadrant is the adjusted quadrant.
     * @param triangle is the triangle which should be verified.
     * @return boolean value is the triangle in the adjusted quadrant.
     */
    private static boolean isInQuadrant(final int quadrant,
                                        final Triangle triangle) {

        switch (quadrant) {
            case 1:
                return isInFirstQuadrant(triangle);
            case 2:
                return isInSecondQuadrant(triangle);
            case NUMBER_OF_QUADRANTS - 1:
                return isInThirdQuadrant(triangle);
            case NUMBER_OF_QUADRANTS:
                return isInFourthQuadrant(triangle);
            default:
                return false;
        }
    }
}
