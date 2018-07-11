package by.epam.figures.action;

import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.OutOfDoubleRangeException;
import by.epam.figures.exception.PerimeterOutOfDoubleRangeException;
import by.epam.figures.exception.SideOutOfDoubleRangeException;
import by.epam.figures.exception.SquareOutOfDoubleRangeException;
import by.epam.figures.validator.DoubleRangeValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An {@link TriangleAction} object contains methods for calculating sides of a triangle, it's perimeter and square.
 * Also methods which determine whether the triangle is regular, is it right, oxygon or obtuse.
 */
public class TriangleAction {

    private static final Logger LOGGER = LogManager.getLogger(TriangleAction.class);

    private double AB;
    private double AC;
    private double BC;

    private void calcSides(Triangle triangle) throws SideOutOfDoubleRangeException {
        LOGGER.log(Level.DEBUG, "The sides of the triangle calculating");

        double xA = triangle.getPointA().getX();
        double yA = triangle.getPointA().getY();
        double xB = triangle.getPointB().getX();
        double yB = triangle.getPointB().getY();
        double xC = triangle.getPointC().getX();
        double yC = triangle.getPointC().getY();

        try {
            AB = Math.sqrt(Math.pow((xB - xA), 2) + Math.pow((yB - yA), 2));
            AC = Math.sqrt(Math.pow((xC - xA), 2) + Math.pow((yC - yA), 2));
            BC = Math.sqrt(Math.pow((xC - xB), 2) + Math.pow((yC - yB), 2));

            checkSide(AB, "AB");
            checkSide(BC, "BC");
            checkSide(AC, "AC");

            LOGGER.log(Level.DEBUG, "Calculation complete. AB = " + AB + ", AC = " + AC + ", BC = " + BC);

        } catch(OutOfDoubleRangeException ex) {
            throw new SideOutOfDoubleRangeException(ex.getMessage() + " of the " + triangle +
                    " is out of double type range.");
        }
    }

    private void checkSide(double AB, String sideName) throws OutOfDoubleRangeException {
        try {
            DoubleRangeValidator.checkDouble(AB);
        } catch(OutOfDoubleRangeException ex) {
            throw new OutOfDoubleRangeException("The value of side " + sideName);
        }

    }

    public double calcPerimeter(Triangle triangle) throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Perimeter calculating");

         try {
             calcSides(triangle);

             double perimeter = AB + AC + BC;

             try {
                 DoubleRangeValidator.checkDouble(perimeter);
             } catch(OutOfDoubleRangeException ex) {
                 throw new PerimeterOutOfDoubleRangeException("Perimeter of the " + triangle +
                         " is out of double type range");
             }

             LOGGER.log(Level.DEBUG, "Calculating is successfully completed. Perimeter = " + perimeter);

             return perimeter;

         } catch(OutOfDoubleRangeException ex) {
             throw new OutOfDoubleRangeException(ex.getMessage());
         }
    }

    public double calcSquare(Triangle triangle) throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Square calculating");

        try {
            calcSides(triangle);

            double semiPerimeter = (AB + AC + BC) / 2;
            double square = Math.sqrt(semiPerimeter * (semiPerimeter - AB)
                    * (semiPerimeter - AC) * (semiPerimeter - BC));

            try {
                DoubleRangeValidator.checkDouble(square);
            } catch(OutOfDoubleRangeException ex) {
                throw new SquareOutOfDoubleRangeException("Square of the " + triangle +
                        " is out of double type range");
            }
            LOGGER.log(Level.DEBUG, "Calculating is successfully completed. Square = " + square);

            return square;

        } catch(OutOfDoubleRangeException ex) {
            throw new OutOfDoubleRangeException(ex.getMessage());
        }
    }

    private double round(double num, int roundCoefficient) {

        int precise = (int) Math.pow(10, roundCoefficient);
        num = num * precise;
        int intermediate = (int) Math.round(num);

        return (double) intermediate / precise;
    }

    public boolean triangleIsRight(Triangle triangle, int roundCoefficient) throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Is triangle right checking");

        try {
            calcSides(triangle);

            LOGGER.log(Level.DEBUG, "Checking is successfully completed.");

            return (round(Math.pow(AB, 2), roundCoefficient)
                    == round(Math.pow(AC, 2) + Math.pow(BC, 2), roundCoefficient)
                    || (round(Math. pow(AC, 2), roundCoefficient)
                    == round(Math.pow(AB, 2) + Math.pow(BC, 2), roundCoefficient))
                    || (round(Math.pow(BC, 2), roundCoefficient)
                    == round(Math.pow(AB, 2) + Math.pow(AC, 2), roundCoefficient)));

        } catch(OutOfDoubleRangeException ex) {
            throw new OutOfDoubleRangeException(ex.getMessage());
        }

    }

    public boolean triangleIsRegular(Triangle triangle, int roundCoefficient) throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Is triangle regular checking");

        try {
            calcSides(triangle);

            LOGGER.log(Level.DEBUG, "Checking is successfully completed.");

            return (round(AB, roundCoefficient) == round(BC, roundCoefficient))
                    && (round(BC, roundCoefficient) == round(AC, roundCoefficient));

        } catch(OutOfDoubleRangeException ex) {
            throw new OutOfDoubleRangeException(ex.getMessage());
        }

    }

    public boolean triangleIsOxygon(Triangle triangle, int roundCoefficient) throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Is triangle oxygon checking");

        try {
            calcSides(triangle);

            LOGGER.log(Level.DEBUG, "Checking is successfully completed.");

            return (round(Math.pow(AB, 2), roundCoefficient)
                    < round(Math.pow(AC, 2) + Math.pow(BC, 2), roundCoefficient)
                    && (round(Math. pow(AC, 2), roundCoefficient)
                    < round(Math.pow(AB, 2) + Math.pow(BC, 2), roundCoefficient))
                    && (round(Math.pow(BC, 2), roundCoefficient)
                    < round(Math.pow(AB, 2) + Math.pow(AC, 2), roundCoefficient)));

        } catch(OutOfDoubleRangeException ex) {
            throw new OutOfDoubleRangeException(ex.getMessage());
        }

    }

    public boolean triangleIsObtuse(Triangle triangle, int roundCoefficient) throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Is triangle obtuse checking");

        try {
            calcSides(triangle);

            LOGGER.log(Level.DEBUG, "Checking is successfully completed.");

            return (round(Math.pow(AB, 2), roundCoefficient)
                    > round(Math.pow(AC, 2) + Math.pow(BC, 2), roundCoefficient)
                    || (round(Math. pow(AC, 2), roundCoefficient)
                    > round(Math.pow(AB, 2) + Math.pow(BC, 2), roundCoefficient))
                    || (round(Math.pow(BC, 2), roundCoefficient)
                    > round(Math.pow(AB, 2) + Math.pow(AC, 2), roundCoefficient)));

        } catch(OutOfDoubleRangeException ex) {
            throw new OutOfDoubleRangeException(ex.getMessage());
        }
    }
}