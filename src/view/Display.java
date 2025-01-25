package view;

import model.Item;

public class Display {
    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String ORANGE = "\u001B[33m";
    public static final String BOLD = "\u001B[1m";

    public static void displayActions() {
        System.out.println(BOLD + BLUE + "Actions List" + RESET);
        System.out.println(
                BLUE + "1: " + RESET + GREEN + "Display Inventory" + RESET + "\n" +
                        BLUE + "2: " + RESET + GREEN + "Display Upgrading Rules" + RESET + "\n" +
                        BLUE + "3: " + RESET + GREEN + "Upgrade" + RESET + "\n" +
                        BLUE + "4: " + RESET + GREEN + "Exit" + RESET
        );
    }

    public static void displayRules() {
        System.out.println(BOLD + GREEN + "Rules List" + RESET);
        System.out.println(GREEN +
                BLUE + "1. " + RESET + GREEN +
                "To upgrade a " + ORANGE + "Common" + RESET + GREEN +
                " item to a " + ORANGE + "Great" + RESET + GREEN +
                " item, combine it with 2 additional " + ORANGE + "Common" + RESET + GREEN +
                " items of the same type.\n" +
                BLUE + "2. " + RESET + GREEN +
                "To upgrade a " + ORANGE + "Great" + RESET + GREEN +
                " item to a " + ORANGE + "Rare" + RESET + GREEN +
                " item, combine it with 2 additional " + ORANGE + "Great" + RESET + GREEN +
                " items of the same type.\n" +
                BLUE + "3. " + RESET + GREEN +
                "To upgrade a " + ORANGE + "Rare" + RESET + GREEN +
                " item to an " + ORANGE + "Epic" + RESET + GREEN +
                " item, combine it with 2 additional " + ORANGE + "Rare" + RESET + GREEN +
                " items of the same type.\n" +
                BLUE + "4. " + RESET + GREEN +
                "To upgrade an " + ORANGE + "Epic" + RESET + GREEN +
                " item to " + ORANGE + "Epic 1" + RESET + GREEN +
                ", combine it with any other " + ORANGE + "Epic" + RESET + GREEN +
                " item.\n" +
                BLUE + "5. " + RESET + GREEN +
                "To upgrade an " + ORANGE + "Epic 1" + RESET + GREEN +
                " to " + ORANGE + "Epic 2" + RESET + GREEN +
                ", combine it with any other " + ORANGE + "Epic" + RESET + GREEN +
                " item. This process converts the first " + ORANGE + "Epic 1" + RESET + GREEN +
                " into " + ORANGE + "Epic 2" + RESET + GREEN + ".\n" +
                BLUE + "6. " + RESET + GREEN +
                "To create a " + ORANGE + "Legendary" + RESET + GREEN +
                " item, combine an " + ORANGE + "Epic 2" + RESET + GREEN +
                " item with 2 additional " + ORANGE + "Epic 2" + RESET + GREEN +
                " items of the same type.\n" +
                BLUE + "7. " + RESET + GREEN +
                "You cannot upgrade an item of type " + ORANGE + "Legendary." + RESET
        );
    }

    // A method for mimicking typing
    public static void type(String s, int speed) {
        try {
            for (char c : s.toCharArray()) {
                System.out.print(c);
                Thread.sleep(speed);
            }
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println("It's fine");
        }
    }

    public static String getRarityMessage(Item.Rarity rarity) {
        return switch (rarity) {
            case Item.Rarity.COMMON -> "But it's okay, don't cry!";
            case Item.Rarity.GREAT -> "And, wow... not bad!";
            case Item.Rarity.RARE -> "Yay, it is getting better and better!";
            case Item.Rarity.EPIC -> "Great luck!!!";
            case Item.Rarity.LEGENDARY -> "Are you even human?!";
        };
    }
}