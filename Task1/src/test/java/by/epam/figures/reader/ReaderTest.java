package by.epam.figures.reader;

import by.epam.figures.exception.FileReadingException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link ReaderTest} is a test for {@link Reader} class.
 */
public class ReaderTest {
    /**
     * A list of strings that should be read from file and added into list.
     */
    private List<String> correctList = new ArrayList<>();

    /**
     * Creating list off strings in order to compare it with
     * a list that would be created by {@link Reader}.
     */
    @BeforeMethod
    public final void beforeMethod() {

        correctList.clear();

        correctList.add("tr01: 2.0, 3.0; 4.0, 1.0; -1.0, -2.0");
        correctList.add("tr02: 1.0, 2.0; 6.0, 8.0; 3.5, 7.6");
        correctList.add("tr04: 6.0, 3.0; 6.0, 9.0; -8.0, 3.0");
        correctList.add("tr05: 1.0, 1.0; 3.0, 3.0; 9.0, 9.0");
    }

    /**
     * @return invalid paths to file.
     */
    @DataProvider(name = "test1")
    public static Object[][] paths() {

        return new Object[][] {{"/List.txt"}, {"Data.txt"},
                {"111"}, {"qwertyuiop"}, {"   "}, {""}};
    }

    /**
     *
     * @param path is a path to file.
     * @throws FileReadingException is thrown when file can't be read.
     */
    @Test (dataProvider = "test1", expectedExceptions
            = FileReadingException.class)
    public final void testPath(final String path) throws FileReadingException {

        Reader rdr = new Reader(path);
        rdr.readFile();
    }

    /**
     * @throws FileReadingException is thrown when file can't be read.
     */
    @Test
    public final void testReader() throws FileReadingException {
        Reader reader = new Reader("/Data.txt");
        reader.readFile();

        Assert.assertEquals(correctList, reader.getLines());
    }
}
