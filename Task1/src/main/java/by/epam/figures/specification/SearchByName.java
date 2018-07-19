package by.epam.figures.specification;

import by.epam.figures.entity.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class is for searching {@link Triangle} objects by name.
 */
public class SearchByName extends Specification {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER = LogManager.getLogger(SearchById.class);

    /**
     * Method searches triangle with adjusted name in the storage.
     * @param name is a name of the triangle which is needed to be found.
     * @return triangle with adjusted id.
     */
    public static Triangle getTriangle(final String name) {

        List<Triangle> triangleList = createTriangleList();
        Triangle searchedTriangle = null;

        int i = 0;
        while (i < triangleList.size()) {

            if (triangleList.get(i).getName().equals(name)) {
                searchedTriangle = triangleList.get(i);
            }
            i++;
        }

        if (searchedTriangle == null) {
            LOGGER.log(Level.WARN, "Triangle with a name " + name
                    + " not found");
        }
        return searchedTriangle;
    }

    /**
     * Method searches all the triangles with adjusted name in the storage.
     * @param name is a name of triangles which is needed to be found.
     * @return list of triangles with adjusted name.
     */
    public static List<Triangle> getTriangles(final String name) {

        List<Triangle> initialList = createTriangleList();
        List<Triangle> resultList = new ArrayList<>();

        int i = 0;
        while ( i < initialList.size()) {

            if (initialList.get(i).getName().equals(name)) {
                resultList.add(initialList.get(i));
            }
        }

        if (resultList.isEmpty()) {
            LOGGER.log(Level.WARN, "No one triangle with a name " + name
                    + " found");
        }
        return resultList;
    }
}
