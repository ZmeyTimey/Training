package by.epam.greenhouse.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * The enum for Flowers.
 */
public enum FlowersEnum {
    /**
     * Flowers enum.
     */
    FLOWERS("Flowers"),
    /**
     * Flower enum.
     */
    FLOWER("Flower"),
    /**
     * Id of flower enum.
     */
    ID("id"),
    /**
     * Family to which a flower belongs enum.
     */
    FAMILY("family"),
    /**
     * Evegreen flower enum.
     */
    EVERGREEN("evergreen"),
    /**
     * Is a flower evergreen enum.
     */
    NAME("Name"),
    /**
     * Soil for planting a flower enum.
     */
    SOIL("Soil"),
    /**
     * Origin of a flower enum.
     */
    ORIGIN("Origin"),
    /**
     * Delivery date of a flower enum.
     */
    DELIVERY_DATE("DeliveryDate"),
    /**
     * Visual parameters of a flower enum.
     */
    VISUAL_PARAMETERS("VisualParameters"),
    /**
     * Stalk color of a flower enum.
     */
    STALK_COLOR("StalkColor"),
    /**
     * Leaves color enum.
     */
    LEAVES_COLOR("LeavesColor"),
    /**
     * Average size of a flower enum.
     */
    AVERAGE_SIZE("AverageSize"),
    /**
     * Growing types of a flower enum.
     */
    GROWING_TIPS("GrowingTips"),
    /**
     * Flower-friendly temperature enum.
     */
    TEMPERATURE("Temperature"),
    /**
     * Minimal flower-friendly temperature enum.
     */
    MIN("min"),
    /**
     * Maximal flower-friendly temperature enum.
     */
    MAX("max"),
    /**
     * Is a flower photophilous enum.
     */
    PHOTOPHILOUS("Photophilous"),
    /**
     * Watering a flower, milliliters a week enum.
     */
    WATERING("Watering"),
    /**
     * Propagation type of a flower enum.
     */
    PROPAGATION("Propagation");

    /**
     * String value of an enum object.
     */
    private String value;

    /**
     * Getter for the value of an enum object.
     * @return the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * A map of flowers.
     */
    private static final Map<String, FlowersEnum> MAP;

    static {
        MAP = new HashMap<>();
        for (FlowersEnum device : FlowersEnum.values()) {
            MAP.put(device.value, device);
        }
    }

    /**
     * Finding an enum by key.
     * @param key the key to some value.
     * @return the flowers enum.
     */
    public static FlowersEnum findByKey(final String key) {
        return MAP.get(key);
    }

    /**
     * Constructor for this enum.
     * @param val is an input value.
     */
    FlowersEnum(final String val) {
        this.value = val;
    }
}
