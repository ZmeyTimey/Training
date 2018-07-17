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

        try {
            Registrator reg = new Registrator(action.calcPerimeter(triangle),
                    action.calcSquare(triangle));

            Repository.update(triangle.getId(), triangle.getName(), reg);

            } catch (OutOfDoubleRangeException ex) {
            LOGGER.log(Level.ERROR, ex.getMessage());
        }
    }
}
