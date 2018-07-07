package by.epam.figures.controller;

import by.epam.figures.action.Calculator;
import by.epam.figures.creator.FigureCreator;
import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.FileIsAbsentException;
import by.epam.figures.exception.PointsFormLineException;
import by.epam.figures.parser.LineParser;
import by.epam.figures.reader.PathSearcher;
import by.epam.figures.reader.Reader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Тимей on 30.06.2018.
 *
 */
public class Controller {
    public static void main(String[] args) {

        Reader reader = new Reader(PathSearcher.getDirectory() + "\\Data.txt");
        List<Triangle> trianglesList = new ArrayList<>();
        LineParser parser = new LineParser();
        Calculator calculator = new Calculator();

        try {
            reader.readFile();
        } catch (FileIsAbsentException e) {
            System.out.println(e.getMessage());
        }

            List<String> lineList = reader.getLines();

            int i = 0;

            //lineList checking
            System.out.print("LineList: ");
            System.out.println(lineList.size());
            while (i < lineList.size()) {
                System.out.println(lineList.get(i));
                i++;
            }
            System.out.print("\n");


            i = 0;

            while (i < lineList.size()) {

                try {
                    parser.parseData(lineList.get(i));

                    Triangle triangle = FigureCreator.createTriangle(
                            parser.getPointA(),
                            parser.getPointB(),
                            parser.getPointC());

                    trianglesList.add(triangle);

                } catch (PointsFormLineException ex) {
                    System.out.println(ex.getMessage());
                }

                i++;
            }


            // trianglesList checking
            i = 0;
            System.out.print("trianglesList: ");
            System.out.println(trianglesList.size());
            while (i < trianglesList.size()) {
                System.out.println(trianglesList.get(i));
                i++;
            }
            System.out.print("\n");


            //Action checking
            i = 0;

            while (i < trianglesList.size()) {

                System.out.println("Triangle " + (i+1));

                System.out.print("Perimeter: ");
                System.out.println(calculator.calculatePerimeter
                        (trianglesList.get(i)));
                System.out.print("Square: ");
                System.out.println(calculator.calculateSquare
                        (trianglesList.get(i)));
                System.out.print("Right: ");
                System.out.println(calculator.triangleIsRight
                        (trianglesList.get(i)));

                i++;
                System.out.print("\n");
            }
    }
}
