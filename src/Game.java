import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Game for game logic.
 */

public class Game {
    private Inventory inventory;
    private static Scanner scanner = new Scanner(System.in);

    // No-arg constructor to initialize the inventory.
    public Game() {
        this.inventory = new Inventory();
        this.inventory.addItem(new Item("Iron Sword", Item.Rarity.COMMON));
        this.inventory.addItem(new Item("Iron Sword", Item.Rarity.COMMON));
        this.inventory.addItem(new Item("Iron Sword", Item.Rarity.COMMON));
        this.inventory.addItem(new Item("Steel Axe", Item.Rarity.GREAT));
        this.inventory.addItem(new Item("Crystal Staff", Item.Rarity.RARE));
        this.inventory.addItem(new Item("Crystal Staff", Item.Rarity.RARE));
        this.inventory.addItem(new Item("Phoenix Bow", Item.Rarity.EPIC, 1));
        this.inventory.addItem(new Item("Dragon Spear", Item.Rarity.LEGENDARY));
    }

    // method to handle user's choices.
    public static void process() {
        type("Hello...");
        type("Show me your inventory, wanderer!!");
        System.out.println("Choose an action.");
        while (true) {
            displayActions();
            int choice = scanner.nextInt();
            if (exit(choice)) {
                break;
            }
            while (choice != 0) {
                type("No, show your damn inventory!");
                choice = scanner.nextInt();
            }
            Inventory.display();
            type("Do you want me to upgrade your pitiful items? (0 for yes, 1 for no)");
            choice = scanner.nextInt();
            if (choice == 1) {
                type("Then why are you even here? Whatever...");
            } else {
                displayActions();
                choice = scanner.nextInt();
                if (exit(choice)) {
                    break;
                }
                while (choice != 1) {
                    type("No, upgrade an item!!");
                    choice = scanner.nextInt();
                }
                if (Inventory.getInventorySize() == 0) {
                    type("What? What are you doing here if you're a newbie. Go and gain experience and items!");
                    break;
                }
                if (!gameUpgrade()) {
                    type("Are you really that dumb? You can't upgrade with these items.");
                } else {
                    type("Good job! Is there anything else you wanna try?");
                }
            }
        }
    }

    // Upgrades the items and removes redundant ones.
    public static boolean gameUpgrade() {
        type("Choose items you want to combine, (eg. 1 5 2 4).");
        Inventory.display();
        String[] splitted = scanner.nextLine().split(" ");
        while (splitted.length < 2) {
            System.out.println("it's empty");
            splitted = scanner.nextLine().split(" ");
        }
        ArrayList<Item> chosen = new ArrayList<>();
        for (String index : splitted) {
            chosen.add(Inventory.getItemByIndex(Integer.parseInt(index)));
        }
        boolean upgraded = Upgrade.upgrade(chosen);
        for (int i = 1; i < chosen.size(); i++) {
            Inventory.removeItem(chosen.get(i));
        }
        return upgraded;
    }

    // Displays the options for action.
    public static void displayActions() {
        System.out.println("\u001B[32m0:\u001B[33m Display Inventory \n" +
                "\u001B[32m1:\u001B[33m Upgrade \n" +
                "\u001B[32m2:\u001B[33m Exit");
    }

    // method for beautiful text:)
    public static void type(String s) {
        try {
            for (char c : s.toCharArray()) {
                System.out.print(c);
                Thread.sleep(100);
            }
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println("It's fine");
        }
    }

    // Exits the game with a tsundere text.
    public static boolean exit(int choice) {
        if (choice == 2) {
            type("Don't ever come back.");
            return true;
        }
        return false;
    }
}