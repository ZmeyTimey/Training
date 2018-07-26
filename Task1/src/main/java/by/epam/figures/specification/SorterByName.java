package by.epam.figures.specification;

import by.epam.figures.entity.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

/**
 * This class is for sorting triangles from the storage by name.
 */
public class SorterByName extends Specification implements Comparator<Triangle> {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SorterById.class);

    /**
     * Compares the names of two triangles by their alphabet order.
     * @param triangle1 is the first of comparing triangles.
     * @param triangle2 is the second of comparing triangles.
     * @return the result of comparison triangles' names.
     */
    @Override
    public int compare(final Triangle triangle1,
                       final Triangle triangle2) {
        return triangle1.getName().compareToIgnoreCase(triangle2.getName());
    }
    /**
     * Method sorts triangles from the storage by name.
     * @return sorted list of {@link Triangle} objects.
     */
    public static List<Triangle> sort() {

        LOGGER.log(Level.DEBUG, "Sorting triangles by name");

        List<Triangle> triangleList = createTriangleList();

        triangleList.sort(new SorterByName());
        LOGGER.log(Level.DEBUG, "Sorting completed");
        return triangleList;
    }
}
