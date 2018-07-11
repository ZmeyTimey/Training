package by.epam.figures.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link ReadingValidatorTest} is a test for class {@link ReadingValidator}.
 */
public class ReadingValidatorTest {

    private static final Logger LOGGER = LogManager.getLogger(ReadingValidatorTest.class);

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

        LOGGER.log(Level.DEBUG, "Is line correct test is started with incoming data:\n" + line);

        try {
            Assert.assertTrue( ReadingValidator.lineIsCorrect(line));
        } catch(AssertionError error) {
            LOGGER.log(Level.DEBUG, "Line: " + line + " is not correct");
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }

    @Test (dataProvider = "test1")
    public void LineIsEmpty(String line) {

        LOGGER.log(Level.DEBUG, "Is line empty test is started with incoming data:\n" + line);

        try {
            Assert.assertTrue(ReadingValidator.lineIsEmpty(line));
        } catch(AssertionError error) {
            LOGGER.log(Level.DEBUG, "Line: " + line + " is not empty");
        }

        LOGGER.log(Level.DEBUG, "The test is complete");
    }
}