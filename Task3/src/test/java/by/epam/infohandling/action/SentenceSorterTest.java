package by.epam.infohandling.action;

import by.epam.infohandling.reader.Reader;
import org.testng.annotations.BeforeMethod;

/**
 * The test class for {@link SentenceSorter}.
 */
public class SentenceSorterTest {

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
}
