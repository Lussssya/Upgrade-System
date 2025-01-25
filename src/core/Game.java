package core;

import model.*;
import view.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class Game for the game logic.
 */
public class Game {
    private static final Scanner scanner = new Scanner(System.in);
    private final Inventory inventory;

    public Game() {
        greeting();
        Chest chest = new Chest(20);
        inventory = new Inventory(chest.getItems());
    }

    // TODO: change the text
    private void greeting() {
        System.out.println("Welcome to the mysterious cave the wind lead you! Hey, look at me! No, not there. And not there as well...");
        System.out.println("...");
        System.out.println("Just look down! Yeah, I'm here. I'm the " + Display.ORANGE + "BOX" + Display.RESET + "!");
        System.out.println("Stop looking at me like that. You're underestimating me, don't you?! Come on, look!");
        Display.displayActions();
        System.out.println("Ha! Thought I couldn't do even that? Now, enough talking, I'll prove you I'm the best! Go on, open a chest.");
        System.out.println(Display.BLUE + "Dan dan dan... Opening a chest! " + Display.RESET);
        System.out.println("Done! Now look at your inventory. I'll open it for you.");
        System.out.println("Now... If you flatter me, I'll help you, he-he.");
        System.out.print(Display.BLUE + "Type a compliment: " + Display.RESET);
        scanner.nextLine();
        System.out.println("Oh, you really think so..? W-well... Okay, then you can pick whatever action you want!");
        System.out.println(Display.BLUE + "Opening a chest..." + Display.RESET);
    }

    private List<Integer> validateInput(String numbers) {
        String[] split = numbers.split(" ");
        List<Integer> indices = new ArrayList<>();
        for (String s : split) {
            if (!s.matches("\\d+")) {
                throw new IllegalArgumentException("Input contains non-numeric values: " + s);
            }
            int num = Integer.parseInt(s) - 1;
            if (num < 0 || num >= inventory.getSize()) {
                throw new IllegalArgumentException("Number out of range: " + num);
            }
            indices.add(num);
        }

        return indices;
    }

    private void validateAction(int choice) {
        if (choice < 1 || choice > 4) {
            throw new IllegalArgumentException("Invalid action. Please enter a number between 1 and 4.");
        }
    }

    private List<Item> getItemsForUpgrade(String choices) {
        List<Integer> indices = validateInput(choices);
        List<Item> items = new ArrayList<>();
        for (int index : indices) {
            items.add(inventory.getItemByIndex(index));
        }
        return items;
    }

    private Item performUpgrade(List<Item> items) {
        Upgrade upgrade = new Upgrade(items);
        return upgrade.run();
    }

    private void finalizeUpgrade(List<Item> items, Item upgradedItem) {
        for (Item item : items) {
            inventory.removeItem(item);
        }
        int displayNumber = inventory.addItem(upgradedItem) + 1;
        System.out.println("Upgrade successful! Upgraded item: " + upgradedItem + " under number " + displayNumber);
    }

    private void handleUpgradeError(IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        System.out.println("Error. For trying again, type 0. If you want to return to the menu, type anything else.");
    }

    private void upgrade() {
        boolean flag = true;

        while (flag) {
            System.out.println("Type the items you want to merge. (Format: 1 2 3)");
            String choices = scanner.nextLine();
            try {
                List<Item> items = getItemsForUpgrade(choices);
                Item upgradedItem = performUpgrade(items);
                finalizeUpgrade(items, upgradedItem);
                flag = false;
            } catch (IllegalArgumentException e) {
                handleUpgradeError(e);
                if (!scanner.nextLine().equals("0")) {
                    flag = false;
                }
            }
        }
    }

    /**
     * Manages the item upgrade process. It prompts the user for items to merge, validates input,
     * performs the upgrade, and updates the inventory. If an error occurs, the user can retry or exit.
     */
    private boolean handleAction() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            validateAction(choice);

            switch (choice) {
                case 1 -> inventory.display();
                case 2 -> Display.displayRules();
                case 3 -> upgrade();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return false;
                }
            }
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
            return true;
        }
    }

    private void process() {
        do {
            Display.displayActions();
            System.out.print(Display.BLUE + "Choose an action: " + Display.RESET);
        } while (handleAction());
    }

    public void start() {
        System.out.println("So, here are your items: ");
        inventory.display();
        process();
    }
}