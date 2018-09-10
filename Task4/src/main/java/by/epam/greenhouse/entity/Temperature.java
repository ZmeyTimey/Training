package by.epam.greenhouse.entity;

import java.util.Objects;

/**
 * This class is for storing temperature data of a flower.
 */
public class Temperature {

    /**
     * A minimal flower-friendly temperature.
     */
    private int min;
    /**
     * A maximal flower-friendly temperature.
     */
    private int max;

    /**
     * Constructor without parameters.
     */
    public Temperature() {
    }
    /**
     * Constructor for this class.
     *
     * @param minTemp is an input value of minimal flower-friendly temperature.
     * @param maxTemp is an input value of maximal flower-friendly temperature.
     */
    public Temperature(final int minTemp, final int maxTemp) {
        min = minTemp;
        max = maxTemp;
    }

    /**
     * Getter for minimal temperature.
     *
     * @return min variable value.
     */
    public int getMin() {
        return min;
    }

    /**
     * Setter for minimal temperature.
     *
     * @param value is an input value of min variable.
     */
    public void setMin(final int value) {
        min = value;
    }

    /**
     * Getter for maximal temperature.
     *
     * @return max variable value.
     */
    public int getMax() {
        return max;
    }

    /**
     * Setter for maximal temperature.
     *
     * @param value is an input value of max variable.
     */
    public void setMax(final int value) {
        max = value;
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

        Temperature temp = (Temperature) obj;
        return Objects.equals(min, temp.min)
                && Objects.equals(max, temp.max);
}

    /**
     * Overriding method 'hashCode' for this class.
     * @return hash code value.
     */
    @Override
    public int hashCode() {

        return Objects.hash(min, max);
    }

    /**
     * Overriding method 'toString' for this class.
     * @return string expression of an instance of this class.
     */
    @Override
    public String toString() {
        return "temperature: " + "min = " + min + "; max = " + max;
    }
}
