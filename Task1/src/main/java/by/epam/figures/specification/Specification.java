package by.epam.figures.specification;

import by.epam.figures.entity.Triangle;
import by.epam.figures.repository.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrap class for the specification classes.
 */
abstract class Specification {

    /**
     * Method creates a list with {@link Triangle} objects from the storage.
     * @return created list.
     */
   static List<Triangle> createTriangleList() {
       List<Triangle> list = new ArrayList<>();

       Storage.getInstance().
               getStorageSet().addAll(list);

       return list;
   }
}
