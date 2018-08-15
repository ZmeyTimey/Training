package by.epam.information_handling.reader;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This is a test for {@link Reader} class.
 */
public class ReaderTest {

    /**
     * Correct String value of the text that should be read from file.
     */
    private String correctText;

    /**
     * Method sets the value of the correct text variable.
     */
    @BeforeMethod
    public final void beforeMethod() {
        correctText = "It has survived - not only (five) "
                + "centuries, but also the leap "
                + "into 13<<2 electronic typesetting, "
                + "remaining 3>>5 essentially ~6&9|(3&4) unchanged. "
                + "It was popularised in the "
                + "5|(1&2&(3|4&(^5|6&47)|3)|2)|1) "
                + "with the release of Letraset sheets containing "
                + "Lorem Ipsum passages, and more recently "
                + "with desktop publishing software like "
                + "Aldus PageMaker including versions "
                + "of Lorem Ipsum.\n"
                + "It is a long established fact that "
                + "a reader will be distracted by "
                + "the readable content of a page when looking "
                + "at its layout. The point of using "
                + "(~71&(2&3|(3|2&1>>2|2)&2)|10&2))|78 "
                + "Ipsum is that it has a more-or-less normal "
                + "distribution of letters, as opposed to using "
                + "(Content here), content here', making it "
                + "look like readable English.\n"
                + "It is (^5|1&2<<(2|5>>2&71))|1200 established "
                + "fact that a reader will be of a page when "
                + "looking at its layout.\n"
                + "Bye.";
    }

    /**
     * Method checks whether the value of the text read from the file
     * matches with the specified value.
     */
    @Test
    public void testReadFile() {
        Reader reader = new Reader("/Text.txt");
        reader.readFile();
        String result = reader.getReadText();
        Assert.assertEquals(result, correctText);
    }
}
