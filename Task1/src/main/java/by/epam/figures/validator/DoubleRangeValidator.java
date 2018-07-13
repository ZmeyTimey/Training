package by.epam.figures.validator;

import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 * {@link DoubleRangeValidator} checks numbers of double for compliance
 * with the range of this type.
 */
public class DoubleRangeValidator {
    /**
     * @param num is a number which had to be checked.
     * @return is a number is valid.
     */
    public static boolean isValid(final double num) {
        return (num != Infinity) && (num != -Infinity);
    }
}
