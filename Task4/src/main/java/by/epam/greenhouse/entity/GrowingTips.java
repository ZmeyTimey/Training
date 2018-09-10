package by.epam.greenhouse.entity;

import java.util.Objects;

/**
 * This class is for storing growing tips of a flower data.
 */
public class GrowingTips {

    /**
     * Flower-friendly temperature.
     */
    private Temperature temperature;
    /**
     * Variable shows whether the flower is photophilous.
     */
    private boolean isPhotophilous;

    /**
     * Watering a flower, milliliters a week.
     */
    private int watering;

    /**
     * Constructor without parameters.
     */
    public GrowingTips() {
    }
    /**
     * Constructor for this class.
     * @param temp is an input value of temperature.
     * @param isPhotophil is an input value of isPhotophilous variable.
     * @param wtrg is an input value of watering.
     */
    public GrowingTips(final Temperature temp,
                       final boolean isPhotophil,
                       final int wtrg) {
        temperature = temp;
        isPhotophilous = isPhotophil;
        watering = wtrg;
    }

    /**
     * Getter for temperature.
     * @return temperature variable value.
     */
    public Temperature getTemperature() {

        if (temperature == null) {
            temperature = new Temperature();
        }
        return temperature;
    }

    /**
     * Setter for temperature.
     * @param value is an input value of temperature variable.
     */
    public void setTemperature(final Temperature value) {
        temperature = value;
    }

    /**
     * Getter for isPhotophilous.
     * @return photophilous variable value.
     */
    public boolean getPhotophilous() {
        return isPhotophilous;
    }

    /**
     * Getter for isPhotophilous.
     * @param value is an input value of isPhotophilous variable.
     */
    public void setPhotophilous(final boolean value) {
        isPhotophilous = value;
    }

    /**
     * Getter for watering.
     * @return watering variable value.
     */
    public int getWatering() {
        return watering;
    }

    /**
     * Setter for watering.
     * @param value is an input value of watering variable.
     */
    public void setWatering(final int value) {
        watering = value;
    }

    /**
     * Overriding method 'equals' for this class.
     *
     * @param obj is an input object for comparing with
     *            an instance of this class.
     * @return result of comparing.
     */
    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GrowingTips growTips = (GrowingTips) obj;
        return Objects.equals(temperature, growTips.temperature)
                && Objects.equals(isPhotophilous, growTips.isPhotophilous)
                && Objects.equals(watering, growTips.watering);
    }

    /**
     * Overriding method 'hashCode' for this class.
     * @return hash code value.
     */
    @Override
    public int hashCode() {

        return Objects.hash(temperature, isPhotophilous, watering);
    }

    /**
     * Overriding method 'toString' for this class.
     * @return string expression of an instance of this class.
     */
    @Override
    public String toString() {
        return "Growing tips: " + temperature + "; "
                + (isPhotophilous ? "photophilous; " : "not photophilous; ")
                + "watering: " + watering + " ml per week";
    }
}
