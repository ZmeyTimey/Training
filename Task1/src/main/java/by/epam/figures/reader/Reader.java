package by.epam.figures.reader;

import by.epam.figures.exception.EmptyLineException;
import by.epam.figures.exception.FileIsAbsentException;
import by.epam.figures.exception.IncorrectLineException;
import by.epam.figures.exception.LineReadingException;
import by.epam.figures.validator.ReadingValidator;


import java.io.IOException;
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

    private List<String> lines = new ArrayList<>();
    private int lineCounter;
    private String filePath;

    public Reader(String path) {
        filePath = path;
    }

    public List<String> getLines() {
        return lines;
    }


    public void readFile() throws FileIsAbsentException {

        lineCounter = 0;

        try {

            Files.lines(Paths.get(filePath),
                    StandardCharsets.UTF_8).forEach(line -> {
                try {
                    addLine(line);

                } catch (LineReadingException ex) {
                    System.out.println(ex.getMessage());
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
