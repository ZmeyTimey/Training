package by.epam.figures.entity;


/**
 * Created by Тимей on 29.06.2018.
 *
 */
public class Triangle {

    private Point2D pointA;
    private Point2D pointB;
    private Point2D pointC;

    public Triangle(Point2D p1, Point2D p2, Point2D p3) {
        pointA = p1;
        pointB = p2;
        pointC = p3;
    }

    public Point2D getPointA() {
        return pointA;
    }

    public Point2D getPointB() {
        return pointB;
    }

    public Point2D getPointC() {
        return pointC;
    }

    public void setPoint1(Point2D point1) {
        this.pointA = point1;
    }

    public void setPoint2(Point2D point2) {
        this.pointB = point2;
    }

    public void setPoint3(Point2D point3) {
        this.pointC = point3;
    }

    @Override
    public String toString() {
        return getClass().getName() + ": ("
                + pointA.getX() + ", " + pointA.getY() + ") ("
                + pointB.getX() + ", " + pointB.getY() + ") ("
                + pointC.getX() + ", " + pointC.getY() + ")";
    }

    @Override
    public int hashCode() {
        int result = 12;

        result = 37 * result + (pointA == null ? 0 : pointA.hashCode());
        result = 37 * result + (pointB == null ? 0 : pointB.hashCode());
        result = 37 * result + (pointC == null ? 0 : pointC.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) obj;

        return (pointA == triangle.pointA || (pointA != null && pointA.equals(triangle.getPointA())))
                && (pointB == triangle.pointB || (pointB != null && pointB.equals(triangle.getPointB())))
                && (pointC == triangle.pointC || (pointC != null && pointC.equals(triangle.getPointC())));
    }
}
