package by.epam.figures.reader;

import by.epam.figures.exception.FileReadingException;
import by.epam.figures.exception.LineReadingException;
import by.epam.figures.exception.EmptyPathException;
import by.epam.figures.exception.FileIsAbsentException;
import by.epam.figures.exception.URIConvertException;
import by.epam.figures.exception.InvalidFilePathException;
import by.epam.figures.exception.InvalidLineException;
import by.epam.figures.exception.EmptyLineException;
import by.epam.figures.validator.ReadingValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link Reader} reads lines from file and puts them into ArrayList.
 */
public class Reader {
    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER = LogManager.getLogger(Reader.class);
    /**
     * A number of line which is being read from file.
     */
    private int lineCounter;
    /**
     * A path to file supposed to be read.
     */
    private String filePath;
    /**
     * A list of valid lines read from file.
     */
    private List<String> lines = new ArrayList<>();

    /**
     *
     * @param path is a path to file supposed to be read.
     */
    public Reader(final String path) {
        filePath = path;
    }

    /**
     * @return valid lines read from file.
     */
    public final List<String> getLines() {
        return lines;
    }

    /**
     * Reads lines from file and put them into list of lines.
     * @throws FileReadingException is thrown when it's impossible
     * to read data from file.
     */
    public final void readFile() throws FileReadingException {

        LOGGER.log(Level.DEBUG, "File reading method is started. The path to file: " + filePath);

        lineCounter = 0;
        URI filePathURI;

        try {
            URL resource = Reader.class.getResource(filePath);
            filePathURI = resource.toURI();

            Files.lines(Paths.get(filePathURI),
                    StandardCharsets.UTF_8).forEach(line -> {

                try {
                    addLine(line);

                } catch (LineReadingException ex) {
                    LOGGER.log(Level.ERROR, ex.getMessage());
                }
            });

            LOGGER.log(Level.DEBUG, "File reading is complete");

        } catch (IOException ex) {
            LOGGER.log(Level.FATAL, "File path string is empty!");
            throw new EmptyPathException();
        } catch (NullPointerException ex) {
            LOGGER.log(Level.FATAL, "File is not found!");
            throw new FileIsAbsentException();
        } catch (URISyntaxException ex) {
            LOGGER.log(Level.FATAL, "Unable to convert path link into URI");
            throw new URIConvertException();
        } catch (InvalidPathException ex) {
            LOGGER.log(Level.FATAL, "Invalid path to file!");
            throw new InvalidFilePathException();
    }
    }

    /**
     * Adds valid line read from file into list.
     * @param line is a line which had to be read.
     * @throws LineReadingException is thrown when a line
     * which is read from file is not valid.
     */
    private void addLine(final String line) throws LineReadingException {

        lineCounter++;

        if (!ReadingValidator.lineIsEmpty(line)) {
            if (ReadingValidator.lineIsCorrect(line)) {
                lines.add(line);
                LOGGER.log(Level.DEBUG, "Line "
                        + lineCounter + " is added to the list");
            } else {
                throw new InvalidLineException("Incorrect expression in line "
                        + lineCounter);
            }
        } else {
            throw new EmptyLineException("Line " + lineCounter + " is empty");
        }
    }




}
