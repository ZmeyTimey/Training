package by.epam.port.parser;

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
     * @return the result of parsing as array of numbers.
     */
    public static int[] parse(final String line) {

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

        LOGGER.log(Level.DEBUG,
                "Parsing completed. The values have been obtained");

        return valuesInt;
    }
}
