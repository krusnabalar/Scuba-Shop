package persistence;

import model.ScubaGear;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of Scuba Gear parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<ScubaGear> readInventory(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }


    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of Scuba Gear parsed from list of strings
    // where each string contains data for one account
    private static List<ScubaGear> parseContent(List<String> fileContent) {
        List<ScubaGear> inventory = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            inventory.add(parseScubaGear(lineComponents));
        }

        return inventory;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 2 where element 0 represents the
    // type of the next Scuba Gear to be constructed, and element 1 represents
    // the price of that gear.
    // EFFECTS: returns a Scuba Gear constructed from components
    private static ScubaGear parseScubaGear(List<String> components) {
        String type = components.get(0);
        double price = Double.parseDouble(components.get(1));
        return new ScubaGear(type, price);
    }



}
