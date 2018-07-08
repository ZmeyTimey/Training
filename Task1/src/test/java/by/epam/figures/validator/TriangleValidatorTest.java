package validator;

import by.epam.figures.creator.FigureCreator;
import by.epam.figures.entity.Point2D;
import by.epam.figures.validator.TriangleValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Тимей on 06.07.2018.
 *
 */
public class TriangleValidatorTest {

    Point2D pointA1 = FigureCreator.createPoint(2.0, 3.0);
    Point2D pointB1 = FigureCreator.createPoint(4.0, 1.0);
    Point2D pointC1 = FigureCreator.createPoint(-1.0, -2.0);

    Point2D pointA2 = FigureCreator.createPoint(1.0, 1.0);
    Point2D pointB2 = FigureCreator.createPoint(3.0, 3.0);
    Point2D pointC2 = FigureCreator.createPoint(9.0, 9.0);

    @Test
    public void PointsFormATriangle() {
        Assert.assertEquals(true, TriangleValidator.pointsFormATriangle(pointA1, pointB1, pointC1));
    }

    @Test
    public void PointsFormALine() {
        Assert.assertEquals(false, TriangleValidator.pointsFormATriangle(pointA2, pointB2, pointC2));
    }
}