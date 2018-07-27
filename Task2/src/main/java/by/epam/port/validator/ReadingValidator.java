package by.epam.port.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains methods which checks is a read line contains
 * necessary data and is this data valid.
 */
public final class ReadingValidator {
    /**
     * Private constructor for this class.
     */
    private ReadingValidator() { }
    /**
     * Checks is a string which contains information about
     * port and it's store is valid.
     * @param line is a read string.
     * @return is line valid.
     */
    public static boolean portDataStringIsValid(final String line) {

        final Pattern PATTERN = Pattern.compile("(\\d+\\s+){2}\\d+\\s*");
        final Matcher MATHCHER = PATTERN.matcher(line);

        return MATHCHER.matches();
    }

    /**
     * Checks is a line read from file which contains
     * information about ships is valid.
     * @param line is a read line.
     * @return is line valid.
     */
    public static boolean shipDataLineIsValid(final String line) {

        final Pattern PATTERN = Pattern.compile(
                "[a-zA-Z0-9]+(\\s\\d+){4}");
        final Matcher MATHCER = PATTERN.matcher(line);

        return MATHCER.matches();
        }

    /**
     * @param line is a line read from file.
     * @return the conditions indicating that line is not empty.
     */
    public static boolean lineIsNotEmpty(final String line) {
        final Pattern PATTERN = Pattern.compile("\\s*");
        final Matcher MATHCER = PATTERN.matcher(line);

        return !MATHCER.matches();
    }

}
