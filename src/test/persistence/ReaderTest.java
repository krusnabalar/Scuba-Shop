package persistence;

import model.ScubaGear;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    @Test
    void testParseAccountsFile1() {
        try {
            List<ScubaGear> inventory = Reader.readInventory(new File("./data/testInventoryFile1"));
            ScubaGear s1 = inventory.get(0);
            assertEquals("BCD.A", s1.getGearType());
            assertEquals(490.0, s1.getPrice());

            ScubaGear s2 = inventory.get(1);
            assertEquals("Fins.A", s2.getGearType());
            assertEquals(140.0, s2.getPrice());

            ScubaGear s3 = inventory.get(2);
            assertEquals("Mask.A", s3.getGearType());
            assertEquals(80.0, s3.getPrice());

            ScubaGear s4 = inventory.get(3);
            assertEquals("Reg.A", s4.getGearType());
            assertEquals(230.0, s4.getPrice());

            ScubaGear s5 = inventory.get(4);
            assertEquals("Tank.A", s5.getGearType());
            assertEquals(500.0, s5.getPrice());

            assertEquals(5, inventory.size());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAccountsFile2() {
        try {
            List<ScubaGear> inventory = Reader.readInventory(new File("./data/testInventoryFile2"));
            ScubaGear s1 = inventory.get(0);
            assertEquals("BCD.B", s1.getGearType());
            assertEquals(510.0, s1.getPrice());

            ScubaGear s2 = inventory.get(1);
            assertEquals("Fins.B", s2.getGearType());
            assertEquals(130.0, s2.getPrice());

            ScubaGear s3 = inventory.get(2);
            assertEquals("Mask.B", s3.getGearType());
            assertEquals(70.0, s3.getPrice());

            ScubaGear s4 = inventory.get(3);
            assertEquals("Reg.B", s4.getGearType());
            assertEquals(250.0, s4.getPrice());

            ScubaGear s5 = inventory.get(4);
            assertEquals("Tank.B", s5.getGearType());
            assertEquals(440.0, s5.getPrice());

            assertEquals(5, inventory.size());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readInventory(new File("./path/does/not/exist/testInventoryFile.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
