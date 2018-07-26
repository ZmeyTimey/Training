package by.epam.port.runner;

import by.epam.port.creator.ShipCreator;
import by.epam.port.entity.Store;
import by.epam.port.exception.FileReadingException;
import by.epam.port.exception.InvalidShipDataException;
import by.epam.port.parser.ShipsDataParser;
import by.epam.port.reader.PortDataFileReader;
import by.epam.port.reader.ShipsDataFileReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

/**
 * Runner for the Port application.
 */
public final class Runner {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(PortDataFileReader.class);

    /**
     * Private constructor for this class.
     */
    private Runner() {
    }

    /**
     * Main method of the app.
     *
     * @param args is an array of command-line arguments.
     */
    public static void main(final String[] args) {

        Store store = Store.getInstance();

        Semaphore semaphore = new Semaphore(store.getNumberOfDocks(), true);

        ShipsDataFileReader reader = new ShipsDataFileReader("/ShipsData.txt");
        ShipsDataParser parser = new ShipsDataParser();

        List<String> shipsLineList;
        List<FutureTask<String>> taskList = new ArrayList<>();
        List<Thread> threadList = new ArrayList<>();

        try {
            shipsLineList = reader.read();

            int i = 0;
            while (i < shipsLineList.size()) {

                parser.parse(shipsLineList.get(i));

                try {
                    FutureTask<String> task = new FutureTask<>(
                            ShipCreator.createShip(
                                    semaphore,
                                    parser.getShipName(),
                                    parser.getNominalVolume(),
                                    parser.getOccupiedVolume(),
                                    parser.getUnloadVolume(),
                                    parser.getLoadVolume()
                            ));

                    taskList.add(task);
                    threadList.add(new Thread(task));

                } catch (InvalidShipDataException e) {
                    LOGGER.log(Level.ERROR, e.getMessage());
                }
                i++;
            }

            i = 0;
            while (i < threadList.size()) {
            threadList.get(i).start();
            i++;
            }

        } catch (FileReadingException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
        }
    }
}
