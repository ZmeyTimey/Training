package by.epam.information_handling.entity;

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
     * Comstructor for this class.
     * @param componentValue is an input component value.
     */
    public Composite(final Object componentValue) {
        value = componentValue;
    }
    /**
     * Getter for the value of component.
     * @return value.
     */
    public Object getValue() {
        return value;
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
