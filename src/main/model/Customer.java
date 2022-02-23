package model;

import java.util.ArrayList;

public class Customer {
    // Include Fields Here:
    private String customerName;
    public static ArrayList<ScubaGear> myGear = new ArrayList<>();
    public static ArrayList<Customer> allCustomers = new ArrayList<>();

    // EFFECTS: Creates a customer with name customerName
    public Customer(String customerName) {
        this.customerName = customerName;
    }

    // EFFECTS: Adds gear to the customer's individual list myGear when it is bought from the inventory
    public static void buyGear(ScubaGear gear1) {
        myGear.add(gear1);
    }

    // EFFECTS: Adds new Customer to the list of customers.
    public static void addCustomer(Customer customer) {
        allCustomers.add(customer);
    }
}
