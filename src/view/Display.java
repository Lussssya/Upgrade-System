package view;

import model.Item;
import model.Rarity;

public class Display {
    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String ORANGE = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String BOLD = "\u001B[1m";

    public static void displayActions() {
        System.out.println(BOLD + BLUE + "Actions List" + RESET);
        System.out.println(
                BLUE + "1: " + RESET + GREEN + "Display Inventory" + RESET + "\n" +
                        BLUE + "2: " + RESET + GREEN + "Open a Chest" + RESET + "\n" +
                        BLUE + "3: " + RESET + GREEN + "Display Upgrading Rules" + RESET + "\n" +
                        BLUE + "4: " + RESET + GREEN + "Upgrade" + RESET + "\n" +
                        BLUE + "5: " + RESET + GREEN + "Exit" + RESET
        );
    }

    public static void displayRules() {
        System.out.println(BOLD + GREEN + "Rules List" + RESET);
        System.out.println(
                BLUE + "1. " + RESET +
                "To upgrade a " + ORANGE + "Common" + RESET +
                " item to a " + ORANGE + "Great" + RESET +
                " item, combine it with 2 additional " + ORANGE + "Common" + RESET +
                " items of the same type.\n" +
                BLUE + "2. " + RESET +
                "To upgrade a " + ORANGE + "Great" + RESET +
                " item to a " + ORANGE + "Rare" + RESET +
                " item, combine it with 2 additional " + ORANGE + "Great" + RESET +
                " items of the same type.\n" +
                BLUE + "3. " + RESET +
                "To upgrade a " + ORANGE + "Rare" + RESET +
                " item to an " + ORANGE + "Epic" + RESET +
                " item, combine it with 2 additional " + ORANGE + "Rare" + RESET +
                " items of the same type.\n" +
                BLUE + "4. " + RESET +
                "To upgrade an " + ORANGE + "Epic" + RESET +
                " item to " + ORANGE + "Epic 1" + RESET +
                ", combine it with any other " + ORANGE + "Epic" + RESET +
                " item.\n" +
                BLUE + "5. " + RESET +
                "To upgrade an " + ORANGE + "Epic 1" + RESET +
                " to " + ORANGE + "Epic 2" + RESET +
                ", combine it with any other " + ORANGE + "Epic" + RESET +
                " item. This process converts the first " + ORANGE + "Epic 1" + RESET +
                " into " + ORANGE + "Epic 2" + RESET + ".\n" +
                BLUE + "6. " + RESET +
                "To create a " + ORANGE + "Legendary" + RESET +
                " item, combine an " + ORANGE + "Epic 2" + RESET +
                " item with 2 additional " + ORANGE + "Epic 2" + RESET +
                " items of the same type.\n" +
                BLUE + "7. " + RESET +
                "You cannot upgrade an item of type " + ORANGE + "Legendary." + RESET
        );
    }

    public static void progressBar() {
        for (int i = 0; i < 50; i++) {
            System.out.print(GREEN + '█' + RESET);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
}