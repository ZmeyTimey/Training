package by.epam.greenhouse.parser.sax;

import by.epam.greenhouse.entity.Flower;
import by.epam.greenhouse.entity.GrowingTips;
import by.epam.greenhouse.entity.Temperature;
import by.epam.greenhouse.entity.VisualParameters;
import by.epam.greenhouse.parser.FlowersEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.datetime.FastDateFormat;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * The handler for {@link Flower} data.
 */
public class Handler extends DefaultHandler {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SAXBuilder.class);

    /**
     * A set of {@link Flower} objects that should be created.
     */
    private Set<Flower> flowers;

    /**
     * Current {@link Flower} which data is being parsed.
     */

    private Flower current;

    /**
     * Current enum that is being checked.
     */
    private FlowersEnum currentEnum;
    /**
     * Enums with text.
     */
    private EnumSet<FlowersEnum> withText;

    /**
     * {@link VisualParameters} instance for flower.
     */
    private VisualParameters parameters;

    /**
     * {@link GrowingTips} instance for flower.
     */
    private GrowingTips growingTips;

    /**
     * {@link Temperature} instance for growingTips.
     */
    private Temperature temperature;

    /**
     * Constructor for this class.
     */
    Handler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(FlowersEnum.NAME, FlowersEnum.PROPAGATION);

        parameters = new VisualParameters();
        growingTips = new GrowingTips();
        temperature = new Temperature();
    }

    /**
     * {@inheritDoc}
     */
    public void startElement(final String uri, final String localName, final
    String qName, final Attributes attrs) {

        if (FlowersEnum.FLOWER.getValue().equals(localName)) {
            current = new Flower();
            for (int counter = 0; counter < attrs.getLength(); ++counter) {
                FlowersEnum temp
                        = FlowersEnum.findByKey(attrs.getQName(counter));

                switch (temp) {

                    case ID:
                        String stringValue = attrs.getValue(counter);
                        int intValue = Integer.parseInt(stringValue);
                        current.setId(intValue);
                        break;

                    case FAMILY:
                        String value = attrs.getValue(counter);
                        current.setFamily(value);
                        break;

                    case EVERGREEN:
                        stringValue = attrs.getValue(counter);
                        boolean booleanValue
                                = Boolean.parseBoolean(stringValue);
                        current.setEvergreen(booleanValue);
                        break;

                }
            }

        } else {
            FlowersEnum temp = FlowersEnum.findByKey(localName);

            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void endElement(final String uri, final String localName, final
    String qName) {
        boolean localNameIsTariff = FlowersEnum.FLOWER.getValue().equals(
                localName);
        if (localNameIsTariff) {
            flowers.add(current);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void characters(final char[] ch, final int start, final int length) {

        String value = new String(ch, start, length).trim();

        if (currentEnum != null) {
            switch (currentEnum) {

                case NAME:
                    current.setName(value);
                    break;

                case SOIL:
                    current.setSoil(value);
                    break;

                case ORIGIN:
                    current.setOrigin(value);
                    break;

                case DELIVERY_DATE:
                    Date date = parseDate(value);
                    current.setDeliveryDate(date);
                    break;

                case VISUAL_PARAMETERS:
                    parameters = current.getVisualParameters();
                    break;

                case STALK_COLOR:
                    Set<String> stalkColor
                            = parameters.getStalkColor();
                    stalkColor.add(value);
                    break;

                case LEAVES_COLOR:
                    Set<String> leavesColor
                            = parameters.getLeavesColor();
                    leavesColor.add(value);
                    break;

                case AVERAGE_SIZE:
                    int size = Integer.parseInt(value);
                    parameters.setAverageSize(size);
                    break;

                case GROWING_TIPS:
                    growingTips = current.getGrowingTips();
                    break;

                case TEMPERATURE:
                    temperature = growingTips.getTemperature();
                    break;

                case MIN:
                    int min = Integer.parseInt(value);
                    temperature.setMin(min);
                    break;

                case MAX:
                    int max = Integer.parseInt(value);
                    temperature.setMax(max);
                    break;

                case PHOTOPHILOUS:
                    boolean isPhotophilous = Boolean.parseBoolean(value);
                    growingTips.setPhotophilous(isPhotophilous);
                    break;

                case WATERING:
                    int watering = Integer.parseInt(value);
                    growingTips.setWatering(watering);
                    break;

                case PROPAGATION:
                    Set<String> propagation = current.getPropagation();
                    propagation.add(value);
                    break;

                default:
                    throw new EnumConstantNotPresentException(currentEnum
                            .getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }

    /**
     * Getter for flowers.
     *
     * @return set of the {@link Flower} instances.
     */
    public Set<Flower> getFlowers() {
        return flowers;
    }

    /**
     * Setter for devices.
     *
     * @param fl is an input value of the flowers set.
     */
    public void setFlowers(final Set<Flower> fl) {
        flowers = fl;
    }

    /**
     * Method converts the date from the format of xml
     * to the format supported by java.
     * @param stringDate is a date in string format.
     * @return {@link Date} instance.
     */
    public static Date parseDate(final String stringDate) {

        Date date = new Date();
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(
                "yyyy-MM-dd'T'HH:mm:ssX");

        try {
            date = fastDateFormat.parse(stringDate);
        } catch (ParseException e) {
            if (LOGGER.isFatalEnabled()) {
                LOGGER.fatal("Date time parsing exception: " + e);
            }
        }

        return date;
    }
}

