package by.epam.greenhouse.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class is for storing visual parameters data of a flower.
 */
public class VisualParameters {

    /**
     * Stalk color(s) of a plant.
     */
    private Set<String> stalkColor;

    /**
     * Leaves color(s) of a plant.
     */
    private Set<String> leavesColor;

    /**
     * Average size of a plant.
     */
    private int averageSize;

    /**
     * Constructor without parameters.
     */
    public VisualParameters() {
    }

    /**
     * Constructor for this class.
     *
     * @param stCol  is an input value of stalk color.
     * @param lvCol  is an input value of leaves color.
     * @param avSize is an input value of average size.
     */
    public VisualParameters(final Set<String> stCol,
                            final Set<String> lvCol,
                            final int avSize) {
        stalkColor = stCol;
        leavesColor = lvCol;
        averageSize = avSize;
    }

    /**
     * Getter for stalk color.
     *
     * @return stalkColor variable value.
     */
    public Set<String> getStalkColor() {

        if (stalkColor == null) {
            stalkColor = new PropertiesSet();
        }
        return stalkColor;
    }

    /**
     * Getter for leaves color.
     *
     * @return leavesColor variable value.
     */
    public Set<String> getLeavesColor() {

        if (leavesColor == null) {
            leavesColor = new PropertiesSet();
        }
        return leavesColor;
    }

    /**
     * Getter for average size.
     *
     * @return averageSize variable value.
     */
    public int getAverageSize() {
        return averageSize;
    }

    /**
     * Setter for average size.
     *
     * @param avSize is an input value of averageSize variable.
     */
    public void setAverageSize(final int avSize) {
        averageSize = avSize;
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

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        VisualParameters parameters = (VisualParameters) obj;
        return stalkColor.equals(parameters.stalkColor)
                && leavesColor.equals(parameters.leavesColor)
                && averageSize == parameters.averageSize;
    }

    /**
     * Overriding method 'hashCode' for this class.
     *
     * @return hash code value.
     */
    @Override
    public int hashCode() {

        return Objects.hash(stalkColor, leavesColor, averageSize);
    }

    /**
     * Overriding method 'toString' for this class.
     *
     * @return string expression of an instance of this class.
     */
    @Override
    public String toString() {
        return "Visual parameters: "
                + "stalk color = " + stalkColor
                + "; leaves color = " + leavesColor
                + "; averageSize = " + averageSize;
    }
}
