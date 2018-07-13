package by.epam.figures.entity;

/**
 * An {@link Point2D} object contains the coordinates x and y related
 * to a single point.
 */
public class Point2D {
    /**
     * Coefficient is used in hash code generating.
     */
    private static final int HASH_CODE_COEFFICIENT_1 = 11;
    /**
     * Coefficient is used in hash code generating.
     */
    private static final int HASH_CODE_COEFFICIENT_2 = 37;
    /**
     * X-coordinate of a point.
     */
    private double coordinateX;
    /**
     * Y-coordinate of a point.
     */
    private double coordinateY;

    /**
     * @param x is X-coordinate of a point.
     * @param y is Y-coordinate of a point.
     */
    public Point2D(final double x, final double y) {
        coordinateX = x;
        coordinateY = y;
    }

    /**
     * @return X-coordinate of a point.
     */
    public final double getX() {
        return coordinateX;
    }

    /**
     * @return Y-coordinate of a point.
     */
    public final double getY() {
        return coordinateY;
    }

    /**
     * @param coordinate1 is X-coordinate of a point.
     */
    public final void setX(final double coordinate1) {
        coordinateX = coordinate1;
    }

    /**
     * @param coordinate2 is Y-coordinate of a point.
     */
    public final void setY(final double coordinate2) {
        coordinateY = coordinate2;
    }

    /**
     * Override the method toString().
     */
    @Override
    public final String toString() {
        return "Point: x = " + coordinateX + " y = " + coordinateY;
    }

    /**
     * Override the method hashCode().
     */
    @Override
    public final int hashCode() {
        int result = HASH_CODE_COEFFICIENT_1;

        result = HASH_CODE_COEFFICIENT_2 * result + (int) coordinateX;
        result = HASH_CODE_COEFFICIENT_2 * result + (int) coordinateY;

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

        Point2D point = (Point2D) obj;

        return (coordinateX == point.coordinateX)
                && (coordinateY == point.coordinateY);
    }
}
