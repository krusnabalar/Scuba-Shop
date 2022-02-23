package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private ScubaGear sg1 = new ScubaGear("BCD", 550);
    private final Customer c1 = new Customer("A");


    @Test
    public void testBuyGear() {
        Customer.buyGear(sg1);
        assertTrue(Customer.myGear.contains(sg1));
    }

    @Test
    public void testAddCustomer() {
        Customer.addCustomer(c1);
        assertTrue(Customer.allCustomers.contains(c1));
    }
}
