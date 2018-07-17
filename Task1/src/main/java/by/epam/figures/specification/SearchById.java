package by.epam.figures.specification;

import by.epam.figures.entity.Registrator;
import by.epam.figures.entity.Triangle;
import by.epam.figures.repository.Storage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Map;

/**
 * Class is for searching {@link Triangle} objects by id.
 */
public class SearchById {

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

        Iterator it = Storage.getInstance().getStorageMap()
                .entrySet().iterator();

        Triangle triangle = null;

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Triangle tr = (Triangle) pair.getKey();

            if (tr.getId() == id) {
                triangle = tr;
            }
        }

        if (triangle == null) {
            LOGGER.log(Level.WARN, "Triangle with id = " + id
                    + " not found");
        }
        return triangle;
    }

    /**
     * Method searches {@link Registrator} object connected with triangle
     * with adjusted id in the storage.
     * @param id is id of the triangle whose {@link Registrator}
     * is needed to be found.
     * @return {@link Registrator} of the triangle with adjusted id.
     */
    public static Registrator getRegistrator(final int id) {

        Iterator it = Storage.getInstance().getStorageMap()
                .entrySet().iterator();

        Registrator registrator = null;

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Triangle tr = (Triangle) pair.getKey();
            Registrator reg = (Registrator) pair.getValue();

            if (tr.getId() == id) {
                registrator = reg;
            }
        }

        if (registrator == null) {
            LOGGER.log(Level.WARN, "Registrator of the triangle with id = "
                    + id + " not found");
        }
        return registrator;
    }
}
