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
 * {@link LineParser} converts information from read line into three {@link Point2D} objects.
 */
public class LineParser {

    private static final Logger LOGGER = LogManager.getLogger(LineParser.class);

    private Point2D pointA;
    private Point2D pointB;
    private Point2D pointC;

    public Point2D getPointA() {
        return pointA;
    }

    public Point2D getPointB() {
        return pointB;
    }

    public Point2D getPointC() {
        return pointC;
    }

    public void parseData(String line) throws OutOfDoubleRangeException {

        int i = 0;
        List<Point2D> pointList = new ArrayList<>();

        String[] points = line.split("; ");

        try {

            while (i < points.length) {

                String xString = points[i].split(", ")[0];
                String yString = points[i].split(", ")[1];

                double x = Double.parseDouble(xString);
                double y = Double.parseDouble(yString);

                checkCoordinate(x, "X", xString);
                checkCoordinate(y, "Y", yString);

                try {
                    DoubleRangeValidator.checkDouble(y);
                } catch(OutOfDoubleRangeException ex) {
                    throw new OutOfDoubleRangeException("Point coordinate " + x);
                }

                DoubleRangeValidator.checkDouble(y);
                pointList.add(PointCreator.createPoint(x, y));

                i++;
            }

            getPointsFromList(pointList);

            LOGGER.log(Level.DEBUG, "Line: " + line + " is successfully parsed. The points are created.");

        } catch(OutOfDoubleRangeException ex) {
            throw new OutOfDoubleRangeException(ex.getMessage() + " is out of double type range.");
                }
    }

    private void getPointsFromList(List<Point2D> pointList) {

        pointA = pointList.get(0);
        pointB = pointList.get(1);
        pointC = pointList.get(2);
    }

    private void checkCoordinate(double coordinate, String axis, String value) throws OutOfDoubleRangeException {
        try {
            DoubleRangeValidator.checkDouble(coordinate);
        } catch(OutOfDoubleRangeException ex) {
            throw new OutOfDoubleRangeException("Point coordinate " + axis + ": " + value);
        }
    }
}
