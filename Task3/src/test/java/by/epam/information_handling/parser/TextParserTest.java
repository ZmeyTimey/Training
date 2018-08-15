package by.epam.information_handling.parser;

import by.epam.information_handling.entity.Component;
import by.epam.information_handling.reader.Reader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The test class for {@link TextParser}.
 */
public class TextParserTest {
    /**
     * The text that should be parsed.
     */
    private String text;

    /**
     * Reading the text from file.
     */
    @BeforeMethod
    public final void beforeMethod() {
        Reader reader = new Reader("/Text.txt");
        reader.readFile();
        text = reader.getReadText();
    }

    /**
     * Testing handleRequest() method.
     */
    @Test
    public final void testHandleRequest() {
        TextParser parser = new TextParser();
        Component textComponent = parser.handleRequest(text);

        int counter = 0;
        while (counter < textComponent.getNumberOfChildren()) {
            Component childComponent = (Component) textComponent.getChild(counter);
            String value = (String) childComponent.getValue();
            System.out.println(value);
            counter++;
        }
    }
}
