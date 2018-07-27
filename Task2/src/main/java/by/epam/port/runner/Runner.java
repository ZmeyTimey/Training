package by.epam.port.runner;

import by.epam.port.creator.ShipCreator;
import by.epam.port.entity.Ship;
import by.epam.port.entity.Store;
import by.epam.port.exception.AppException;
import by.epam.port.parser.ShipsDataParser;
import by.epam.port.reader.PortDataFileReader;
import by.epam.port.reader.ShipsDataFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

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
        Phaser phaser = new Phaser();
        phaser.register();

        List<String> shipsLineList;
        List<Future<String>> futureList = new ArrayList<>();
        List<Ship> shipList = new ArrayList<>();

        try {
            shipsLineList = reader.read();

            int i = 0;
            while (i < shipsLineList.size()) {

                parser.parse(shipsLineList.get(i));

                try {
                    Ship ship =
                            ShipCreator.createShip(
                                    semaphore,
                                    parser.getShipName(),
                                    parser.getNominalVolume(),
                                    parser.getOccupiedVolume(),
                                    parser.getUnloadVolume(),
                                    parser.getLoadVolume());

                    shipList.add(ship);

                } catch (AppException e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error(e.getMessage());
                    }
                }
                i++;
            }

            ExecutorService executor
                    = Executors.newFixedThreadPool(shipList.size());

            i = 0;
            while (i < shipList.size()) {

                final int WAIT_TIME = 100;
                Future<String> future = executor.submit(shipList.get(i));
                futureList.add(future);

                try {
                    TimeUnit.MILLISECONDS.sleep(WAIT_TIME);

                } catch (InterruptedException e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error(e.getMessage());
                    }
                }
                i++;
            }

            i = 0;
            while (i < futureList.size()) {

                try {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(futureList.get(i).get());
                    }

                } catch (Exception e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error(e.getMessage());
                    }
                }
                i++;
            }

            executor.shutdown();

        } catch (AppException e) {
            LOGGER.fatal(e.getMessage());
        }
    }
}
