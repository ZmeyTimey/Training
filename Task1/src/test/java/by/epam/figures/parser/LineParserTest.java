package by.epam.figures.parser;

import by.epam.figures.creator.FigureCreator;
import by.epam.figures.entity.Point2D;
import by.epam.figures.parser.LineParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Тимей on 06.07.2018.
 *
 */
public class LineParserTest {

    private LineParser parser = new LineParser();

    @DataProvider(name = "test1")
    public static Object[][] points() {
        return new Object[][]
                        {{"2.0, 3.0; 4.0, 1.0; -1.0, -2.0",
                                new Point2D(2.0, 3.0), new Point2D(4.0, 1.0), new Point2D(-1.0, -2.0)},
                        {"1.0, 2.0; 6.0, 8.0; 3.5, 7.6",
                                new Point2D(2.0, 3.0), new Point2D(4.0, 1.0), new Point2D(-1.0, -2.0)},
                        {"6.0, 3.0; 6.0, 9.0; -8.0, 3.0",
                                new Point2D(6.0, 3.0), new Point2D(6.0, 9.0), new Point2D(-8.0, 3.0)},
                        {"1.0, 1.0; 3.0, 3.0; 9.0, 9.0",
                                new Point2D(1.0, 1.0), new Point2D(3.0, 3.0), new Point2D(9.0, 9.0)}};
    }

    @Test (dataProvider = "test1")
    public void testParseData(String line, Point2D point1, Point2D point2, Point2D point3) {
        parser.parseData(line);

        Assert.assertEquals(point1, parser.getPointA());
        Assert.assertEquals(point2, parser.getPointB());
        Assert.assertEquals(point3, parser.getPointC());
    }
}