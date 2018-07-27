package by.epam.port.entity;

import by.epam.port.exception.AppException;
import by.epam.port.parser.PortDataParser;
import by.epam.port.reader.PortDataFileReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class describes the functioning of port store which stores
 * containers downloading and unloading from the port.
 */
public final class Store {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Store.class);
    /**
     * The number of containers that the store can accommodate.
     */
    private final transient int NOMINAL_VOLUME;
    /**
     * The number of docks in the port.
     */
    private final transient int NUMBER_OF_DOCKS;

    /**
     * A single instance of this class is specified
     * to be the only one possible.
     */
    private static final Store INSTANCE = new Store(getData());

    /**
     * The number of containers that are in the store now.
     */
    private transient int occupiedVolume;

    /**
     * Constructor for this class.
     * @param data is an array which contains the necessary data for
     * creating the {@link Store} instance.
     */
    private Store(final int... data) {

        NUMBER_OF_DOCKS = data[0];
        NOMINAL_VOLUME = data[1];
        occupiedVolume = data[2];
    }

    /**
     * @return the single instance of the {@link Store} class.
     */
    public static Store getInstance() {
        return INSTANCE;
    }

    /**
     * Getter for NOMINAL_VOLUME field.
     * @return nominal volume of the store (in containers).
     */
    /* default */ int getNominalVolume() {
        return NOMINAL_VOLUME;
    }

    /**
     * Getter for occupiedVolume field.
     * @return the occupied volume of the store (in containers).
     */
    /* default */ int getOccupiedVolume() {
        return occupiedVolume;
    }

    /**
     * Getter for NUMBER_OF_DOCKS field.
     * @return the number of docks in the port.
     */
    public int getNumberOfDocks() {
        return NUMBER_OF_DOCKS;
    }

    /**
     * Method refer to {@link PortDataFileReader} and {@link PortDataParser}
     * classes' methods for getting necessary data for the store.
     * @return array of read values.
     */
    private static int[] getData() {

        final int NUMBER_OF_VALUES = 3;

        final PortDataFileReader READER
                = new PortDataFileReader("/PortData.txt");

        String readData;
        int[] parsedData = new int[NUMBER_OF_VALUES];

        try {
            readData = READER.read();

            try {
                parsedData = PortDataParser.parse(readData);
            } catch (AppException e) {
                LOGGER.log(Level.FATAL, e.getMessage());
            }

        } catch (AppException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
        }

        return parsedData;
    }

    /**
     * Method loads one container to the store.
     */
    /* default */ void load() {
        occupiedVolume += 1;
    }

    /**
     * Method unloads one container from the store.
     */
    /* default */ void unload() {
        occupiedVolume -= 1;
    }
}
