package by.epam.greenhouse.validator;

import org.testng.annotations.Test;

/**
 * The test for {@link ErrorHandler} class.
 */
public class ValidatorTest {

    @Test
    public void validateTest() {
        final String FILE_PATH = "/file.xml";
        final String SCHEMA_PATH ="/scheme.xsd";

        Validator.validate(FILE_PATH, SCHEMA_PATH);

    }
}
