package by.epam.port.runner;

import by.epam.port.creator.ShipCreator;
import by.epam.port.entity.Ship;
import by.epam.port.entity.Store;
import by.epam.port.exception.AppException;
import by.epam.port.parser.ShipDataParser;
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

        final Store STORE = Store.getInstance();

        final int DOCKS = STORE.getNumberOfDocks();
        final Semaphore SEMAPHORE = new Semaphore(DOCKS, true);

        final ShipsDataFileReader READER = new ShipsDataFileReader("/ShipsData.txt");
        final ShipDataParser PARSER = new ShipDataParser();
        final Phaser PHASER = new Phaser();
        PHASER.register();

        List<String> shipsLineList;
        final List<Future<String>> FUTURE_LIST = new ArrayList<>();
        final List<Ship> SHIP_LIST = new ArrayList<>();

        try {
            shipsLineList = READER.read();

            int counter = 0;
            while (counter < shipsLineList.size()) {

                PARSER.parse(shipsLineList.get(counter));

                try {
                    final Ship SHIP =
                            ShipCreator.createShip(
                                    SEMAPHORE,
                                    PARSER.getShipName(),
                                    PARSER.getNominalVolume(),
                                    PARSER.getOccupiedVolume(),
                                    PARSER.getUnloadVolume(),
                                    PARSER.getLoadVolume());

                    SHIP_LIST.add(SHIP);

                } catch (AppException e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error(e.getMessage());
                    }
                }
                counter++;
            }

            final ExecutorService EXECUTOR
                    = Executors.newFixedThreadPool(SHIP_LIST.size());

            counter = 0;
            while (counter < SHIP_LIST.size()) {

                final int WAIT_TIME = 100;
                final Future<String> FUTURE
                        = EXECUTOR.submit(SHIP_LIST.get(counter));
                FUTURE_LIST.add(FUTURE);

                try {
                    TimeUnit.MILLISECONDS.sleep(WAIT_TIME);

                } catch (InterruptedException e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error(e.getMessage());
                    }
                }
                counter++;
            }

            counter = 0;
            while (counter < FUTURE_LIST.size()) {

                try {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(FUTURE_LIST.get(counter).get());
                    }

                } catch (Exception e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error(e.getMessage());
                    }
                }
                counter++;
            }

            EXECUTOR.shutdown();

        } catch (AppException e) {
            LOGGER.fatal(e.getMessage());
        }
    }
}
