package by.epam.port.parser;

import by.epam.port.exception.InvalidStoreDataException;
import by.epam.port.validator.StoreDataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is for converting information about port and it's store
 * from read line into numerical values.
 */
public final class PortDataParser {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(PortDataParser.class);

    /**
     * Private constructor for this class.
     */
    private PortDataParser() { }

    /**
     * Method parses a line read from file.
     * @param line is a line read from file.
     * @throws InvalidStoreDataException is thrown when the intended
     * for the Store class instance creation data is not valid.
     * @return the result of parsing as array of numbers.
     */
    public static int[] parse(final String line)
            throws InvalidStoreDataException {

        LOGGER.log(Level.DEBUG, "String: " + line + " is being parsed");

        final int NUMBER_OF_VALUES = 3;

        String[] valuesString = line.split("\\s+", NUMBER_OF_VALUES);
        int[] valuesInt = new int[NUMBER_OF_VALUES];

        int i = 0;
        while (i < valuesString.length) {
            valuesInt[i] = Integer.parseInt(
                    valuesString[i].replaceAll("\\s+", ""));
            i++;
        }

        if (StoreDataValidator.isDocksValueIsValid(valuesInt[0])) {
            if (StoreDataValidator.isOccupiedVolumeValid(
                    valuesInt[1], valuesInt[2])) {
                LOGGER.log(Level.DEBUG,
                        "Parsing completed. The values have been obtained");

                return valuesInt;
            } else {
                throw new InvalidStoreDataException("The initial volume occupied "
                        + "by containers is too large!");
            }
        } else {
            throw new InvalidStoreDataException("Invalid number of docks! "
                    + "It can't be equal 0");
        }
    }
}
