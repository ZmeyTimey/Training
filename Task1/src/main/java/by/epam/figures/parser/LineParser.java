package by.epam.figures.parser;

import by.epam.figures.creator.FigureCreator;
import by.epam.figures.entity.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Тимей on 30.06.2018.
 *
 */
public class LineParser {

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

    public void parseData(String line) {

        int i = 0;
        List<Point2D> pointList = new ArrayList<>();

        String[] points = line.split("; ");

        while(i < points.length) {
            double x = Double.parseDouble(points[i].split(", ")[0]);
            double y = Double.parseDouble(points[i].split(", ")[1]);

            pointList.add(FigureCreator.createPoint(x, y));
            i++;
        }

        getPointsFromList(pointList);
    }

    private void getPointsFromList(List<Point2D> pointList) {

        pointA = pointList.get(0);
        pointB = pointList.get(1);
        pointC = pointList.get(2);
    }
}
