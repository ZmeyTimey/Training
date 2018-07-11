package by.epam.figures.reader;

import by.epam.figures.exception.*;
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

    private static final Logger LOGGER = LogManager.getLogger(Reader.class);

    private int lineCounter;
    private String filePath;
    private List<String> lines = new ArrayList<>();

    public Reader(String path) {
        filePath = path;
    }

    public List<String> getLines() {
        return lines;
    }

    public void readFile() throws FileReadingException {

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
            LOGGER.log(Level.FATAL, "Error in the work of file reader stream");
        } catch(NullPointerException ex) {
            throw new FileIsAbsentException("File is not found!");
        } catch(URISyntaxException ex) {
            throw new URIConvertException("Invalid path to file!!");
        } catch(InvalidPathException ex) {
            throw new InvalidFilePathException("Invalid path to file!");
    }
    }

    private void addLine(String line) throws LineReadingException {

        lineCounter++;

        if (! ReadingValidator.lineIsEmpty(line)) {
            if (ReadingValidator.lineIsCorrect(line)) {
                lines.add(line);
                LOGGER.log(Level.DEBUG, "Line " + lineCounter + " is added to the list");
            } else {
                throw new InvalidLineException("Incorrect expression in line " + lineCounter);
            }
        } else {
            throw new EmptyLineException("Line " + lineCounter + " is empty");
        }
    }




}
