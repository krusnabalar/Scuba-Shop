package model;

import java.util.ArrayList;

// Represents the available Scuba Gear at the Shop, acts as an inventory
public class ScubaShop {
    // Include Fields here:
    public static ArrayList<ScubaGear> inventory = new ArrayList<>();
    public static final String user = "ScubaHut123";
    public static final String pass = "Underwater54321";

    // EFFECTS: returns true if the input entered in console UI is 'Customer,' and false, if it is 'Shop Owner,'
    // and throws and exception if it is neither.
    public static boolean whoAmI(String string) throws IllegalInputException {
        if (string.equals("Customer") || string.equals("customer")) {
            return true;
        } else if (string.equals("Shop Owner") || string.equals("shop owner")) {
            return false;
        } else {
            throw new IllegalInputException();
        }
    }

    public static class IllegalInputException extends Exception {
    }

    public static void initialInventory() {
        // 4 BCD's
        inventory.add(new ScubaGear("BCD1", ScubaGear.bcdPrice));
        inventory.add(new ScubaGear("BCD2", ScubaGear.bcdPrice));
        inventory.add(new ScubaGear("BCD3", ScubaGear.bcdPrice));
        inventory.add(new ScubaGear("BCD4", ScubaGear.bcdPrice));

        // 4 Masks
        inventory.add(new ScubaGear("Mask1", ScubaGear.maskPrice));
        inventory.add(new ScubaGear("Mask2", ScubaGear.maskPrice));
        inventory.add(new ScubaGear("Mask3", ScubaGear.maskPrice));
        inventory.add(new ScubaGear("Mask4", ScubaGear.maskPrice));

        // 4 Fins
        inventory.add(new ScubaGear("Fins1", ScubaGear.finsPrice));
        inventory.add(new ScubaGear("Fins2", ScubaGear.finsPrice));
        inventory.add(new ScubaGear("Fins3", ScubaGear.finsPrice));
        inventory.add(new ScubaGear("Fins4", ScubaGear.finsPrice));

        // 4 Regulators
        inventory.add(new ScubaGear("Reg1", ScubaGear.regPrice));
        inventory.add(new ScubaGear("Reg2", ScubaGear.regPrice));
        inventory.add(new ScubaGear("Reg3", ScubaGear.regPrice));
        inventory.add(new ScubaGear("Reg4", ScubaGear.regPrice));

        // 4 Tanks
        inventory.add(new ScubaGear("Tank1", ScubaGear.tankPrice));
        inventory.add(new ScubaGear("Tank2", ScubaGear.tankPrice));
        inventory.add(new ScubaGear("Tank3", ScubaGear.tankPrice));
        inventory.add(new ScubaGear("Tank4", ScubaGear.tankPrice));
    }

}
