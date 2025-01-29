import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;
import core.UpgradeManager;
import java.util.Arrays;
import java.util.List;

class GameTests {
    private Inventory inventory;
    private Item commonItem1, commonItem2, commonItem3;
    private Item epicItem1, epicItem2;
    private Item legendaryItem;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        commonItem1 = new Item("Shield", Rarity.COMMON);
        commonItem2 = new Item("Shield", Rarity.COMMON);
        commonItem3 = new Item("Shield", Rarity.COMMON);
        epicItem1 = new Item("Relic", Rarity.EPIC, 1);
        epicItem2 = new Item("Relic", Rarity.EPIC, 2);
        legendaryItem = new Item("Weapon", Rarity.LEGENDARY);
    }

    @Test
    void testAddItemToInventory() {
        inventory.addItem(commonItem1);
        assertEquals(1, inventory.getSize());
    }

    @Test
    void testAddNullItemToInventory() {
        assertThrows(IllegalArgumentException.class, () -> inventory.addItem(null));
    }

    @Test
    void testRemoveItemFromInventory() {
        inventory.addItem(commonItem1);
        inventory.removeItem(commonItem1);
        assertEquals(0, inventory.getSize());
    }

    @Test
    void testInventoryCapacityLimit() {
        for (int i = 0; i < 30; i++) {
            inventory.addItem(new Item("Armor", Rarity.COMMON));
        }
        assertThrows(IllegalArgumentException.class, () -> inventory.addItem(new Item("Armor", Rarity.COMMON)));
    }

    @Test
    void testUpgradeValidItems() {
        inventory.addItem(commonItem1);
        inventory.addItem(commonItem2);
        inventory.addItem(commonItem3);
        List<Item> items = Arrays.asList(commonItem1, commonItem2, commonItem3);
        UpgradeManager upgradeManager = new UpgradeManager(items, inventory);
        upgradeManager.upgrade();
        assertEquals(1, inventory.getSize());
        assertEquals(Rarity.GREAT, inventory.getItemByIndex(0).getRarity());
    }

    @Test
    void testUpgradeEpicItem() {
        inventory.addItem(epicItem1);
        inventory.addItem(epicItem2);
        List<Item> items = Arrays.asList(epicItem1, epicItem2);
        UpgradeManager upgradeManager = new UpgradeManager(items, inventory);
        upgradeManager.upgrade();
        assertEquals(1, inventory.getSize());
        assertEquals(Rarity.EPIC, inventory.getItemByIndex(0).getRarity());
    }

    @Test
    void testUpgradeLegendaryItemThrowsException() {
        inventory.addItem(legendaryItem);
        assertThrows(IllegalArgumentException.class, () -> new UpgradeManager(List.of(legendaryItem), inventory).validateMergingItems());
    }

    @Test
    void testValidateNonMatchingItemUpgrade() {
        inventory.addItem(commonItem1);
        inventory.addItem(new Item("Armor", Rarity.COMMON));
        assertThrows(IllegalArgumentException.class, () -> new UpgradeManager(Arrays.asList(commonItem1, new Item("Armor", Rarity.COMMON)), inventory).validateMergingItems());
    }

    @Test
    void testGetItemByInvalidIndex() {
        assertThrows(IllegalArgumentException.class, () -> inventory.getItemByIndex(0));
    }
}