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
 * Created by Тимей on 06.07.2018.
 *
 */
public class ReaderTest {

    private static final Logger LOGGER = LogManager.getLogger(ReaderTest.class);

    private Reader reader = new Reader("/Data.txt");

    @DataProvider(name = "test1")
    public static Object[][] paths() {
        return new Object[][]{{"/Data.txt"}, {"Data.txt"}, {"   "}};
    }

    @DataProvider(name = "test2")
    public static Object[][] correctLines() {
        return new Object[][]
                {{"2.0, 3.0; 4.0, 1.0; -1.0, -2.0", "1.0, 2.0; 6.0, 8.0; 3.5, 7.6",
                "6.0, 3.0; 6.0, 9.0; -8.0, 3.0", "1.0, 1.0; 3.0, 3.0; 9.0, 9.0"},
                };
    }

    @Test (dataProvider = "test1")
    public void testPathSearching(String path) {
        Reader rdr = new Reader(path);

        try {
            rdr.readFile();
        } catch (FileReadingException ex) {
            LOGGER.log(Level.FATAL, ex.getMessage());
        }
    }

    @Test (dataProvider = "test2")
    public void testReader(String[] lines) {

        try {
            reader.readFile();
        } catch (FileReadingException ex) {
            LOGGER.log(Level.FATAL, ex.getMessage());
        }

        List<String> correctList = new ArrayList<>();
        int i = 0;

        while (i < lines.length) {
            correctList.add(lines[i]);
            i++;
        }

        Assert.assertEquals(correctList, reader.getLines());
    }

}
