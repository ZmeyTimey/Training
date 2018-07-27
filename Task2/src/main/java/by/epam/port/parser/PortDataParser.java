package by.epam.port.parser;

import by.epam.port.exception.AppException;
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
     * @throws AppException is thrown when the intended
     * for the Store class instance creation data is not valid.
     * @return the result of parsing as array of numbers.
     */
    public static int[] parse(final String line)
            throws AppException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("String: " + line + " is being parsed");
        }

        final int NUMBER_OF_VALUES = 3;

        final String[] VALUES_STRING = line.split("\\s+", NUMBER_OF_VALUES);
        int[] valuesInt = new int[NUMBER_OF_VALUES];

        int counter = 0;
        while (counter < VALUES_STRING.length) {
            final String NO_SPACES_STRING = VALUES_STRING[counter]
                    .replaceAll("\\s+", "");
            valuesInt[counter] = Integer.parseInt(NO_SPACES_STRING);
            counter++;
        }

        if (StoreDataValidator.isDocksValueIsValid(valuesInt[0])) {
            if (StoreDataValidator.isOccupiedVolumeValid(
                    valuesInt[1], valuesInt[2])) {
                LOGGER.log(Level.DEBUG,
                        "Parsing completed. The values have been obtained");

                return valuesInt;
            } else {
                throw new AppException("The initial volume occupied "
                        + "by containers is too large!");
            }
        } else {
            throw new AppException("Invalid number of docks! "
                    + "It can't be equal 0");
        }
    }
}
