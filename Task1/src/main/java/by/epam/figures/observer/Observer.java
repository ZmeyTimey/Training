package by.epam.figures.observer;

import by.epam.figures.action.TriangleAction;
import by.epam.figures.entity.Registrator;
import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.OutOfDoubleRangeException;
import by.epam.figures.repository.Repository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Observes class {@link Triangle} objects' fields changes
 * and causes recounting of triangles' perimeters and squares.
 */
public class Observer {
    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Observer.class);

    /**
     * Updates {@link Registrator} data connected with changed
     * {@link Triangle} object.
     * @param triangle is changed {@link Triangle} object.
     */
    public void update(final Triangle triangle) {

        TriangleAction action = new TriangleAction();

        LOGGER.log(Level.DEBUG, "Observer detected changes");

        double newPerimeter = 0;
        double newSquare = 0;

        try {
            newPerimeter = action.calcPerimeter(triangle);
            newSquare = action.calcSquare(triangle);

        } catch (OutOfDoubleRangeException ex) {
            ex.printStackTrace();
        }
            Repository.update(triangle.getId(), newPerimeter, newSquare);
    }
}
