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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Тимей on 29.06.2018.
 *
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

        lineCounter = 0;
        URI filePathURI;

        try {
            URL resource = Reader.class.getResource(filePath);
            filePathURI = resource.toURI();

        } catch(URISyntaxException ex) {
            throw new IncorrectFilePathException("Incorrect path to file!");
        }

        try {

            Files.lines(Paths.get(filePathURI),
                    StandardCharsets.UTF_8).forEach(line -> {
                try {
                    addLine(line);

                } catch (LineReadingException ex) {
                    LOGGER.log(Level.WARN, ex.getMessage());
                }
            });

        } catch (IOException e) {
            throw new FileIsAbsentException("File is not found!");
        }
    }

    private void addLine(String line) throws LineReadingException {

        lineCounter++;

        if (ReadingValidator.lineIsEmpty(line)) {
            throw new EmptyLineException("Line " + lineCounter + " is empty");
        } else {
            if (ReadingValidator.lineIsCorrect(line)) {
                lines.add(line);
            } else {
                throw new IncorrectLineException("Incorrect expression in line " + lineCounter);
            }
        }
    }




}
