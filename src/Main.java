import core.Game;
import model.*;
import core.UpgradeManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    private static void testInventoryManagement() {
        System.out.println("\n[TEST] Inventory Management...");

        Inventory inventory = new Inventory();
        Item item1 = new Item("Shield", Rarity.COMMON);
        Item item2 = new Item("Armor", Rarity.COMMON);

        inventory.addItem(item1);
        inventory.addItem(item2);

        System.out.println("Added items to inventory.");
        System.out.println("Inventory size: " + inventory.getSize());

        inventory.removeItem(item1);
        System.out.println("Removed one item. Inventory size: " + inventory.getSize());
    }

    private static void testItemUpgrades() {
        System.out.println("\n[TEST] Item Upgrades...");

        Inventory inventory = new Inventory();
        Item commonItem1 = new Item("Shield", Rarity.COMMON);
        Item commonItem2 = new Item("Shield", Rarity.COMMON);
        Item commonItem3 = new Item("Shield", Rarity.COMMON);

        inventory.addItem(commonItem1);
        inventory.addItem(commonItem2);
        inventory.addItem(commonItem3);

        List<Item> items = List.of(commonItem1, commonItem2, commonItem3);
        UpgradeManager upgradeManager = new UpgradeManager(items, inventory);

        try {
            upgradeManager.upgrade();
            System.out.println("Upgrade successful! New inventory size: " + inventory.getSize());
            System.out.println("New item rarity: " + inventory.getItemByIndex(0).getRarity());
        } catch (IllegalArgumentException e) {
            System.out.println("Upgrade failed: " + e.getMessage());
        }
    }

    private static void testInvalidUpgrades() {
        System.out.println("\n[TEST] Invalid Upgrades...");

        Inventory inventory = new Inventory();
        Item legendaryItem = new Item("Weapon", Rarity.LEGENDARY);

        inventory.addItem(legendaryItem);

        try {
            List<Item> items = List.of(legendaryItem);
            UpgradeManager upgradeManager = new UpgradeManager(items, inventory);
            upgradeManager.upgrade();
        } catch (IllegalArgumentException e) {
            System.out.println("Expected failure: " + e.getMessage());
        }
    }

    private static void testChestMechanics() {
        System.out.println("\n[TEST] Chest Mechanics...");

        List<Item> chestItems = Chest.openChest();
        System.out.println("Chest opened! Items received: " + chestItems.size());

        for (Item item : chestItems) {
            System.out.println(item);
        }
    }
}