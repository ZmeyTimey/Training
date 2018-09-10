package by.epam.greenhouse.parser.stax;

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
 * This class is for testing {@link StAXBuilder} class.
 */
public class StAXBuilderTest {
    private InputStream stream;

    @BeforeClass
    public void init() {

        String path = "src/main/resources/flowers.xml";
        try {
            stream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void buildSetTest() {

        Builder parser = new StAXBuilder();
        parser.buildSet(stream);
        Set<Flower> flowers = parser.getEntities();

        Assert.assertEquals(flowers, DataProvider.getData());
    }
}
