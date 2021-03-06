package by.epam.port.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is for converting information about ships
 * from read lines into string and numeral values.
 */
public class ShipDataParser {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ShipDataParser.class);
    /**
     * A ship's name parsed from line.
     */
    private transient String shipName;
    /**
     * A Ship's nominal volume parsed from line.
     */
    private transient int nominalVolume;
    /**
     * A Ship's volume occupied by containers parsed from line.
     */
    private transient int occupiedVolume;
    /**
     * Volume of containers that should be unloaded from a ship
     * value parsed from line.
     */
    private transient int unloadVolume;
    /**
     * Volume of containers that should be loaded on a ship
     * value parsed from line.
     */
    private transient int loadVolume;

    /**
     * Constructor for this class.
     */
    public ShipDataParser() { }

    /**
     * Getter for the shipName variable.
     * @return shipName value.
     */
    public String getShipName() {
        return shipName;
    }

    /**
     * Getter for the nominalVolume variable.
     * @return nominalVolume value.
     */
    public int getNominalVolume() {
        return nominalVolume;
    }

    /**
     * Getter for the occupiedVolume variable.
     * @return occupiedVolume value.
     */
    public int getOccupiedVolume() {
        return occupiedVolume;
    }

    /**
     * Getter for the unloadVolume variable.
     * @return unloadVolume value.
     */
    public int getUnloadVolume() {
        return unloadVolume;
    }

    /**
     * Getter for the loadValue variable.
     * @return loadVolume value.
     */
    public int getLoadVolume() {
        return loadVolume;
    }

    /**
     * Method parses a line read from file.
     * @param line is a line read from file.
     */
    public void parse(final String line) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("String: " + line + " is being parsed");
        }

        shipName = line.split(" ")[0];
        String tempLine = line.split(" ", 2)[1];
        nominalVolume = Integer.parseInt(tempLine.split(" ")[0]);
        tempLine = tempLine.split(" ", 2)[1];
        occupiedVolume = Integer.parseInt(tempLine.split(" ")[0]);
        tempLine = tempLine.split(" ", 2)[1];
        unloadVolume = Integer.parseInt(tempLine.split(" ")[0]);
        loadVolume = Integer.parseInt(tempLine.split(" ")[1]);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Parsing completed. The values of "
                    + "the ship's characteristics' created:\n"
                    + "Name: " + shipName + ";\n"
                    + "Nominal volume: " + nominalVolume + ";\n"
                    + "Occupied volume: " + occupiedVolume + ";\n"
                    + "Unload volume: " + unloadVolume + ";\n"
                    + "Load volume: " + loadVolume + ".");
        }
    }
}
