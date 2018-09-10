package by.epam.greenhouse.parser.sax;

import by.epam.greenhouse.entity.Flower;
import by.epam.greenhouse.parser.Builder;
import by.epam.greenhouse.parser.DataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Set;

/**
 * This class is for testing {@link SAXBuilder} class.
 */
public class SAXBuilderTest {
    private InputStream stream;

    @BeforeClass
    public void init() {

        String path = "file.xml";
        try {
            stream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void buildSetTest() {
        Builder parser = new SAXBuilder();
        parser.buildSet(stream);

        Set<Flower> flowers = parser.getEntities();

        Assert.assertEquals(flowers, DataProvider.getData());
    }
}
