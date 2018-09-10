package by.epam.infohandling.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is for storing text structural components
 * not consisting of other components.
 */
public class Leaf implements Component {

    /**
     * Coefficient is used in hash code generating.
     */
    private static final int HASH_CODE_COEFFICIENT_1 = 13;
    /**
     * Coefficient is used in hash code generating.
     */
    private static final int HASH_CODE_COEFFICIENT_2 = 37;

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Leaf.class);

    /**
     * The value of component.
     */
    private Object value;

    @Override
    public final int hashCode() {
        int result = HASH_CODE_COEFFICIENT_1;

        result = HASH_CODE_COEFFICIENT_2 * result
                + (value == null ? 0 : value.hashCode());

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

        Leaf component = (Leaf) obj;
        Object componentValue = component.getValue();

        return (value == component.value
                || (value != null && value.equals(componentValue)));
    }

    /**
     * Constructor for this class.
     * @param leafValue is an input component value.
     */
    public Leaf(final Object leafValue) {
        value = leafValue;
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
     * The method produces an error because leaf component
     * can't have any children components.
     * @param component is a component that should be added.
     */
    public void add(final Component component) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("Unable to add child component"
                    + " to elementary component!");
        }
    }

    /**
     * The method produces an error because leaf component
     * can't have any children components.
     * @param component is a component that should be removed.
     */
    public void remove(final Component component) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("Unable to remove child component."
                    + " This component is elementary!");
        }
    }

    /**
     * The method produces an error because leaf component
     * can't have any children components.
     * @param index is a number of child component.
     * @return false.
     */
    public Object getChild(final int index) {

        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("Unable to get child component."
                    + " This component is elementary!");
        }

        return false;
    }

    /**
     * The method produces an error because leaf component
     * can't have any children components.
     * @return 0.
     */
    public int getNumberOfChildren() {

        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("This component can't have children."
                    + " It is elementary!");
        }
        return 0;
        }
    }
