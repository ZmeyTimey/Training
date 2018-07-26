package by.epam.port.reader;

import by.epam.port.exception.FileIsAbsentException;
import by.epam.port.exception.FileReadingException;
import by.epam.port.exception.InvalidLineException;
import by.epam.port.exception.URIConvertException;
import by.epam.port.exception.EmptyLineException;

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
    private String portDataFilePath;

    /**
     * Constructor for this class.
     * @param portPath is input value of portDataFilePath.
     */
    public PortDataFileReader(final String portPath) {
        portDataFilePath = portPath;
    }

    /**
     * Method reads data from the file.
     * @return string with read data.
     * @throws FileReadingException includes exceptions which emerge
     * during reading from file.
     */
    public String read() throws FileReadingException {

        String dataString = "";
        List<String> lineList = new ArrayList<>();

        try {
            URL resource = PortDataFileReader.class
                    .getResource(portDataFilePath);
            URI filePathURI = resource.toURI();

            Files.lines(Paths.get(filePathURI), StandardCharsets.UTF_8)
                   .forEach(lineList::add);

        } catch (IOException e) {
            throw new FileReadingException(
                    "Problem with reading data from a file: ", e);
        } catch (NullPointerException e) {
            throw new FileIsAbsentException("File is not found!");
        } catch (URISyntaxException e) {
            throw new URIConvertException(
                    "Unable to convert path link into URI");
        }

            int i = 0;
        while (i < lineList.size()) {

            dataString = (new StringBuilder()
                    .append(dataString)
                    .append(lineList.get(i)))
                    .append(" ")
                    .toString();
            i++;
        }

        if ((ReadingValidator.lineIsNotEmpty(dataString))) {

            if (ReadingValidator.portDataStringIsValid(dataString)) {
                LOGGER.log(Level.DEBUG, "Port data successfully read");
                return dataString;

            } else {
                throw new InvalidLineException(
                        "Invalid expression in the read data");
            }

        } else {
            throw new EmptyLineException("Read file is empty");
        }
    }
}