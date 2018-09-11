package by.epam.greenhouse.entity;

import java.util.HashSet;

/**
 * Set for storing the properties of a flower.
 */
public class PropertiesSet extends HashSet<String> {

    /**
     * Converting a set of properties to string.
     * @return string value.
     */
    @Override
    public String toString() {
        String result = "";
        for (String value: this) {
            result += value;
            result += ", ";
        }
        int length = result.length();
        result = result.substring(0, length - 2);

        return result;
    }
}
