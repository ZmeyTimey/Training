package by.epam.figures.action;

import by.epam.figures.entity.Triangle;
import by.epam.figures.exception.OutOfDoubleRangeException;
import by.epam.figures.validator.DoubleRangeValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An {@link TriangleAction} object contains methods for calculating
 * sides of a triangle, it's perimeter and square.
 * Also methods which determine whether the triangle is regular,
 * is it right, oxygon or obtuse.
 */
public class TriangleAction {
    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(TriangleAction.class);

    /**
     * AB side of a triangle.
     */
    private double sideAB;
    /**
     * AC side of a triangle.
     */
    private double sideAC;
    /**
     * BC side of a triangle.
     */
    private double sideBC;

    /**
     *
     * @param triangle is transmitted to calculate it's sides
     * @throws OutOfDoubleRangeException is thrown when any triangle's side
     * value is out of double type range.
     */
    private void calcSides(final Triangle triangle)
            throws OutOfDoubleRangeException {
        LOGGER.log(Level.DEBUG, "The sides of the triangle calculating");

        double xA = triangle.getPointA().getX();
        double yA = triangle.getPointA().getY();
        double xB = triangle.getPointB().getX();
        double yB = triangle.getPointB().getY();
        double xC = triangle.getPointC().getX();
        double yC = triangle.getPointC().getY();

        try {
            sideAB = Math.sqrt(Math.pow((xB - xA), 2) + Math.pow((yB - yA), 2));
            sideAC = Math.sqrt(Math.pow((xC - xA), 2) + Math.pow((yC - yA), 2));
            sideBC = Math.sqrt(Math.pow((xC - xB), 2) + Math.pow((yC - yB), 2));

            checkSide(sideAB, "AB");
            checkSide(sideBC, "BC");
            checkSide(sideAC, "AC");

            LOGGER.log(Level.DEBUG, "Calculation complete. AB = " + sideAB
                    + ", AC = " + sideAC + ", BC = " + sideBC);

        } catch (OutOfDoubleRangeException ex) {
            throw new OutOfDoubleRangeException(ex.getMessage()
                    + " of the " + triangle
                    + " is out of double type range.");
        }
    }

    /**
     *
     * @param side of triangle.
     * @param sideName marks what a side is this (A, B or C).
     * @throws OutOfDoubleRangeException is thrown when any triangle's side
     * value is out of double type range.
     */
    private void checkSide(final double side,
                           final String sideName)
            throws OutOfDoubleRangeException {

        if (!DoubleRangeValidator.isValid(side)) {
            throw new OutOfDoubleRangeException("The value of side"
                    + " " + sideName);
        }
    }

    /**
     *
     * @param triangle is transmitted to calculate it's perimeter.
     * @return calculated perimeter of triangle.
     * @throws OutOfDoubleRangeException is thrown when one of
     * the triangle's sides value or triangle's perimeter
     * is out of double type range.
     */
    public final double calcPerimeter(final Triangle triangle)
            throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Calculating perimeter of " + triangle);

         try {
             calcSides(triangle);
             double perimeter = sideAB + sideAC + sideBC;

             if (!DoubleRangeValidator.isValid(perimeter)) {
                 throw new OutOfDoubleRangeException("Perimeter of the " + triangle
                         + " is out of double type range");
             }

             LOGGER.log(Level.DEBUG, "Calculating is successfully completed. "
                     + "Perimeter = " + perimeter);

             return perimeter;

         } catch (OutOfDoubleRangeException ex) {

             LOGGER.log(Level.ERROR, ex.getMessage());
             throw new OutOfDoubleRangeException();
         }
    }

    /**
     *
     * @param triangle is transmitted to calculate it's square.
     * @return triangle's square.
     * @throws OutOfDoubleRangeException is thrown when one of
     * the triangle's sides value or triangle's square
     * is out of double type range.
     */
    public final double calcSquare(final Triangle triangle)
            throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Calculating square of " + triangle);

        try {
            calcSides(triangle);

            double semiPerimeter = (sideAB + sideAC + sideBC) / 2;
            double square = Math.sqrt(semiPerimeter * (semiPerimeter - sideAB)
                    * (semiPerimeter - sideAC) * (semiPerimeter - sideBC));

            if (!DoubleRangeValidator.isValid(square)) {
                throw new OutOfDoubleRangeException("Square of the " + triangle
                        + " is out of double type range");
            }
            LOGGER.log(Level.DEBUG, "Calculating is successfully completed. "
                    + "Square = " + square);

            return square;

        } catch (OutOfDoubleRangeException ex) {

            LOGGER.log(Level.ERROR, ex.getMessage());
            throw new OutOfDoubleRangeException();
        }
    }

    /**
     *
     * @param num is a number which transmitted to be rounded.
     * @param roundCoefficient is a coefficient of rounding.
     * @return a rounded value of number.
     */
    private double round(double num, final int roundCoefficient) {

        int precise = (int) Math.pow(10, roundCoefficient);
        num = num * precise;
        int intermediate = (int) Math.round(num);

        return (double) intermediate / precise;
    }

    public boolean triangleIsRight(final Triangle triangle,
                                   final int roundCoefficient)
            throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Is " + triangle + " right checking");

        try {
            calcSides(triangle);

            boolean isRight = (round(Math.pow(sideAB, 2), roundCoefficient)
                    == round(Math.pow(sideAC, 2) + Math.pow(sideBC, 2),
                    roundCoefficient)
                    || (round(Math.pow(sideAC, 2), roundCoefficient)
                    == round(Math.pow(sideAB, 2) + Math.pow(sideBC, 2),
                    roundCoefficient))
                    || (round(Math.pow(sideBC, 2), roundCoefficient)
                    == round(Math.pow(sideAB, 2) + Math.pow(sideAC, 2),
                    roundCoefficient)));

            String message;

            if (isRight) {
                message = "Triangle is right.";
            } else {
                message = "Triangle is not right.";
            }
            LOGGER.log(Level.DEBUG, message);

            return isRight;

        } catch (OutOfDoubleRangeException ex) {

            LOGGER.log(Level.ERROR, ex.getMessage());
            throw new OutOfDoubleRangeException();
        }

    }

    /**
     *
     * @param triangle is transmitted to be checked is it regular.
     * @param roundCoefficient uses in rounding triangle's sides.
     * @return is triangle regular.
     * @throws OutOfDoubleRangeException is thrown when any
     * triangle's side value is out of double type range.
     */
    public final boolean triangleIsRegular(final Triangle triangle,
                                     final int roundCoefficient)
            throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Is " + triangle + " regular checking");

        try {
            calcSides(triangle);

            boolean isRegular = (round(sideAB, roundCoefficient)
                    == round(sideBC, roundCoefficient))
                    && (round(sideBC, roundCoefficient)
                    == round(sideAC, roundCoefficient));
            String message;

            if (isRegular) {
                message = "Triangle is regular";
            } else {
                message = "Triangle is not regular";
            }

            LOGGER.log(Level.DEBUG, message);

            return isRegular;

        } catch (OutOfDoubleRangeException ex) {

            LOGGER.log(Level.ERROR, ex.getMessage());
            throw new OutOfDoubleRangeException();
        }

    }

    /**
     *
     * @param triangle is transmitted to be checked is it a oxygon
     * @param roundCoefficient uses in rounding triangle's sides.
     * @return is triangle an oxygon.
     * @throws OutOfDoubleRangeException is thrown when any
     * triangle's side value is out of double type range.
     */
    public final boolean triangleIsOxygon(final Triangle triangle, final int roundCoefficient) throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Is " + triangle + " oxygon checking");

        try {
            calcSides(triangle);

            boolean isOxygon = (round(Math.pow(sideAB, 2), roundCoefficient)
                    < round(Math.pow(sideAC, 2) + Math.pow(sideBC, 2),
                    roundCoefficient)
                    && (round(Math.pow(sideAC, 2), roundCoefficient)
                    < round(Math.pow(sideAB, 2) + Math.pow(sideBC, 2),
                    roundCoefficient))
                    && (round(Math.pow(sideBC, 2), roundCoefficient)
                    < round(Math.pow(sideAB, 2) + Math.pow(sideAC, 2),
                    roundCoefficient)));

            String message;

            if (isOxygon) {
                message = "Triangle is an oxygon.";
            } else {
                message = "Triangle is not an oxygon";
            }

            LOGGER.log(Level.DEBUG, message);

            return isOxygon;

        } catch (OutOfDoubleRangeException ex) {

            LOGGER.log(Level.ERROR, ex.getMessage());
            throw new OutOfDoubleRangeException();
        }

    }

    /**
     *
     * @param triangle is transmitted to be checked is it obtuse.
     * @param roundCoefficient uses in rounding triangle's sides.
     * @return is triangle obtuse.
     * @throws OutOfDoubleRangeException is thrown when any
     * triangle's side value is out of double type range.
     */
    public final boolean triangleIsObtuse(final Triangle triangle, final int roundCoefficient) throws OutOfDoubleRangeException {

        LOGGER.log(Level.DEBUG, "Is " + triangle + " obtuse checking");

        try {
            calcSides(triangle);

            boolean isObtuse = (round(Math.pow(sideAB, 2), roundCoefficient)
                        > round(Math.pow(sideAC, 2)
                        + Math.pow(sideBC, 2), roundCoefficient)
                    || (round(Math.pow(sideAC, 2), roundCoefficient)
                        > round(Math.pow(sideAB, 2)
                        + Math.pow(sideBC, 2), roundCoefficient))
                    || (round(Math.pow(sideBC, 2), roundCoefficient)
                        > round(Math.pow(sideAB, 2)
                        + Math.pow(sideAC, 2), roundCoefficient)));

            String message;

            if (isObtuse) {
                message = "Triangle is obtuse.";
            } else {
                message = "Triangle is not obtuse.";
            }
            LOGGER.log(Level.DEBUG, message);

            return isObtuse;

        } catch (OutOfDoubleRangeException ex) {

            LOGGER.log(Level.ERROR, ex.getMessage());
            throw new OutOfDoubleRangeException();
        }
    }
}
