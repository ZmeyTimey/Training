package by.epam.information_handling.entity;

/**
 * Interface specifies the organization of
 * {@link Composite} and {@link Leaf} objects.
 */
public interface Component {

    /**
     * Abstract method for getting the value of component.
     * @return value of component.
     */
    Object getValue();

    /**
     * Abstract method for realization adding components into
     * the class object's list.
     * @param component is a component that should be added.
     */
    void add(Component component);

    /**
     * Abstract method for realization removing components from
     * the class object's list.
     * @param component is a component that should be removed.
     */
    void remove(Component component);

    /**
     * Abstract method for getting child component of this component by index.
     * @param index is a number of child component.
     * @return child component.
     */
    Object getChild(int index);

    /**
     * Abstract method for getting the number of child components
     * of this component.
     * @return the number of child components.
     */
    int getNumberOfChildren();
}
