package by.epam.figures.specification;

import by.epam.figures.entity.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

/**
 * This class is for sorting triangles from the storage by id.
 */
public class SorterById extends Specification implements Comparator<Triangle> {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SorterById.class);

    /**
     * Compares the values of id of two triangles.
     * @param triangle1 is the first of comparing triangles.
     * @param triangle2 is the second of comparing triangles.
     * @return the difference of the id values.
     */
    @Override
    public int compare(final Triangle triangle1,
                       final Triangle triangle2) {
        return triangle1.getId() - triangle2.getId();
    }

    /**
     * Method sorts triangles from the storage by id.
     * @return sorted list of {@link Triangle} objects.
     */
    public static List<Triangle> sort() {

        LOGGER.log(Level.DEBUG, "Sorting triangles by id");

        List<Triangle> triangleList = createTriangleList();

        triangleList.sort(new SorterById());
        LOGGER.log(Level.DEBUG, "Sorting completed");
        return triangleList;
    }
}
