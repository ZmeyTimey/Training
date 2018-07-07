package by.epam.figures.validator;

import by.epam.figures.exception.LineReadingException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Тимей on 05.07.2018.
 *
 */
public class ReadingValidator {

    public static boolean lineIsCorrect(String line) {

        Pattern pattern = Pattern.compile("(-?\\d+\\.\\d+,\\s-?\\d+\\.\\d+;?\\s?){3}");
        Matcher matcher = pattern.matcher(line);

        return matcher.matches();
    }

    public static boolean lineIsEmpty(String line) {
        Pattern pattern = Pattern.compile("\\s*");
        Matcher matcher = pattern.matcher(line);

        return matcher.matches();
    }
}
