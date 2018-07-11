package by.epam.figures.runner;

import by.epam.figures.action.TriangleAction;
import by.epam.figures.creator.TriangleCreator;
import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.FileReadingException;
import by.epam.figures.exception.OutOfDoubleRangeException;
import by.epam.figures.exception.PointsFormLineException;
import by.epam.figures.parser.LineParser;
import by.epam.figures.reader.Reader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Тимей on 30.06.2018.
 *
 */
public class Runner {
    public static void main(String[] args) {

        Reader reader = new Reader("/Data.txt");
        List<Triangle> trianglesList = new ArrayList<>();
        LineParser parser = new LineParser();
        TriangleAction action = new TriangleAction();

        try {
            reader.readFile();
        } catch (FileReadingException ex) {
            System.out.println(ex.getMessage());
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

                    Triangle triangle = TriangleCreator.createTriangle(
                            parser.getPointA(),
                            parser.getPointB(),
                            parser.getPointC());

                    trianglesList.add(triangle);

                } catch(PointsFormLineException e) {
                    System.out.println(e.getMessage());
                } catch(OutOfDoubleRangeException ex) {
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


            /* TriangleAction checking
            i = 0;

            while (i < trianglesList.size()) {

                System.out.println("Triangle " + (i+1));

                System.out.print("Perimeter: ");
                System.out.println(action.calcPerimeter
                          (trianglesList.get(i)));
                System.out.print("Square: ");
                System.out.println(action.calcSquare
                          (trianglesList.get(i)));
                System.out.print("Right: ");
                System.out.println(action.triangleIsRight
                        (trianglesList.get(i), 4));

                i++;
                System.out.print("\n");
            } */
    }
}
