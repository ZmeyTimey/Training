package by.epam.figures.validator;

import by.epam.figures.exception.OutOfDoubleRangeException;

import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * {@link DoubleRangeValidator} checks numbers of double for compliance with the range of this type.
 */
public class DoubleRangeValidator {

    public static void checkDouble(double num) throws OutOfDoubleRangeException {
        if (! isValid(num)) {
            throw new OutOfDoubleRangeException();
        }
    }

    private static boolean isValid(double num) {
        return (num != Infinity);
    }
}
