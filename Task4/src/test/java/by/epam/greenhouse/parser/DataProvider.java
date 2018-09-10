package by.epam.greenhouse.parser;

import by.epam.greenhouse.entity.Flower;
import by.epam.greenhouse.entity.GrowingTips;
import by.epam.greenhouse.entity.Temperature;
import by.epam.greenhouse.entity.VisualParameters;
import by.epam.greenhouse.parser.sax.Handler;
import org.testng.annotations.BeforeSuite;

import java.util.*;

/**
 * This class is for providing data which is necessary for testing parser-classes.
 */
public class DataProvider {

    private static Set<Flower> flowers;

    public static Set<Flower> getData(){
        if (flowers == null){
            flowersDataInit();
        }
        return flowers;
    }

    @BeforeSuite
    private static void flowersDataInit() {

        flowers = new HashSet<>();


        Date deliveryDate = Handler.parseDate("2016-05-03T09:00:00Z");

        Set<String> stalkColors = new HashSet<>();
        stalkColors.add("green");
        Set<String> leavesColors = new HashSet<>();
        leavesColors.add("green");
        VisualParameters parameters = new VisualParameters(stalkColors, leavesColors, 60);

        Temperature temperature = new Temperature(6, 20);
        GrowingTips growingTips = new GrowingTips(temperature, true, 15000);

        Set<String> propagation = new HashSet<>();
        propagation.add("cuttings");
        Flower rose = new Flower(1, "Rosaceae", false,
                "rose", "podzolic", "Ancient Greece", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(rose);


        deliveryDate = Handler.parseDate("2016-05-09T11:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        parameters = new VisualParameters(stalkColors, leavesColors, 60);

        temperature = new Temperature(8, 18);
        growingTips = new GrowingTips(temperature, true, 10000);

        propagation = new HashSet<>();
        propagation.add("cuttings");
        Flower tulip = new Flower(2, "Liliaceae", false,
                "tulip", "kidney", "Near East", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(tulip);


        deliveryDate = Handler.parseDate("2016-05-12T09:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        parameters = new VisualParameters(stalkColors, leavesColors, 50);

        temperature = new Temperature(20, 28);
        growingTips = new GrowingTips(temperature, true, 5000);

        propagation = new HashSet<>();
        propagation.add("cuttings");
        Flower hemp = new Flower(3, "Cannabaceae", false,
                "hemp", "kidney", "China", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(hemp);


        deliveryDate = Handler.parseDate("2016-06-21T09:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("purple");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        leavesColors.add("purple");
        parameters = new VisualParameters(stalkColors, leavesColors, 40);

        temperature = new Temperature(20, 24);
        growingTips = new GrowingTips(temperature, false, 7000);

        propagation = new HashSet<>();
        propagation.add("cuttings");
        Flower gynur = new Flower(4, "Asteraceae", false,
                "gynur", "sod-podzolic",
                "Africa and the tropics of Asia", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(gynur);


        deliveryDate = Handler.parseDate("2016-08-22T20:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        parameters = new VisualParameters(stalkColors, leavesColors, 60);

        temperature = new Temperature(15, 20);
        growingTips = new GrowingTips(temperature, true, 18000);

        propagation = new HashSet<>();
        propagation.add("cuttings");
        propagation.add("seeds");
        Flower geranium = new Flower(5, "Geraniaceae", false,
                "geranium", "podzolic",
                "South Africa", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(geranium);


        deliveryDate = Handler.parseDate("2016-09-06T09:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        stalkColors.add("red");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        parameters = new VisualParameters(stalkColors, leavesColors, 50);

        temperature = new Temperature(18, 25);
        growingTips = new GrowingTips(temperature, false, 9000);

        propagation = new HashSet<>();
        propagation.add("cuttings");
        propagation.add("seeds");
        Flower dichorisandra = new Flower(6, "Commelinaceae", false,
                "dichorisandra", "sod-podzolic",
                "Peru, Brazil", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(dichorisandra);
    }
}
