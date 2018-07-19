package by.epam.figures.repository;

import by.epam.figures.entity.Registrator;
import by.epam.figures.entity.Triangle;

import java.util.HashSet;
import java.util.Set;

/**
 * This class stores links to {@link Triangle} objects and
 * {@link Registrator} instances attached to them. Uses the Singleton pattern.
 */
public class Storage {
    /**
     * A map which stores links to triangles and their registrators.
     */
    private Set<Triangle> storageSet
            = new HashSet<>();

    /**
     * A single instance of {@link Storage} is specified
     * to be the only one possible.
     */
    private static final Storage INSTANCE = new Storage();

    /**
     * Private constructor.
     */
    private Storage() { }

    /**
     * @return single instance of {@link Storage}.
     */
    public static Storage getInstance() {
        return INSTANCE;
    }

    /**
     * @return storage map.
     */
    public Set<Triangle> getStorageSet() {
        return storageSet;
    }
}
