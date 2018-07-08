package by.epam.figures.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Тимей on 06.07.2018.
 *
 */
public class ReadingValidatorTest {

    @DataProvider(name = "test1")
    public static Object[][] correctLines() {
        return new Object[][]
                {{""},
                {"   "},
                {"asdfv"},
                {"2.0, 3.0; 4.0, 1.0; -1.0,-2.0"},
                {"1.0, 1.0; 3.0, 3.0; 9.0, 9.0"},
                {"5.0, 6.5w; -3.0; 2.5; -9.0, -1.0"}};
    }

    @Test (dataProvider = "test1")
    public void lineIsCorrect(String line) {
        Assert.assertTrue( ReadingValidator.lineIsCorrect(line));
    }

    @Test (dataProvider = "test1")
    public void lineIsNotCorrect(String line) {
        Assert.assertFalse(ReadingValidator.lineIsCorrect(line));
    }

    @Test (dataProvider = "test1")
    public void LineIsEmpty(String line) {
        Assert.assertTrue(ReadingValidator.lineIsEmpty(line));
    }

    @Test (dataProvider = "test1")
    public void LineIsNotEmpty(String line) {
        Assert.assertFalse(ReadingValidator.lineIsEmpty(line));
    }
}