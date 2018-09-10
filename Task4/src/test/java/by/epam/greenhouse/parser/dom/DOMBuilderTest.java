package by.epam.greenhouse.parser.dom;

import by.epam.greenhouse.entity.Flower;
import by.epam.greenhouse.parser.Builder;
import by.epam.greenhouse.parser.DataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This class is for testing {@link DOMBuilder} class.
 */
public class DOMBuilderTest {
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
        Builder parser = new DOMBuilder();
        parser.buildSet(stream);

        Set<Flower> flowers = parser.getEntities();
        Set<Flower> correctFlowers =  DataProvider.getData();

        Iterator iC = flowers.iterator();
        Iterator iCF = correctFlowers.iterator();

        Flower flower1 = new Flower();
        Flower correctFlower1 = new Flower();

        while (!(flower1.getId() == 4)) {
            flower1 = (Flower) iC.next();
        }

        while (!(correctFlower1.getId() == 4)) {
            correctFlower1 = (Flower) iCF.next();
        }

        Assert.assertEquals(flower1.getId(), correctFlower1.getId());
        Assert.assertEquals(flower1.getFamily(), correctFlower1.getFamily());
        Assert.assertEquals(flower1.isEvergreen(), correctFlower1.isEvergreen());
        Assert.assertEquals(flower1.getName(), correctFlower1.getName());
        Assert.assertEquals(flower1.getSoil(), correctFlower1.getSoil());
        Assert.assertEquals(flower1.getOrigin(), correctFlower1.getOrigin());
        Assert.assertEquals(flower1.getDeliveryDate(), correctFlower1.getDeliveryDate());

        Set<String> stalkColor = flower1.getVisualParameters().getStalkColor();
        Set<String> correctStalkColor = correctFlower1.getVisualParameters().getStalkColor();
        Set<String> leavesColor = flower1.getVisualParameters().getLeavesColor();
        Set<String> correctLeavesColor = correctFlower1.getVisualParameters().getLeavesColor();
        int size = flower1.getVisualParameters().getAverageSize();
        int correctSize = correctFlower1.getVisualParameters().getAverageSize();

        Assert.assertEquals(stalkColor, correctStalkColor);
        Assert.assertEquals(leavesColor, correctLeavesColor);
        Assert.assertEquals(size, correctSize);

        Assert.assertEquals(flower1.getVisualParameters(), correctFlower1.getVisualParameters());
        Assert.assertEquals(flower1.getGrowingTips(), correctFlower1.getGrowingTips());
        Assert.assertEquals(flower1.getPropagation(), correctFlower1.getPropagation());
        // Assert.assertEquals(flowers, DataProvider.getData());
    }
}
