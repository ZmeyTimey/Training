package by.epam.port.reader;

import by.epam.port.exception.AppException;
import by.epam.port.validator.ReadingValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is for reading necessary data about the port.
 */
public class ShipsDataFileReader {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(PortDataFileReader.class);

    /**
     * A number of line which is being read from file.
     */
    private int lineCounter;

    /**
     * A path to the file which contains characteristics of the port data.
     */
    private String shipsDataFilePath;

    /**
     * Constructor for this class.
     * @param shipsPath  is input value of shipsDataFilePath.
     */
    public ShipsDataFileReader(final String shipsPath) {
        shipsDataFilePath = shipsPath;
    }

    /**
     * Method reads data from the file.
     * @return a list of read strings.
     * @throws AppException includes exceptions which emerge
     * during reading from file.
     */
    public List<String> read() throws AppException {
        List<String> lineList = new ArrayList<>();
        try {
            URL resource = PortDataFileReader.class
                    .getResource(shipsDataFilePath);
            URI filePathURI = resource.toURI();

            Files.lines(Paths.get(filePathURI), StandardCharsets.UTF_8)
                    .forEach(line -> {

                        try {
                            addLine(line, lineList);
                            LOGGER.log(Level.DEBUG,
                                    "Ships data successfully read");

                        } catch (AppException e) {
                            LOGGER.log(Level.ERROR, e.getMessage());
                        }
                    });

        } catch (IOException e) {
            throw new AppException(
                    "Problem with reading data from a file: ", e);
        } catch (NullPointerException e) {
            throw new AppException("File is not found! ", e);
        } catch (URISyntaxException e) {
            throw new AppException(
                    "Unable to convert path link into URI. ", e);
        }

        return lineList;
    }

    /**
     * Adds valid read line into list.
     * @param line is a line which had to be read.
     * @param lineList is a list of valid read lines.
     * @throws AppException is thrown when a line
     * which is read from file is not valid.
     */

    private void addLine(final String line,
                         final List<String> lineList)
            throws AppException {

        lineCounter++;

        if (ReadingValidator.lineIsNotEmpty(line)) {

            if (ReadingValidator.shipDataLineIsValid(line)) {
                lineList.add(line);

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Line "
                            + lineCounter + " is added to the list");
                }

            } else {
                throw new AppException("Incorrect expression in line "
                        + lineCounter);
            }
        } else {
            throw new AppException("Line " + lineCounter + " is empty");
        }
    }
}
