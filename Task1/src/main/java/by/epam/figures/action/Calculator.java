package by.epam.figures.action;

import by.epam.figures.entity.Triangle;

/**
 * Created by Тимей on 03.07.2018.
 *
 */
public class Calculator {

    private final static int ROUND_COEFFICIENT = 4;

    private double AB;
    private double AC;
    private double BC;

    private void calculateSides(Triangle triangle) {

        double xA = triangle.getPointA().getX();
        double yA = triangle.getPointA().getY();
        double xB = triangle.getPointB().getX();
        double yB = triangle.getPointB().getY();
        double xC = triangle.getPointC().getX();
        double yC = triangle.getPointC().getY();

        AB = Math.sqrt(Math.pow((xB - xA), 2) + Math.pow((yB - yA), 2));
        AC = Math.sqrt(Math.pow((xC - xA), 2) + Math.pow((yC - yA), 2));
        BC = Math.sqrt(Math.pow((xC - xB), 2) + Math.pow((yC - yB), 2));
    }

    public double calculatePerimeter(Triangle triangle) {

        calculateSides(triangle);

        return round(AB + AC + BC);
    }

    public double calculateSquare(Triangle triangle) {

        calculateSides(triangle);

        double semiPerimeter = (AB + AC + BC) / 2;

        return round(Math.sqrt(semiPerimeter * (semiPerimeter - AB) * (semiPerimeter - AC) * (semiPerimeter - BC)));
    }

    private double round(double num) {

        int precise = (int) Math.pow(10, ROUND_COEFFICIENT);
        num = num * precise;
        int intermediate = (int) Math.round(num);

        return (double) intermediate / precise;
    }

    public boolean triangleIsRight(Triangle triangle) {

        calculateSides(triangle);

        return (round(Math.pow(AB, 2)) == round(Math.pow(AC, 2) + Math.pow(BC, 2)))
                || (round(Math. pow(AC, 2)) == round(Math.pow(AB, 2) + Math.pow(BC, 2)))
                || (round(Math.pow(BC, 2)) == round(Math.pow(AB, 2) + Math.pow(AC, 2)));
    }
}