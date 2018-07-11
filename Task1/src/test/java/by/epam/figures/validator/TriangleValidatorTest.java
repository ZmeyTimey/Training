package by.epam.figures.validator;

import by.epam.figures.entity.Point2D;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link TriangleValidatorTest} is a test for class {@link TriangleValidator}.
 */
public class TriangleValidatorTest {

    private static final Logger LOGGER = LogManager.getLogger(TriangleValidatorTest.class);

    @DataProvider(name = "test1")
    public static Object[][] typesOfTriangles() {
        return new Object[][]
                {{new Point2D(2.0, 3.0),
                new Point2D(4.0, 1.0),
                new Point2D(-1.0, -2.0)},

                {new Point2D(1.0, 1.0),
                new Point2D(3.0, 3.0),
                new Point2D(9.0, 9.0)}};
    }

        @Test (dataProvider = "test1")
        public void PointsFormATriangle (Point2D pointA, Point2D pointB, Point2D pointC) {

            LOGGER.log(Level.DEBUG, "Is line correct test is started with incoming data:\n"
                    + pointA + "\n" + pointB + "\n" + pointC);

            try {
                Assert.assertTrue(TriangleValidator.pointsFormATriangle(pointA, pointB, pointC));
                LOGGER.log(Level.DEBUG, "Points form a triangle");
            } catch(AssertionError error) {
                LOGGER.log(Level.DEBUG, "Points form a line");
            }

            LOGGER.log(Level.DEBUG, "The test is complete");
        }
}