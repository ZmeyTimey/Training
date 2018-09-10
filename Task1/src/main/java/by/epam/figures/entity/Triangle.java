package by.epam.figures.entity;


import by.epam.figures.observer.Observer;

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
     * Triangle's name.
     */
    private String triangleName;
    /**
     * Triangle's id.
     */
    private int triangleId;

    /**
     * The instance of {@link Observer}.
     */
    private Observer observer = new Observer();

    private Registrator registrator;
    /**
     * @param name is a name of the triangle.
     * @param p1 is transmitted first point for creating a triangle.
     * @param p2 is transmitted second point for creating a triangle.
     * @param p3 is transmitted third point for creating a triangle.
     */
    public Triangle(final String name,
                    final Point2D p1, final Point2D p2, final Point2D p3) {
        triangleName = name;
        pointA = p1;
        pointB = p2;
        pointC = p3;
    }
    /**
     * @param p1 is transmitted first point for creating a triangle.
     * @param p2 is transmitted second point for creating a triangle.
     * @param p3 is transmitted third point for creating a triangle.
     * @param name is transmitted name of triangle.
     * @param id is transmetted id of triangle.
     */
    public Triangle(final String name, final int id,
                    final Point2D p1, final Point2D p2, final Point2D p3) {
        pointA = p1;
        pointB = p2;
        pointC = p3;
        triangleName = name;
        triangleId = id;
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
     * @return triangle's name.
     */
    public final String getName() {
        return triangleName;
    }

    /**
     * @return triangle's id.
     */
    public final int getId() {
        return triangleId;
    }

    /**
     * @return {@link Registrator} object connected with
     * this triangle.
     */
    public final Registrator getRegistrator() {
        return registrator;
    }

    /**
     * @param point1 is point A of a triangle.
     */
    public final void setPointA(final Point2D point1) {
        pointA = point1;
        observer.update(this);
    }
    /**
     * @param point2 is point B of a triangle.
     */
    public final void setPointB(final Point2D point2) {
        pointB = point2;
        observer.update(this);
    }
    /**
     * @param point3 is point C of a triangle.
     */
    public final void setPointC(final Point2D point3) {
        pointC = point3;
        observer.update(this);
    }

    /**
     * @param id is triangle's individual id.
     */
    public final void setId(final int id) {
        triangleId = id;
    }

    /**
     * @param perimeter is the perimeter of the triangle.
     * @param square is the square of the triangle.
     */
    public final void setRegistrator(final double perimeter,
                                     final double square) {
        registrator = new Registrator(perimeter, square);
    }

    @Override
    public final String toString() {
        return "Triangle " + triangleName + ": ("
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
