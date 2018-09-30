package by.epam.figures.validator;

/**
 * {@link DoubleRangeValidator} checks numbers of double for compliance
 * with the range of this type.
 */
public final class DoubleRangeValidator {

    /**
     * Private constructor.
     */
    private DoubleRangeValidator() {
        throw new AssertionError(
                "Creating an object of this class is not allowed");
    }

    /**
     * @param num is a number which had to be checked.
     * @return is a number is valid.
     */
    public static boolean isValid(final double num) {
        return (num != Double.POSITIVE_INFINITY)
                && (num > 0);
    }
}
