package by.epam.figures.specification;

import by.epam.figures.entity.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for sorting triangles from the storage by id.
 */
public class SorterById extends Specification {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SorterById.class);

    /**
     * Method sorts triangles from the storage by id.
     * @return sorted list of {@link Triangle} objects.
     */
    public static List<Triangle> sort() {

        LOGGER.log(Level.DEBUG, "Sorting triangles by id");

        List<Triangle> initialList = createTriangleList();
        List<Triangle> resultList = new ArrayList<>();

        int i = 1;
        while (i < initialList.size()) {

            if (i == 0 || initialList.get(i - 1).getId()
                    <= initialList.get(i).getId()) {
                i++;

            } else {
                int temp = initialList.get(i).getId();
                initialList.get(i).setId(initialList.get(i - 1).getId());
                initialList.get(i - 1).setId(temp);
                i--;
            }
        }
        LOGGER.log(Level.DEBUG, "Sorting completed");
        return resultList;
    }
}
