package by.epam.figures.repository;

import by.epam.figures.entity.Registrator;
import by.epam.figures.entity.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

/**
 * {@link Repository} class allows you to manage the {@link Storage}.
 */
public class Repository {
    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER = LogManager.getLogger(Repository.class);

    private Repository() { }

    /**
     * Method adds a pair of triangle and it's {@link Registrator}
     * to the storage.
     *
     * @param triangle    is a {@link Triangle} object that need to be added
     * to the storage.
     */
    public static void add(final Triangle triangle) {

        triangle.setId(Storage.getInstance().getStorageSet().size() + 1);
        Storage.getInstance().getStorageSet().add(triangle);
    }

    /**
     * Method removes triangle and it's {@link Registrator}
     * from the storage.
     *
     * @param id is an id of the triangle which should be remove.
     */
    public static void remove(final int id) {

        Iterator it = Storage.getInstance().getStorageSet().
                iterator();

        Storage.getInstance().
                getStorageSet().forEach(triangle -> {

            if (triangle.getId() == id) {
                it.remove();
            }
        });
    }

    /**
     * Removes all the elements from the storage.
     */
    public static void removeAll() {
        Storage.getInstance().getStorageSet().clear();
    }

    /**
     * Method updates a {@link Registrator} attached to the triangle.
     *
     * @param id  is an id of the changed triangle.
     * @param perimeter is a perimeter of changed triangle.
     * @param square is a square of changed triangle.
     */
    public static void update(final int id,
                              final double perimeter,
                              final double square) {

        LOGGER.log(Level.DEBUG, "Repository updating");

        Storage.getInstance().
                getStorageSet().forEach(triangle -> {

            if (triangle.getId() == id) {
                triangle.setRegistrator(perimeter, square);
            }
        });
    }
}
