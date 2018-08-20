package by.epam.infohandling.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for storing text structural components
 * consisting of other components.
 */

public class Composite implements Component {

    /**
     * The value of component.
     */
    private Object value;

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
     * A list of the parts that make up the text component.
     */
    private List<Component> components = new ArrayList<>();

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
     * Abstract method for replacing component's child.
     * @param number is an input number of child component that should
     *               be replaced.
     * @param newComponent is a component replacing specified child component.
     */
    public void replace(final int number, final Component newComponent) {
        components.set(number, newComponent);
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
