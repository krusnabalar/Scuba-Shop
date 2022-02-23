package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

import java.awt.*;

// Represents a Scuba gear with properties including an type, color (maybe), size, and price
public class ScubaGear implements Saveable {
    // Include Fields here:
    private String gearType;
    private double price;
    public static double bcdPrice = 500;
    public static double finsPrice = 125;
    public static double maskPrice = 50;
    public static double regPrice = 300;
    public static double tankPrice = 400;

    // REQUIRES: gearType has a non-zero length, price>0
    // EFFECTS: constructs a Scuba Gear of type gearType, whose cost is equal to price.
    public ScubaGear(String gearType, double price) {
        this.gearType = gearType;
        this.price = price;
    }

    // EFFECTS: returns gear type
    public String getGearType() {
        return gearType;
        //HOW Do I make different types of gears that the user can choose from?
    }

    // EFFECTS: returns price of gear
    public double getPrice() {
        return price;
    }

    @Override
    // EFFECTS: sets up the formatting for the saving of each Scuba Gear in the inventory data file.
    public void save(PrintWriter printWriter) {
        printWriter.print(gearType);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(price);
        printWriter.print(Reader.DELIMITER);
        printWriter.print("\n");
    }
}
