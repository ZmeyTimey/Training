package by.epam.figures.repository;

import by.epam.figures.entity.Registrator;
import by.epam.figures.entity.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Map;

/**
 * {@link Repository} class allows you to manage the {@link Storage}.
 */
public class Repository {
    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER = LogManager.getLogger(Repository.class);

    /**
     * Method adds a pair of triangle and it's {@link Registrator}
     * to the storage.
     * @param triangle is a {@link Triangle} object that need to be added
     * to the storage.
     * @param registrator is a {@link Registrator} object attached to triangle.
     */
    public static void add(final Triangle triangle,
                           final Registrator registrator) {

        triangle.setId(Storage.getInstance().getStorageMap().size() + 1);
        Storage.getInstance().getStorageMap().put(triangle, registrator);
    }

    /**
     * Method removes triangle and it's {@link Registrator}
     * from the storage.
     * @param id is an id of the triangle which should be remove.
     */
    public static void remove(final int id) {

        Iterator it = Storage.getInstance().getStorageMap().
                entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            Triangle tr = (Triangle) pair.getKey();

            if (tr.getId() == id) {
                it.remove();
            }
        }
    }

    /**
     * Removes all the elements from the storage.
     */
    public static void removeAll() {
        Storage.getInstance().getStorageMap().clear();
    }
    /**
     * Method updates a {@link Registrator} attached to the triangle.
     * @param id is an id of the changed triangle.
     * @param name is triangle's name.
     * @param reg is a new {@link Registrator} for the changed triangle.
     */
    public static void update(final int id,
                              final String name,
                              final Registrator reg) {

        Iterator it = Storage.getInstance().getStorageMap().
                entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Triangle tr = (Triangle) pair.getKey();

            if (tr.getId() == id) {

                it.remove();
                tr.setId(id);
                Storage.getInstance().getStorageMap().put(tr, reg);
            }
        }
    }
}
