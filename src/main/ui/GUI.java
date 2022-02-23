package ui;

import model.*;
import persistence.Reader;
import persistence.Writer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;
import java.util.List;


public class GUI implements ActionListener {
    private static final String INVENTORY_FILE = "./data/inventory";
    private static final int MAX_FRAME_WIDTH = 1500;
    private static final int MAX_FRAME_HEIGHT = 700;

    private static final JFrame customerFrame = new JFrame("Scuba Shop - The Customer's Perspective");
    private static final JPanel buyingPanel = new JPanel();
    private static final JLabel buyGear = new JLabel("Please enter the name of the gear you want to buy here:");
    private static final JTextField gearName = new JTextField(20);
    private static final JButton saveButton = new JButton("Save Inventory");
    private static final JButton buyButton = new JButton("Buy!");
    private static final JButton loadInventoryButton = new JButton("Load Inventory");

    private static final JFrame inventoryFrame = new JFrame("Scuba Shop - The Shop's Inventory!");

    private static final JFrame shopOwnerFrame = new JFrame("Scuba Shop - The Owner's Perspective");
    private static final JPanel shopPanel = new JPanel();
    private static final JLabel welcome = new JLabel("Welcome Back! Click here to view the inventory of the shop:");
    private static final JLabel addGearName = new JLabel("Gear Name:");
    private static final JTextField addName = new JTextField(20);
    private static final JLabel addGearPrice = new JLabel("Gear Price:");
    private static final JTextField addPrice = new JTextField(20);
    private static final JButton addButton = new JButton("Add To Inventory!");

    private static final JFrame loginFrame = new JFrame("Scuba Shop - Login Portal");
    private static final JPanel loginPanel = new JPanel();
    private static final JLabel loginSuccessful = new JLabel("");
    private static final JLabel loginFailed = new JLabel("");
    private static final JTextField userText = new JTextField(20);
    private static JPasswordField passwordText;
    private static final JButton loginButton = new JButton("Login");
    private static final JLabel scubaLogo = new JLabel(new ImageIcon("data/ScubaCPSC210.jpg"));
    private static final JLabel loginLabel = new JLabel("Please Verify Your Credentials:");

    public static void main(String[] args) {
        beginShop();
    }


    // MODIFIES: this
    // EFFECTS: loads accounts from INVENTORY_FILE, if that

    // MODIFIES: this
    // EFFECTS: processes user input, begin's the Scuba Shop application
    public static void beginShop() {
        loadInventory();

        Scanner in = new Scanner(System.in);
        System.out.println("Are you a: 'Customer' or a 'Shop Owner'?");
        String s = in.nextLine();

        try {
            if (ScubaShop.whoAmI(s)) {
                customerPerspective();

            } else {
                shopOwnerLoginPanel();
            }
        } catch (ScubaShop.IllegalInputException e) {
            System.out.println("Illegal Input try again!");
        }
    }

    // EFFECTS: Interprets the code from the perspective of the Customer
    public static void customerPerspective() {
        Scanner inp = new Scanner(System.in);
        System.out.println("Welcome to Scuba Hut! Please enter your name:");
        String s1 = inp.nextLine();
        Customer c = new Customer(s1);
        Customer.addCustomer(c);

        customerFrame.setPreferredSize(new Dimension(MAX_FRAME_WIDTH * 2, MAX_FRAME_HEIGHT));
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.add(buyingPanel);

        buyingPanel.setLayout(null);
        buyingPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JLabel greeting = new JLabel("Hello " + s1 + ", here is a list of gear presently available at the shop:");
        greeting.setBounds(780, 25, 400, 25);
        buyingPanel.add(greeting);

        buyGear.setBounds(40, 100, 350, 25);
        buyingPanel.add(buyGear);

        gearName.setBounds(40, 140, 165, 25);
        buyingPanel.add(gearName);

        restOfConsumerPerspective();
    }

    //EFFECTS: continues working on the Customer Perspective, creates a GUI
    public static void restOfConsumerPerspective() {
        saveButton.setBounds(40, 250,165, 25);
        saveButton.addActionListener(
                e -> {
                    saveInventory();
                    playSound("data/bloop_x.wav"); });
        buyingPanel.add(saveButton);

        buyButton.setBounds(40, 175, 165, 50);
        buyButton.addActionListener(
                e -> {
                    buyGear();
                    playSound("data/cash_register_x.wav"); });
        buyingPanel.add(buyButton);

        loadInventoryButton.setBounds(880, 55, 165, 25);
        loadInventoryButton.addActionListener(
                e -> {
                    inventoryTable();
                    playSound("data/blurp_x.wav"); });
        buyingPanel.add(loadInventoryButton);

        customerFrame.pack();
        customerFrame.setVisible(true);
    }

    // EFFECTS: Interprets the code from the perspective of the Shop Owner
    public static void shopOwnerLoginPanel() {
        loginFrame.setPreferredSize(new Dimension(MAX_FRAME_WIDTH, MAX_FRAME_HEIGHT));
        loginFrame.getContentPane().add(loginPanel);
        loginPanel.setLayout(null);

        loginLabel.setBounds((MAX_FRAME_WIDTH / 2) - (175 / 2), 15, 175, 25);
        loginPanel.add(loginLabel);

        makeLoginGui();
    }

    //EFFECTS: continues working on the Customer Perspective, creates a Login Portal GUI
    public static void makeLoginGui() {
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 50, 80, 25);
        loginPanel.add(usernameLabel);

        userText.setBounds(100, 50, 165, 25);
        loginPanel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 80, 80, 25);
        loginPanel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 80, 165, 25);
        loginPanel.add(passwordText);

        loginButton.setBounds(10, 110, 75, 25);
        loginButton.addActionListener(new GUI());
        loginPanel.add(loginButton);

        loginSuccessful.setBounds((MAX_FRAME_WIDTH / 2) - (200 / 2), 110, 350, 25);
        loginPanel.add(loginSuccessful);

        loginFailed.setBounds(100, 150, 275, 25);
        loginPanel.add(loginFailed);

        scubaLogo.setBounds((MAX_FRAME_WIDTH / 2) - (450 / 2), 100, 500, 500);
        loginPanel.add(scubaLogo);

        loginFrame.pack();
        loginFrame.setVisible(true);
    }

    // EFFECTS: makes a GUI from the Shop Owner's perspective which can be used to add gear
    public static void shopOwnerPortal() {
        shopOwnerFrame.setPreferredSize(new Dimension(MAX_FRAME_WIDTH * 2, MAX_FRAME_HEIGHT * 2));
        shopOwnerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopOwnerFrame.add(shopPanel);

        shopPanel.setLayout(null);

        welcome.setBounds(750, 25, 500, 25);
        shopPanel.add(welcome);

        loadInventoryButton.setBounds(870, 55, 165, 25);
        loadInventoryButton.addActionListener(
                e -> {
                    inventoryTable();
                    playSound("data/blurp_x.wav"); });
        shopPanel.add(loadInventoryButton);

        addGearName.setBounds(10, 50, 100, 25);
        shopPanel.add(addGearName);

        addName.setBounds(125, 50, 165, 25);
        shopPanel.add(addName);

        addGearPrice.setBounds(10, 80, 100, 25);
        shopPanel.add(addGearPrice);

        restOfShopPerspective();
    }

    // EFFECTS: Continues working on shopOwnerPortal();
    public static void restOfShopPerspective() {
        addPrice.setBounds(125, 80, 165, 25);
        shopPanel.add(addPrice);

        addButton.setBounds(123, 110, 300, 25);
        addButton.addActionListener(
                e -> {
                    String gearN = addName.getText();
                    String gearP = addPrice.getText();
                    double price = Double.parseDouble(gearP);
                    ScubaShop.inventory.add(new ScubaGear(gearN, price));
                    saveInventory();
                    playSound("data/bloop_x.wav");
                }
        );
        shopPanel.add(addButton);

        shopOwnerFrame.pack();
        shopOwnerFrame.setVisible(true);
    }

    // EFFECTS: produces an inventory Table in Java Swing
    public static void inventoryTable() {
        inventoryFrame.setPreferredSize(new Dimension(MAX_FRAME_WIDTH, MAX_FRAME_HEIGHT - 200));
        String[] colNames = {"Gear Names", "Gear Prices"};
        Vector<String> colNamesV = new Vector<>(Arrays.asList(colNames));
        Vector rowData = new Vector();
        for (ScubaGear s : ScubaShop.inventory) {
            Vector  tempData = new Vector(Arrays.asList(s.getGearType(), s.getPrice()));
            rowData.add(tempData);
        }

        JTable gearTable = new JTable(rowData, colNamesV);
        gearTable.setGridColor(Color.CYAN);
        inventoryFrame.add(new JScrollPane(gearTable));

        inventoryFrame.pack();
        inventoryFrame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
    // otherwise initializes Shop with default Initial Inventory
    private static void loadInventory() {
        try {
            List<ScubaGear> inventory = Reader.readInventory(new File(INVENTORY_FILE));
            ScubaShop.inventory.addAll(inventory);
        } catch (IOException e) {
            ScubaShop.initialInventory();
        }
    }

    // EFFECTS: saves state of inventory to INVENTORY_FILE
    private static void saveInventory() {
        try {
            Writer writer = new Writer(new File(INVENTORY_FILE));
            for (ScubaGear s : ScubaShop.inventory) {
                writer.write(s);
            }
            writer.close();
            System.out.println("All Scuba Gear saved to file " + INVENTORY_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save Scuba Gear to " + INVENTORY_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // EFFECTS: adds gear from the inventory to the customer's gear
    public static void buyGear() {
        String gearNameText = gearName.getText();

        // For the ConcurrentModificationException:
        ArrayList<ScubaGear> toRemove = new ArrayList<>();
        for (ScubaGear gear : ScubaShop.inventory) {
            if (gearNameText.equals(gear.getGearType())) {
                toRemove.add(gear);
                Customer.buyGear(gear);
            }
        }
        ScubaShop.inventory.removeAll(toRemove);
    }



    // EFFECTS: helper to add sound to the GUI
    public static void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    @Override
    // EFFECTS: action performed for the login button
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
        if (user.equals(ScubaShop.user) && password.equals(ScubaShop.pass)) {
            playSound("data/applause_y.wav");
            loginSuccessful.setText("Login Successful! Welcome back to the shop! (Go to console to continue)");
            shopOwnerPortal();
            loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
        } else {
            playSound("data/slide_whistle_down.wav");
            loginFailed.setText("Error, incorrect username or password.");
            passwordText.setText("");
            userText.requestFocus();
        }
    }
}