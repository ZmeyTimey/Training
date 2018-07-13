package by.epam.figures.entity;


/**
 * An {@link Triangle} object contains three
 * {@link Point2D} objects which form a triangle.
 */
public class Triangle {
    /**
     * Coefficient is used in hash code generating.
     */
    private static final int HASH_CODE_COEFFICIENT_1 = 12;
    /**
     * Coefficient is used in hash code generating.
     */
    private static final int HASH_CODE_COEFFICIENT_2 = 37;
    /**
     * Point A of a triangle.
     */
    private Point2D pointA;
    /**
     * Point B of a triangle.
     */
    private Point2D pointB;
    /**
     * Point C of a triangle.
     */
    private Point2D pointC;

    /**
     * @param p1 is transmitted first point for creating a triangle.
     * @param p2 is transmitted second point for creating a triangle.
     * @param p3 is transmitted third point for creating a triangle.
     */
    public Triangle(final Point2D p1, final Point2D p2, final Point2D p3) {
        pointA = p1;
        pointB = p2;
        pointC = p3;
    }

    /**
     * @return point A of a triangle.
     */
    public final Point2D getPointA() {
        return pointA;
    }
    /**
     * @return point B of a triangle.
     */
    public final Point2D getPointB() {
        return pointB;
    }
    /**
     * @return point C of a triangle.
     */
    public final Point2D getPointC() {
        return pointC;
    }

    /**
     * @param point1 is point A of a triangle.
     */
    public final void setPointA(final Point2D point1) {
        this.pointA = point1;
    }
    /**
     * @param point2 is point B of a triangle.
     */
    public final void setPointB(final Point2D point2) {
        this.pointB = point2;
    }
    /**
     * @param point3 is point C of a triangle.
     */
    public final void setPointC(final Point2D point3) {
        this.pointC = point3;
    }

    @Override
    public final String toString() {
        return "Triangle: ("
                + pointA.getX() + ", " + pointA.getY() + ") ("
                + pointB.getX() + ", " + pointB.getY() + ") ("
                + pointC.getX() + ", " + pointC.getY() + ")";
    }

    @Override
    public final int hashCode() {
        int result = HASH_CODE_COEFFICIENT_1;

        result = HASH_CODE_COEFFICIENT_2 * result
                + (pointA == null ? 0 : pointA.hashCode());
        result = HASH_CODE_COEFFICIENT_2 * result
                + (pointB == null ? 0 : pointB.hashCode());
        result = HASH_CODE_COEFFICIENT_2 * result
                + (pointC == null ? 0 : pointC.hashCode());

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

        Triangle triangle = (Triangle) obj;

        return (pointA == triangle.pointA
                || (pointA != null && pointA.equals(triangle.getPointA())))
                && (pointB == triangle.pointB
                || (pointB != null && pointB.equals(triangle.getPointB())))
                && (pointC == triangle.pointC
                || (pointC != null && pointC.equals(triangle.getPointC())));
    }
}
