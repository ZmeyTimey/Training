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
 * This class is for reading necessary data about the port and it's store.
 */
public class PortDataFileReader {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(PortDataFileReader.class);
    /**
     * A path to the file which contains characteristics of the port data.
     */
    private final transient String PORT_DATA_FILE_PATH;

    /**
     * Constructor for this class.
     * @param portPath is input value of PORT_DATA_FILE_PATH.
     */
    public PortDataFileReader(final String portPath) {
        PORT_DATA_FILE_PATH = portPath;
    }

    /**
     * Method reads data from the file.
     * @return string with read data.
     * @throws AppException includes exceptions which emerge
     * during reading from file.
     */
    public String read() throws AppException {

        final List<String> LINE_LIST = new ArrayList<>();

        try {
            final Class THIS_CLASS = PortDataFileReader.class;
            final URL RESOURCE = THIS_CLASS.getResource(PORT_DATA_FILE_PATH);
            final URI FILE_PATH_URI = RESOURCE.toURI();

            Files.lines(Paths.get(FILE_PATH_URI), StandardCharsets.UTF_8)
                   .forEach(LINE_LIST::add);

        } catch (IOException e) {
            throw new AppException(
                    "Problem with reading data from a file: ", e);
        } catch (NullPointerException e) {
            throw new AppException("File is not found! ", e);
        } catch (URISyntaxException e) {
            throw new AppException("Unable to convert path link into URI. ", e);
        }

        int counter = 0;
        String dataString = "";
        while (counter < LINE_LIST.size()) {

            dataString = (new StringBuilder()
                    .append(dataString)
                    .append(LINE_LIST.get(counter)))
                    .append(" ")
                    .toString();
            counter++;
        }

        if ((ReadingValidator.lineIsNotEmpty(dataString))) {

            if (ReadingValidator.portDataStringIsValid(dataString)) {
                LOGGER.log(Level.DEBUG, "Port data successfully read");
                return dataString;

            } else {
                throw new AppException(
                        "Invalid expression in the read data");
            }

        } else {
            throw new AppException("Read file is empty");
        }
    }
}
