package by.epam.greenhouse.parser.dom;

import by.epam.greenhouse.entity.Flower;
import by.epam.greenhouse.entity.GrowingTips;
import by.epam.greenhouse.entity.Temperature;
import by.epam.greenhouse.entity.VisualParameters;
import by.epam.greenhouse.parser.Builder;
import by.epam.greenhouse.parser.FlowersEnum;
import by.epam.greenhouse.parser.sax.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Type DOM xml-file parser.
 */
public class DOMBuilder implements Builder {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DOMBuilder.class);

    /**
     * Set of {@link Flower} instances.
     */
    private Set<Flower> flowers;

    /**
     * The Doc builder.
     */
    private DocumentBuilder docBuilder;

    /**
     * Constructor for this class.
     */
    public DOMBuilder() {

        flowers = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Flower> getEntities() {
        return flowers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildSet(final InputStream fileInputStream) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Starting DOM parser!");
        }

        try {
            Document doc = docBuilder.parse(fileInputStream);
            Element root = doc.getDocumentElement();
            NodeList flowersList = root.getElementsByTagName(FlowersEnum
                    .FLOWER.getValue());

            for (int i = 0; i < flowersList.getLength(); i++) {
                Element flowerElement = (Element) flowersList.item(i);
                Flower flower = buildFlower(flowerElement);
                flowers.add(flower);
            }

        } catch (IOException e) {
            LOGGER.error("Input/Output exception: " + e);
        } catch (SAXException e) {
            LOGGER.error("Parsing failure: " + e);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Parsing is successfully completed!");
        }
    }

    /**
     * Method builds {@link Flower} instance.
     *
     * @param flowerElement is the flower element
     * @return {@link Flower} instance.
     */
    private Flower buildFlower(final Element flowerElement) {

        Flower flower = new Flower();

        String value = FlowersEnum.ID.getValue();
        String stringId = flowerElement.getAttribute(value);
        int id = Integer.parseInt(stringId);
        flower.setId(id);

        value = FlowersEnum.FAMILY.getValue();
        String family = flowerElement.getAttribute(value);
        flower.setFamily(family);

        value = FlowersEnum.EVERGREEN.getValue();
        String stringEvergreen = flowerElement.getAttribute(value);
        boolean isEvergreen = !stringEvergreen.equals("")
                && Boolean.parseBoolean(stringEvergreen);
        flower.setEvergreen(isEvergreen);

        value = FlowersEnum.NAME.getValue();
        String name = getElementText(flowerElement, value);
        flower.setName(name);

        value = FlowersEnum.SOIL.getValue();
        String soil = getElementText(flowerElement, value);
        flower.setSoil(soil);

        value = FlowersEnum.ORIGIN.getValue();
        String origin = getElementText(flowerElement, value);
        flower.setOrigin(origin);

        value = FlowersEnum.DELIVERY_DATE.getValue();
        String stringDate = getElementText(flowerElement, value);
        Date date = Handler.parseDate(stringDate);
        flower.setDeliveryDate(date);

        VisualParameters parameters = flower.getVisualParameters();
        value = FlowersEnum.VISUAL_PARAMETERS.getValue();
        Element parametersElement
                = (Element) flowerElement.getElementsByTagName(value).item(0);

        value = FlowersEnum.STALK_COLOR.getValue();
        NodeList nodeList = getElementList(parametersElement, value);
        Set<String> stalkColors = parameters.getStalkColor();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nodeColor = nodeList.item(i);
            String color = nodeColor.getTextContent();
            stalkColors.add(color);
        }

        value = FlowersEnum.LEAVES_COLOR.getValue();
        nodeList = getElementList(parametersElement, value);
        Set<String> leavesColors = parameters.getLeavesColor();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node colorNode = nodeList.item(i);
            String color = colorNode.getTextContent();
            leavesColors.add(color);
        }

        value = FlowersEnum.AVERAGE_SIZE.getValue();
        String stringSize = getElementText(parametersElement, value);
        int size = Integer.parseInt(stringSize);
        parameters.setAverageSize(size);

        GrowingTips growingTips = flower.getGrowingTips();
        value = FlowersEnum.GROWING_TIPS.getValue();
        Element growingTipsElement
                = (Element) flowerElement.getElementsByTagName(value).item(0);

        Temperature temperature = growingTips.getTemperature();
        value = FlowersEnum.TEMPERATURE.getValue();
        Element temperatureElement
                = (Element) flowerElement.getElementsByTagName(value).item(0);

        value = FlowersEnum.MIN.getValue();
        String stringMin = getElementText(temperatureElement, value);
        int min = Integer.parseInt(stringMin);
        temperature.setMin(min);

        value = FlowersEnum.MAX.getValue();
        String stringMax = getElementText(temperatureElement, value);
        int max = Integer.parseInt(stringMax);
        temperature.setMax(max);

        value = FlowersEnum.PHOTOPHILOUS.getValue();
        String stringPhotophilous = getElementText(growingTipsElement, value);
        boolean isPhotophilous = Boolean.parseBoolean(stringPhotophilous);
        growingTips.setPhotophilous(isPhotophilous);

        value = FlowersEnum.WATERING.getValue();
        String stringWatering = getElementText(growingTipsElement, value);
        int watering = Integer.parseInt(stringWatering);
        growingTips.setWatering(watering);

        value = FlowersEnum.PROPAGATION.getValue();
        nodeList = getElementList(flowerElement, value);
        Set<String> propagation = flower.getPropagation();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node propagationTypeNode = nodeList.item(i);
            String propagationType = propagationTypeNode.getTextContent();
            propagation.add(propagationType);
        }

        return flower;
    }

    /**
     * Gets element with the specified name.
     *
     * @param element     is the element.
     * @param elementName is the name of element.
     * @return the element text.
     */
    private static String getElementText(final Element element,
                                         final String elementName) {

        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);

        return node.getTextContent();
    }

    /**
     * Gets a list of elements with the specified name.
     *
     * @param element     is the element.
     * @param elementName is the name of element.
     * @return the element text.
     */
    private static NodeList getElementList(final Element element,
                                           final String elementName) {

        return element.getElementsByTagName(elementName);
    }
}
