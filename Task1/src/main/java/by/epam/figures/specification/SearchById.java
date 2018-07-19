package by.epam.figures.specification;

import by.epam.figures.entity.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This class is for searching {@link Triangle} objects by id.
 */
public class SearchById extends Specification {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER = LogManager.getLogger(SearchById.class);

    /**
     * Method searches triangle with adjusted id in the storage.
     * @param id is id of the triangle which is needed to be found.
     * @return triangle with adjusted id.
     */
    public static Triangle getTriangle(final int id) {

        List<Triangle> triangleList = createTriangleList();
        Triangle triangle = null;

        int i = 0;
        while (i < triangleList.size()) {

            if (triangleList.get(i).getId() == id) {
                triangle = triangleList.get(i);
            }
            i++;
        }

        if (triangle == null) {
            LOGGER.log(Level.WARN, "Triangle with id = " + id
                    + " not found");
        }
        return triangle;
    }
}
