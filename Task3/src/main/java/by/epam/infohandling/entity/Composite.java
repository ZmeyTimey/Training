package by.epam.infohandling.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for storing text structural components
 * consisting of other components.
 */

public class Composite implements Component {

    /**
     * Coefficient is used in hash code generating.
     */
    private static final int HASH_CODE_COEFFICIENT_1 = 12;
    /**
     * Coefficient is used in hash code generating.
     */
    private static final int HASH_CODE_COEFFICIENT_2 = 37;

    /**
     * The value of component.
     */
    private Object value;

    /**
     * A list of the parts that make up the text component.
     */
    private List<Component> components = new ArrayList<>();

    @Override
    public final int hashCode() {
        int result = HASH_CODE_COEFFICIENT_1;

        result = HASH_CODE_COEFFICIENT_2 * result
                + (value == null ? 0 : value.hashCode());
        result = HASH_CODE_COEFFICIENT_2 * result
                + (components == null ? 0 : components.hashCode());

        return result;
    }

    @Override
    public final boolean equals(final Object obj) {

        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Composite composite = (Composite) obj;
        Object compositeValue = composite.getValue();

        List<Component> compositeChildren = new ArrayList<>();
        int numberOfChildren = composite.getNumberOfChildren();

        for (int counter = 0; counter < numberOfChildren; counter++) {
            Component childComponent = (Component) composite.getChild(counter);
            compositeChildren.add(childComponent);
        }

        return (value == composite.value
                || (value != null && value.equals(compositeValue)))
                && (components == composite.components
                || (components != null
                && components.equals(compositeChildren)));
    }

    /**
     * Constructor for this class.
     * @param componentValue is an input component value.
     */
    public Composite(final Object componentValue) {
        value = componentValue;
    }

    /**
     * Getter for the value of component.
     * @return value of component.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Setter for the value of component.
     * @param newValue is what replace an old value of component.
     */
    public void setValue(final Object newValue) {
        value = newValue;
    }

    /**
     * Method adds component to the list of components.
     * @param component is a component that should be added to the list.
     */
    public void add(final Component component) {
        components.add(component);
    }

    /**
     * Method removes given component from the list.
     * @param component is a component that should be removed.
     */
    public void remove(final Component component) {
        components.remove(component);
    }

    /**
     * Method returns child component with a given number.
     * @param number of component.
     * @return child component.
     */
    public Object getChild(final int number) {
        return components.get(number);
    }

    /**
     * Method is for getting the number of child components
     * of the instance of this class.
     * @return the number of child components.
     */
    public int getNumberOfChildren() {
        return components.size();
    }
}
