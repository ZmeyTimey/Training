package by.epam.figures.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@link ReadingValidator} has methods which checks a line is empty
 * and is it's valid.
 */

public final class ReadingValidator {

    /**
     * Private constructor.
     */
    private ReadingValidator() {
        throw new AssertionError(
                "Creating an object of this class is not allowed");
    }
    /**
     * @param line is a line read from file.
     * @return the conditions indicating that line is valid.
     */
    public static boolean lineIsCorrect(final String line) {

        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+:"
                + "\\s(-?\\d+\\.\\d+,\\s-?\\d+\\.\\d+;?\\s?){3}");
        Matcher matcher = pattern.matcher(line);

        return matcher.matches();
    }

    /**
     * @param line is a line read from file.
     * @return the conditions indicating that line is empty.
     */
    public static boolean lineIsEmpty(final String line) {
        Pattern pattern = Pattern.compile("\\s*");
        Matcher matcher = pattern.matcher(line);

        return matcher.matches();
    }
}
