package by.epam.greenhouse.entity;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * Flower entity-class.
 */
public class Flower {

    /**
     * Id of a flower.
     */
    private int id;
    /**
     * Family to which a flower belongs.
     */
    private String family;
    /**
     * Is a flower is evergreen.
     */
    private boolean evergreen;
    /**
     * Name of a flower.
     */
    private String name;
    /**
     * Soil for planting a flower.
     */
    private String soil;
    /**
     * Origin of a flower.
     */
    private String origin;
    /**
     * Date and time of flower deliver in the greenhouse.
     */
    private Date deliveryDate;
    /**
     * Visual parameters of a flower.
     */
    private VisualParameters visualParameters;
    /**
     * Growing tips of a flower.
     */
    private GrowingTips growingTips;
    /**
     * Propagation type(s) of a flower.
     */
    private Set<String> propagation;

    /**
     * Constructor for this class.
     */
    public Flower() {
    }

    /**
     * Constructor for this class.
     * @param i is an input value of id variable.
     * @param fml is an input value of family variable.
     * @param evgr is an input value of evergreen variable.
     * @param nm is an input value of name variable.
     * @param sl is an input value of soil variable.
     * @param orgn is an input value of origin variable.
     * @param date is an input value of date variable.
     * @param visPar is an input value of visualParameters variable.
     * @param grTips is an input value of growingTips variable.
     * @param pr is an input value of propagation variable.
     */
    public Flower(final int i,
                  final String fml,
                  final boolean evgr,
                  final String nm,
                  final String sl,
                  final String orgn,
                  final Date date,
                  final VisualParameters visPar,
                  final GrowingTips grTips,
                  final Set<String> pr) {
        id = i;
        family = fml;
        evergreen = evgr;
        name = nm;
        soil = sl;
        origin = orgn;
        deliveryDate = date;
        visualParameters = visPar;
        growingTips = grTips;
        propagation = pr;
    }

    /**
     * Getter for id.
     * @return id variable value.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id.
     * @param value is an input value of id variable.
     */
    public void setId(final int value) {
        id = value;
    }
    /**
     * Getter for family.
     * @return family variable value.
     */
    public String getFamily() {
        return family;
    }

    /**
     * Setter for family.
     * @param value is an input value of family variable.
     */
    public void setFamily(final String value) {
        family = value;
    }
    /**
     * Getter for evergreen.
     * @return evegreen variable value.
     */
    public boolean isEvergreen() {
        return evergreen;
    }

    /**
     * Setter for evergreen boolean.
     * @param value is an input value of evergreen variable.
     */
    public void setEvergreen(final boolean value) {
        evergreen = value;
    }

    /**
     * Getter for name.
     * @return name variable value.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name of a flower.
     * @param value is an input value of name variable.
     */
    public void setName(final String value) {
        name = value;
    }

    /**
     * Getter for soil.
     * @return soil variable value.
     */
    public String getSoil() {
        return soil;
    }

    /**
     * Setter for soil.
     * @param value is an input value of soil variable.
     */
    public void setSoil(final String value) {
        soil = value;
    }

    /**
     * Getter for origin.
     * @return origin variable value.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Setter for origin of a flower.
     * @param value is an input value of origin variable.
     */
    public void setOrigin(final String value) {
        origin = value;
    }

    /**
     * Getter for delivery date.
     * @return deliveryDate variable value.
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Setter for delivery date.
     * @param date is an input value of deliveryDate variable.
     */
    public void setDeliveryDate(final Date date) {
        deliveryDate = date;
    }

    /**
     * Getter for visual parameters.
     * @return visualParameters variable value.
     */
    public VisualParameters getVisualParameters() {

        if (visualParameters == null) {
            visualParameters = new VisualParameters();
        }
        return visualParameters;
    }

    /**
     * Setter for visual parameters.
     * @param value is an input value of visualParameters variable.
     */
    public void setVisualParameters(final VisualParameters value) {
        visualParameters = value;
    }

    /**
     * Getter for growing tips.
     * @return growingTips variable value.
     */
    public GrowingTips getGrowingTips() {

        if (growingTips == null) {
            growingTips = new GrowingTips();
        }
        return growingTips;
    }

    /**
     * Setter for growing tips.
     * @param value is an input value of growingTips variable.
     */
    public void setGrowingTips(final GrowingTips value) {
        growingTips = value;
    }

    /**
     * Getter for prepagation.
     * @return prepagation variable value.
     */
    public Set<String> getPropagation() {

        if (propagation == null) {
            propagation = new PropertiesSet();
    }
        return propagation;
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

        Flower flower = (Flower) obj;
        return id == flower.id
                && Objects.equals(family, flower.family)
                && evergreen == flower.evergreen
                && Objects.equals(name, flower.name)
                && Objects.equals(soil, flower.soil)
                && Objects.equals(origin, flower.origin)
                && Objects.equals(deliveryDate, flower.deliveryDate)
                && Objects.equals(visualParameters, flower.visualParameters)
                && Objects.equals(growingTips, flower.growingTips)
                && Objects.equals(propagation, flower.propagation);
    }

    /**
     * Overriding method 'hashCode' for this class.
     * @return hash code value.
     */
    @Override
    public int hashCode() {

        return Objects.hash(id, family, evergreen, name, soil, origin,
                deliveryDate, visualParameters, growingTips, propagation);
    }

    /**
     * Overriding method 'toString' for this class.
     * @return string expression of an instance of this class.
     */
    @Override
    public String toString() {
        return "\n\nFlower: "
                + "id = " + id
                +  "; family = " + family
                + "; evergreen = " + evergreen
                + ";\nname = " + name
                + "; soil = " + soil
                + "; origin = " + origin
                + "; delivery date = " + deliveryDate
                + ";\n" + visualParameters
                + ";\n" + growingTips
                + ";\npropagation = " + propagation;
    }
}

