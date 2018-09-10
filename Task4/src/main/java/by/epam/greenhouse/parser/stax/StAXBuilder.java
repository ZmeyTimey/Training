package by.epam.greenhouse.parser.stax;

import by.epam.greenhouse.entity.Flower;
import by.epam.greenhouse.entity.GrowingTips;
import by.epam.greenhouse.entity.Temperature;
import by.epam.greenhouse.entity.VisualParameters;
import by.epam.greenhouse.parser.Builder;
import by.epam.greenhouse.parser.FlowersEnum;
import by.epam.greenhouse.parser.sax.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamConstants;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Type StAX xml-file parser.
 */
public class StAXBuilder implements Builder {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(StAXBuilder.class);

    /**
     * Set of {@link Flower} instances.
     */
    private Set<Flower> flowers;

    /**
     * XML Input factory.
     */
    private XMLInputFactory inputFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Flower> getEntities() {
        return flowers;
    }

    /**
     * Constructor for this class.
     */
    public StAXBuilder() {
        flowers = new HashSet<>();
        inputFactory = XMLInputFactory.newInstance();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildSet(final InputStream inputStream) {

        XMLStreamReader reader;
        String name;

        try {
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();

                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();

                    if (FlowersEnum.findByKey(name) == FlowersEnum.FLOWER) {
                        Flower flower = buildFlower(reader);
                        flowers.add(flower);
                    }
                }
            }

        } catch (XMLStreamException ex) {
            LOGGER.error("StAX parsing error: " + ex.getMessage());

        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error("Unable to close file: " + e);
            }
        }
    }

    /**
     * Method builds {@link Flower} instance.
     *
     * @param reader is {@link XMLStreamReader} instance.
     * @return built {@link Flower} instance.
     * @throws XMLStreamException is the xml stream exception.
     */
    private Flower buildFlower(final XMLStreamReader reader) throws
            XMLStreamException {

        Flower flower = new Flower();

        String value = FlowersEnum.ID.getValue();
        String idString = reader.getAttributeValue(null, value);
        int id = Integer.parseInt(idString);
        flower.setId(id);

        value = FlowersEnum.FAMILY.getValue();
        String family = reader.getAttributeValue(null, value);
        flower.setFamily(family);

        value = FlowersEnum.EVERGREEN.getValue();
        String evergreenString = reader.getAttributeValue(null, value);
        boolean isEvergreen = Boolean.parseBoolean(evergreenString);
        flower.setEvergreen(isEvergreen);


        String name;
        while (reader.hasNext()) {
            int type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();

                    switch (FlowersEnum.findByKey(name)) {

                        case NAME:
                            value = getXMLText(reader);
                            flower.setName(value);
                            break;

                        case SOIL:
                            value = getXMLText(reader);
                            flower.setSoil(value);
                            break;

                        case ORIGIN:
                            value = getXMLText(reader);
                            flower.setOrigin(value);
                            break;

                        case DELIVERY_DATE:
                            value = getXMLText(reader);
                            Date date = Handler.parseDate(value);
                            flower.setDeliveryDate(date);

                        case VISUAL_PARAMETERS:
                            VisualParameters parameters = getParameters(reader);
                            flower.setVisualParameters(parameters);
                            break;

                        case GROWING_TIPS:
                            GrowingTips growingTips = getGrowingTips(reader);
                            flower.setGrowingTips(growingTips);
                            break;

                        case PROPAGATION:
                            Set<String> propagation = flower.getPropagation();
                            value = getXMLText(reader);
                            propagation.add(value);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    FlowersEnum temp = FlowersEnum.findByKey(name);
                    if (temp == FlowersEnum.FLOWER) {
                        return flower;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Flower");
    }

    /**
     * Method builds {@link VisualParameters} instance.
     * @param reader is {@link XMLStreamReader} instance.
     * @return built {@link VisualParameters} instance.
     * @throws XMLStreamException is the xml stream exception.
     */
    private VisualParameters getParameters(final XMLStreamReader reader)
            throws XMLStreamException {
        VisualParameters parameters = new VisualParameters();

        int type;
        String name;
        String value;

        while (reader.hasNext()) {
            type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();

                    switch (FlowersEnum.findByKey(name)) {

                        case STALK_COLOR:
                            Set<String> stalkColor
                                    = parameters.getStalkColor();
                            value = getXMLText(reader);
                            stalkColor.add(value);
                            break;

                        case LEAVES_COLOR:
                            Set<String> leavesColor
                                    = parameters.getLeavesColor();
                            value = getXMLText(reader);
                            leavesColor.add(value);
                            break;

                        case AVERAGE_SIZE:
                            value = getXMLText(reader);
                            int size = Integer.parseInt(value);
                            parameters.setAverageSize(size);
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    FlowersEnum temp = FlowersEnum.findByKey(name);
                    if (temp == FlowersEnum.VISUAL_PARAMETERS) {
                        return parameters;
                    }
                    break;
            }
        }
        return parameters;
    }

    /**
     * Method builds {@link GrowingTips} instance.
     * @param reader is {@link XMLStreamReader} instance.
     * @return built {@link GrowingTips} instance.
     * @throws XMLStreamException is the xml stream exception.
     */
    private GrowingTips getGrowingTips(final XMLStreamReader reader)
            throws XMLStreamException {
        GrowingTips growingTips = new GrowingTips();

        int type;
        String name;
        String value;

        while (reader.hasNext()) {
            type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();

                    switch (FlowersEnum.findByKey(name)) {

                        case TEMPERATURE:
                            Temperature temperature = getTemperature(reader);
                            growingTips.setTemperature(temperature);
                            break;

                        case PHOTOPHILOUS:
                            value = getXMLText(reader);
                            boolean isPhotophilous
                                    = Boolean.parseBoolean(value);
                            growingTips.setPhotophilous(isPhotophilous);
                            break;

                        case WATERING:
                            value = getXMLText(reader);
                            int watering = Integer.parseInt(value);
                            growingTips.setWatering(watering);
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    FlowersEnum temp = FlowersEnum.findByKey(name);
                    if (temp == FlowersEnum.GROWING_TIPS) {
                        return growingTips;
                    }
                    break;
            }
        }
        return growingTips;
    }
    /**
     * Method builds {@link Temperature} instance.
     * @param reader is {@link XMLStreamReader} instance.
     * @return built {@link Temperature} instance.
     * @throws XMLStreamException is the xml stream exception.
     */
    private Temperature getTemperature(final XMLStreamReader reader)
            throws XMLStreamException {
        Temperature temperature = new Temperature();

        int type;
        String name;
        String value;

        while (reader.hasNext()) {
            type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();

                    switch (FlowersEnum.findByKey(name)) {

                        case MIN:
                            value = getXMLText(reader);
                            int min = Integer.parseInt(value);
                            temperature.setMin(min);
                            break;

                        case MAX:
                            value = getXMLText(reader);
                            int max = Integer.parseInt(value);
                            temperature.setMax(max);
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    FlowersEnum temp = FlowersEnum.findByKey(name);
                    if (temp == FlowersEnum.TEMPERATURE) {
                        return temperature;
                    }
                    break;
            }
        }
        return temperature;
    }

    /**
     * Gets xml text.
     *
     * @param reader the reader.
     * @return the xml text.
     * @throws XMLStreamException the xml stream exception.
     */
    private String getXMLText(final XMLStreamReader reader) throws
            XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
