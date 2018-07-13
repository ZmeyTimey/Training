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

    private List<String> correctList = new ArrayList<>();

    @BeforeMethod
    public void beforeMethod() {

        correctList.clear();

        correctList.add("2.0, 3.0; 4.0, 1.0; -1.0, -2.0");
        correctList.add("1.0, 2.0; 6.0, 8.0; 3.5, 7.6");
        correctList.add("6.0, 3.0; 6.0, 9.0; -8.0, 3.0");
        correctList.add("1.0, 1.0; 3.0, 3.0; 9.0, 9.0");
    }

    @DataProvider(name = "test1")
    public static Object[][] paths() {

        return new Object[][] {{"/List.txt"}, {"Data.txt"}, {"111"}, {"qwertyuiop"}, {"   "}, {""}};
    }

    @Test (dataProvider = "test1", expectedExceptions = FileReadingException.class)
    public void testPath(String path) throws FileReadingException {

        Reader rdr = new Reader(path);
        rdr.readFile();
    }

    @Test
    public void testReader() throws FileReadingException {
        Reader reader = new Reader("/Data.txt");
        reader.readFile();

        Assert.assertEquals(correctList, reader.getLines());
    }
}
