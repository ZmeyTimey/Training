package by.epam.figures.reader;


import by.epam.figures.exception.FileReadingException;
import by.epam.figures.exception.IncorrectFilePathException;
import by.epam.figures.reader.Reader;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Helper {

    public static void main(String args[]) throws FileReadingException {
        String filePath = "/Data.txt";
        URI filePathURI;
        try {
            URL resource = Helper.class.getResource(filePath);
            System.out.println(resource);
            filePathURI = resource.toURI();
            System.out.println(filePathURI);

        } catch(URISyntaxException ex) {
            throw new IncorrectFilePathException("Incorrect path to file!");
        }
    }
}
