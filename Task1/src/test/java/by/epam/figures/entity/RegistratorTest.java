package by.epam.figures.entity;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link RegistratorTest} is a test for class {@link Registrator}.
 */
public class RegistratorTest {

    /**
     * @return {@link Registrator} objects and their string expressions.
     */
    @DataProvider(name = "test1")
    public static Object[][] registratorToString() {
        return new Object[][]
                {{"Perimeter = 26.133, Square = 65.0",
                       new Registrator(26.133, 65.0 )},
                {"Perimeter = 4.5E123, Square = 9.6E124",
                        new Registrator(4.5e+123, 9.6e+124)}};
    }

    /**
     * @return pairs of {@link Registrator} objects which can be equal or not.
     */
    @DataProvider(name = "test2")
    public static Object[][] registratorPairs() {
        return new Object[][]
                {{new Registrator(12.365, 29.39),
                        new Registrator(12.365, 29.39), true},
                {new Registrator(6.0, 12.3333),
                        new Registrator(12.3333, 6.0), false},
                {new Registrator(6.99, 17.9),
                        new Registrator(6.99e+100, 17.9e+100), false}};
    }

    /**
     * Testing method toString().
     * @param correctString is a string expression of registrator.
     * @param registrator is tested {@link Registrator} object.
     */
    @Test (dataProvider = "test1")
    public final void testToString(final String correctString,
                             final Registrator registrator) {
        Assert.assertEquals(correctString, registrator.toString());
    }

    /**
     * Testing method hashCode().
     * @param registrator1 is a first of comparing {@link Registrator} objects.
     * @param registrator2 is a second of comparing {@link Registrator} objects.
     * @param isEquals informs is the hash codes equal.
     */
    @Test (dataProvider = "test2")
    public final void testHashCode(final Registrator registrator1,
                             final Registrator registrator2,
                             final boolean isEquals) {
        Assert.assertEquals(registrator1.hashCode()
                == registrator2.hashCode(), isEquals);
    }

    /**
     * Testing method equals().
     * @param registrator1 is a first of comparing {@link Registrator} objects.
     * @param registrator2 is a second of comparing {@link Registrator} objects.
     * @param isEquals informs are {@link Registrator} objects equal.
     */
    @Test (dataProvider = "test2")
    public final void testEquals(final Registrator registrator1,
                           final Registrator registrator2,
                           final boolean isEquals) {
        Assert.assertEquals(registrator1.equals(registrator2), isEquals);
    }
}
