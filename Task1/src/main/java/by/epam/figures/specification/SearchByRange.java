package by.epam.figures.specification;

import by.epam.figures.entity.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for searching triangles whose perimeters or squares
 * are in the adjusted range.
 */
public class SearchByRange extends Specification {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SearchByRange.class);

    /**
     * Method searches triangles whose perimeters are in the adjusted range.
     * @param lowerBound is a lower bound of the range.
     * @param upperBound is a upper bound of the range.
     * @return a list of needed triangles.
     */
    public static List<Triangle> getTrianglesInPerimeterRange(
            final double lowerBound,
            final double upperBound) {

        LOGGER.log(Level.DEBUG, "Searching triangles with perimeters"
                + " in the range " + lowerBound + "..." + upperBound);

        List<Triangle> initialList = createTriangleList();
        List<Triangle> resultList = new ArrayList<>();

        int i = 0;
        while (i < initialList.size()) {

            double perimeter = initialList.get(i)
                    .getRegistrator().getPerimeter();

            if (perimeter >= lowerBound && perimeter <= upperBound) {
                resultList.add(initialList.get(i));
            }

            if (!resultList.isEmpty()) {
                LOGGER.log(Level.DEBUG, "Needed triangles found.");
            } else {
                LOGGER.log(Level.DEBUG, "No triangles with perimeters"
                        + " in the adjusted range found.");
            }
        }
        return resultList;
    }

    /**
     * Method searches triangles whose squares are in the adjusted range.
     * @param lowerBound is a lower bound of the range.
     * @param upperBound is a upper bound of the range.
     * @return a list of needed triangles.
     */
    public static List<Triangle> getTriangleInSquareRange(
            final double lowerBound,
            final double upperBound) {

        LOGGER.log(Level.DEBUG, "Searching triangles with squares"
                + " in the range " + lowerBound + "..." + upperBound);

        List<Triangle> initialList = createTriangleList();
        List<Triangle> resultList = new ArrayList<>();

        int i = 0;
        while (i < initialList.size()) {

            double square = initialList.get(i)
                    .getRegistrator().getSquare();

            if (!resultList.isEmpty()) {
                LOGGER.log(Level.DEBUG, "Needed triangles found.");
            } else {
                LOGGER.log(Level.DEBUG, "No triangles with squares"
                        + " in the adjusted range found.");
            }
        }
        return resultList;
    }
}
