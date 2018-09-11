package by.epam.greenhouse.parser;

import by.epam.greenhouse.entity.Flower;
import by.epam.greenhouse.entity.GrowingTips;
import by.epam.greenhouse.entity.Temperature;
import by.epam.greenhouse.entity.VisualParameters;
import by.epam.greenhouse.parser.sax.Handler;
import org.testng.annotations.BeforeSuite;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


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


        deliveryDate = Handler.parseDate("2016-09-13T21:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        parameters = new VisualParameters(stalkColors, leavesColors, 55);

        temperature = new Temperature(15, 25);
        growingTips = new GrowingTips(temperature, true, 7000);

        propagation = new HashSet<>();
        propagation.add("seeds");

        Flower gippeastrum = new Flower(7, "Amaryllidaceae", false,
                "gippeastrum", "sod-podzolic",
                "South America", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(gippeastrum);


        deliveryDate = Handler.parseDate("2016-09-26T19:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        stalkColors.add("violet");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        leavesColors.add("white");
        parameters = new VisualParameters(stalkColors, leavesColors, 20);

        temperature = new Temperature(18, 24);
        growingTips = new GrowingTips(temperature, false, 6000);

        propagation = new HashSet<>();
        propagation.add("seeds");
        propagation.add("leaves");

        Flower viola = new Flower(8, "Violaceae", false,
                "viola", "podzolic",
                "Africa", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(viola);


        deliveryDate = Handler.parseDate("2016-12-10T09:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        stalkColors.add("pink");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        leavesColors.add("white");
        leavesColors.add("pink");
        parameters = new VisualParameters(stalkColors, leavesColors, 70);

        temperature = new Temperature(17, 23);
        growingTips = new GrowingTips(temperature, false, 10000);

        propagation = new HashSet<>();
        propagation.add("seeds");
        propagation.add("cuttings");

        Flower aglaonema = new Flower(9, "Araceae", true,
                "aglaonema", "kidney",
                "Malay Archipelago, Eastern India", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(aglaonema);


        deliveryDate = Handler.parseDate("2017-03-17T09:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        parameters = new VisualParameters(stalkColors, leavesColors, 60);

        temperature = new Temperature(17, 25);
        growingTips = new GrowingTips(temperature, true, 14000);

        propagation = new HashSet<>();
        propagation.add("cuttings");

        Flower orchid = new Flower(10, "Orchidaceae", false,
                "orchid", "kidney",
                "India, South-East Asia", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(orchid);


        deliveryDate = Handler.parseDate("2017-04-02T12:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        leavesColors.add("white");
        parameters = new VisualParameters(stalkColors, leavesColors, 30);

        temperature = new Temperature(19, 24);
        growingTips = new GrowingTips(temperature, true, 10000);

        propagation = new HashSet<>();
        propagation.add("cuttings");

        Flower aphelandra = new Flower(11, "Acanthaceae", false,
                "aphelandra", "podzolic",
                "Central and Southern Africa", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(aphelandra);


        deliveryDate = Handler.parseDate("2017-05-09T09:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        leavesColors.add("pink");
        leavesColors.add("red");
        leavesColors.add("violet");
        parameters = new VisualParameters(stalkColors, leavesColors, 30);

        temperature = new Temperature(18, 28);
        growingTips = new GrowingTips(temperature, false, 7000);

        propagation = new HashSet<>();
        propagation.add("cuttings");
        propagation.add("seeds");

        Flower tillandsia = new Flower(12, "Bromeliaceae", true,
                "tillandsia", "sod-podzolic",
                "tropics of America", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(tillandsia);


        deliveryDate = Handler.parseDate("2017-06-19T16:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        parameters = new VisualParameters(stalkColors, leavesColors, 55);

        temperature = new Temperature(15, 25);
        growingTips = new GrowingTips(temperature, false, 7000);

        propagation = new HashSet<>();
        propagation.add("seeds");

        Flower amaryllis = new Flower(13, "Amaryllidaceae", false,
                "amaryllis", "sod-podzolic",
                "South Africa", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(amaryllis);


        deliveryDate = Handler.parseDate("2017-08-20T09:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        stalkColors.add("brown");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        leavesColors.add("red");
        parameters = new VisualParameters(stalkColors, leavesColors, 15);

        temperature = new Temperature(18, 22);
        growingTips = new GrowingTips(temperature, true, 9000);

        propagation = new HashSet<>();
        propagation.add("leaves");
        propagation.add("cuttings");

        Flower begonia = new Flower(14, "Begoniaceae", false,
                "begonia", "podzolic",
                "Antilles", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(begonia);


        deliveryDate = Handler.parseDate("2018-03-01T09:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        stalkColors.add("red");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        parameters = new VisualParameters(stalkColors, leavesColors, 35);

        temperature = new Temperature(14, 25);
        growingTips = new GrowingTips(temperature, false, 6000);

        propagation = new HashSet<>();
        propagation.add("leaves");
        propagation.add("cuttings");
        propagation.add("seeds");

        Flower streptocarpus = new Flower(15, "Gesneriaceae", false,
                "streptocarpus", "sod-podzolic",
                "Madagascar", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(streptocarpus);


        deliveryDate = Handler.parseDate("2018-05-07T13:00:00Z");

        stalkColors = new HashSet<>();
        stalkColors.add("green");
        leavesColors = new HashSet<>();
        leavesColors.add("green");
        parameters = new VisualParameters(stalkColors, leavesColors, 50);

        temperature = new Temperature(18, 25);
        growingTips = new GrowingTips(temperature, true, 5000);

        propagation = new HashSet<>();
        propagation.add("cuttings");

        Flower aeschynanthus = new Flower(16, "Gesneriaceae", false,
                "aeschynanthus", "kidney",
                "India", deliveryDate,
                parameters, growingTips, propagation);
        flowers.add(aeschynanthus);
    }
}
