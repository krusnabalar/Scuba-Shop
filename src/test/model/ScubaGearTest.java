package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScubaGearTest {
    private ScubaGear sg2 = new ScubaGear("Fins", 130);

    @Test
    public void testScubaGear () {
        ScubaGear s = new ScubaGear("Fins", 130);
        assertEquals("Fins", s.getGearType());
        assertEquals(130, s.getPrice());
    }

    @Test
    public void testGetGearType () {
        assertEquals("Fins", sg2.getGearType());
    }

    @Test
    public void testGetPrice () {
        assertEquals(130, sg2.getPrice());
    }
}