package by.epam.figures.entity;

/**
 * An {@link Point2D} object contains the coordinates x and y related to a single point.
 */
public class Point2D {

    private double coordinateX;
    private double coordinateY;

    public Point2D(double x, double y) {
        coordinateX = x;
        coordinateY = y;
    }

    public double getX() {
        return coordinateX;
    }

    public double getY() {
        return coordinateY;
    }

    public void setX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    @Override
    public String toString() {
        return "Point: x = " + coordinateX + " y = " + coordinateY;
    }

    @Override
    public int hashCode() {
        int result = 11;

        result = 37 * result + (int) coordinateX;
        result = 37 * result + (int) coordinateY;

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

        Point2D point = (Point2D) obj;

        return (coordinateX == point.coordinateX) && (coordinateY == point.coordinateY);
    }
}
