package by.epam.figures.entity;

/**
 * {@link Registrator} object stores perimeter and square of some triangle.
 */
public class Registrator {

    /**
     * Coefficient is used in hash code generating.
     */
    private static final int HASH_CODE_COEFFICIENT_1 = 13;
    /**
     * Coefficient is used in hash code generating.
     */
    private static final int HASH_CODE_COEFFICIENT_2 = 37;
    /**
     * Perimeter of a triangle.
     */
    private double perimeter;
    /**
     * Square of a triangle.
     */
    private double square;

    /**
     * @param per is incoming value of triangle's perimeter.
     * @param sqr is incoming value of triangle's square.
     */
    public Registrator(final double per, final double sqr) {
        perimeter = per;
        square = sqr;
    }

    /**
     * @return perimeter of a triangle.
     */
    public final double getPerimeter() {
        return perimeter;
    }

    /**
     * @return square of a triangle.
     */
    public final double getSquare() {
        return square;
    }

    /**
     * @param newPerimeter is a new perimeter of changed triangle.
     */
    public final void setPerimeter(final double newPerimeter) {
        perimeter = newPerimeter;
    }

    /**
     * @param newSquare is a new square of changed triangle.
     */
    public final void setSquare(final double newSquare) {
        square = newSquare;
    }

    @Override
    public final String toString() {
        return "Perimeter = " + perimeter + ", Square = " + square;
    }

    @Override
    public final int hashCode() {
        int result = HASH_CODE_COEFFICIENT_1;

        result = HASH_CODE_COEFFICIENT_2 * result + (int) perimeter;
        result = HASH_CODE_COEFFICIENT_2 * result + (int) square;

        return result;
    }

    @Override
    public final boolean equals(final Object obj) {

        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Registrator registrator = (Registrator) obj;

        return (perimeter == registrator.perimeter)
                && (square == registrator.square);
    }
}

