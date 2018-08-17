package by.epam.infohandling.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class is for reading text from file.
 */
public class Reader {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER = LogManager.getLogger(Reader.class);

    /**
     * A path to file supposed to be read.
     */
    private String filePath;

    /**
     * A full text as a result of reading from file.
     */
    private String readText = "";
    /**
     *
     * @param path is a path to file supposed to be read.
     */
    public Reader(final String path) {
        filePath = path;
    }

    /**
     * Getter for read text converted to String.
     * @return read text String.
     */
    public String getReadText() {
        return readText;
    }

    /**
     * Method reads text from file.
     */
    public final void readFile() {

        LOGGER.log(Level.DEBUG, "File reading method is started. "
                + "The path to file: " + filePath);

        URI filePathURI;
        List<String> lineList = new ArrayList<>();

        try {
            URL filePathURL = Reader.class.getResource(filePath);
            filePathURI = filePathURL.toURI();
            Path path = Paths.get(filePathURI);
            Charset charset = StandardCharsets.UTF_8;

            Stream<String> stream = Files.lines(path, charset);
            stream.forEach(lineList::add);

        } catch (IOException e) {
            if (LOGGER.isFatalEnabled()) {
                LOGGER.fatal("File path value is empty!");
            }

        } catch (URISyntaxException e) {
            if (LOGGER.isFatalEnabled()) {
                LOGGER.fatal("Unable to convert path link into URI");
            }
        }

        int counter = 0;
        while (counter < lineList.size()) {

            StringBuilder builder = new StringBuilder();

            if (counter > 0) {
                builder.append(readText).append("\n");
                readText = builder.toString();
            }
            builder.append(readText).append(lineList.get(counter));
            readText += lineList.get(counter);
            counter++;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("File reading completed");
        }
    }
}
