package model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScubaShopTest {

    @Test
    public void testWhoAmI () {
        try {
            assertTrue(ScubaShop.whoAmI("Customer"));
        } catch (ScubaShop.IllegalInputException e) {
            e.printStackTrace();
        }
        try {
            assertTrue(ScubaShop.whoAmI("customer"));
        } catch (ScubaShop.IllegalInputException e) {
            e.printStackTrace();
        }
        try {
            assertFalse(ScubaShop.whoAmI("Shop Owner"));
        } catch (ScubaShop.IllegalInputException e) {
            e.printStackTrace();
        }
        try {
            assertFalse(ScubaShop.whoAmI("shop owner"));
        } catch (ScubaShop.IllegalInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInitialInventory () {
        ScubaShop.initialInventory();
        assertEquals(20, ScubaShop.inventory.size());
    }

    @Test
    public void testIllegalInputException (){
        try {
            ScubaShop.whoAmI("gibberish");
            fail();
        } catch (ScubaShop.IllegalInputException e) {

        }
    }

}
