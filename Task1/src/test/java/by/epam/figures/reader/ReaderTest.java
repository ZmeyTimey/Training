package by.epam.figures.reader;

import by.epam.figures.exception.FileReadingException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link ReaderTest} is a test for class {@link Reader}.
 */
public class ReaderTest {

    private static final Logger LOGGER = LogManager.getLogger(ReaderTest.class);

    @DataProvider(name = "test1")
    public static Object[][] correctLines() {
        String[] correctData = {"2.0, 3.0; 4.0, 1.0; -1.0, -2.0", "1.0, 2.0; 6.0, 8.0; 3.5, 7.6",
                "6.0, 3.0; 6.0, 9.0; -8.0, 3.0", "1.0, 1.0; 3.0, 3.0; 9.0, 9.0"};

        List<String> correctList = new ArrayList<>();
        int i = 0;

        while (i < correctData.length) {
            correctList.add(correctData[i]);
            i++;
        }
        return new Object[][]
                {{correctList, "/Data.txt"},
                {correctList, "/List.txt"},
                {correctList, "Data.txt"},
                {correctList, "111"},
                {correctList, "   "}};
    }

    @Test (dataProvider = "test1")
    public void testReader(List<String> correctList, String path) {
        LOGGER.log(Level.DEBUG, "Reader test is started");

        Reader reader = new Reader(path);

        try {
            reader.readFile();

            Assert.assertEquals(correctList, reader.getLines());

        } catch (FileReadingException ex) {
            LOGGER.log(Level.FATAL, ex.getMessage());
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }
}
