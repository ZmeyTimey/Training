package by.epam.infohandling.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Leaf implements Component {

    private static final Logger LOGGER
            = LogManager.getLogger(Leaf.class);

    private Object value;

    public Leaf(final Object leafValue) {
        value = leafValue;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(final Object newValue) {
        value = newValue;
    }
    public void add(final Component component) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("Unable to add child component"
                    + " to elementary component!");
        }
    }

    public void remove(final Component component) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("Unable to remove child component."
                    + " This component is elementary!");
        }
    }

    public void replace(final int number, final Component newComponent) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("Unable to replace child component."
                    + " This component is elementary!");
        }
    }

    public Object getChild(final int number) {

        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("Unable to get child component."
                    + " This component is elementary!");
        }

        return false;
    }

    public int getNumberOfChildren() {

        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("This component can't have children."
                    + " It is elementary!");
        }
        return 0;
        }
    }
