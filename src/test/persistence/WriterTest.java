package persistence;
import model.ScubaGear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testInventory.txt";
    private Writer testWriter;
    private ScubaGear s1;
    private ScubaGear s2;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        s1 = new ScubaGear("BCD6", 505);
        s2 = new ScubaGear("Fins6", 121);
    }

    @Test
    void testWriteInventory() {
        // save gears s1 and s2 to file
        testWriter.write(s1);
        testWriter.write(s2);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<ScubaGear> inventory = Reader.readInventory(new File("./data/testInventory.txt"));
            ScubaGear s1 = inventory.get(0);
            assertEquals("BCD6", s1.getGearType());
            assertEquals(505.0, s1.getPrice());

            ScubaGear s2 = inventory.get(1);
            assertEquals("Fins6", s2.getGearType());
            assertEquals(121.0, s2.getPrice());

            assertEquals(2, inventory.size());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
