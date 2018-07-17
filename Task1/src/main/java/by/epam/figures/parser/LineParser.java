package by.epam.figures.parser;

import by.epam.figures.creator.PointCreator;
import by.epam.figures.entity.Point2D;
import by.epam.figures.exception.OutOfDoubleRangeException;
import by.epam.figures.validator.DoubleRangeValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link LineParser} converts information from read line into
 * three {@link Point2D} objects.
 */
public class LineParser {
    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER = LogManager.getLogger(LineParser.class);

    /**
     * Point A of an expected triangle.
     */
    private Point2D pointA;
    /**
     * Point B of an expected triangle.
     */
    private Point2D pointB;
    /**
     * Point C of an expected triangle.
     */
    private Point2D pointC;
    /**
     * Triangle's name.
     */
    private String name;
    /**
     * @return point A object.
     */
    public final Point2D getPointA() {
        return pointA;
    }

    /**
     * @return point B object.
     */
    public final Point2D getPointB() {
        return pointB;
    }

    /**
     * @return point C object.
     */
    public final Point2D getPointC() {
        return pointC;
    }

    /**
     * @return triangle's name.
     */
    public final String getName() {
        return name;
    }
    /**
     * @param line read from file and had to be parsed
     * @throws OutOfDoubleRangeException is thrown when a value of point
     * coordinate is out of double type range.
     */
    public final void parseData(final String line)
            throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Line: " + line + " is being parsed");

        int i = 0;
        List<Point2D> pointList = new ArrayList<>();

        name = line.split(": ")[0];

        LOGGER.log(Level.DEBUG, "Triangle's name: " + name);

        String coordinatesData = line.split(": ")[1];
        String[] points = coordinatesData.split("; ");

        try {

            while (i < points.length) {

                String xString = points[i].split(", ")[0];
                String yString = points[i].split(", ")[1];

                double x = Double.parseDouble(xString);
                double y = Double.parseDouble(yString);

                checkCoordinate(x, "X", xString);
                checkCoordinate(y, "Y", yString);

                pointList.add(PointCreator.createPoint(x, y));

                i++;
            }

            getPointsFromList(pointList);

            LOGGER.log(Level.DEBUG, "Line: " + line
                    + " is successfully parsed. The points are created.");

        } catch (OutOfDoubleRangeException ex) {
            LOGGER.log(Level.ERROR, ex.getMessage()
                    + " Point can't be created");
            throw new OutOfDoubleRangeException();
                }
    }

    /**
     * @param pointList is a list of points created on the basis of parsed data.
     */
    private void getPointsFromList(final List<Point2D> pointList) {

        pointA = pointList.get(0);
        pointB = pointList.get(1);
        pointC = pointList.get(2);
    }

    /**
     *
     * @param coordinate is a coordinate had to be checked.
     * @param axis means is it X-coordinate or Y-coordinate.
     * @param value is a value of coordinate stored in string.
     * @throws OutOfDoubleRangeException is thrown when a
     * coordinate value is out of double type range.
     */
    private void checkCoordinate(final double coordinate, final String axis,
                                 final String value)
            throws OutOfDoubleRangeException {

        if (!DoubleRangeValidator.isValid(coordinate)) {
            throw new OutOfDoubleRangeException("Point coordinate "
                    + axis + ": "
                    + value + " is out of double type range.");
        }
    }
}
