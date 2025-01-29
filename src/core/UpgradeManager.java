package core;

import model.Inventory;
import model.Item;
import model.Rarity;

import java.util.List;

public class UpgradeManager {
    private final List<Item> mergingItems;
    private final Inventory inventory;

    public UpgradeManager(List<Item> mergingItems, Inventory inventory) {
        this.mergingItems = mergingItems;
        this.inventory = inventory;
    }

    public void validateMergingItems() {
        if (mergingItems == null || (mergingItems.size() != 2 && mergingItems.size() != 3)) {
            throw new IllegalArgumentException("Wrong number of items. You must provide 2 or 3 items.");
        }

        Item baseItem = mergingItems.get(0);

        switch (baseItem.getRarity()) {
            case EPIC:
                checkEpicUpgrade(baseItem);
                break;
            case LEGENDARY:
                throw new IllegalArgumentException("Legendary items cannot be upgraded!");
            default:
                validateCommonUpgrade(baseItem);
                break;
        }
    }

    // validates the upgrade for every item except epic and legendary
    private void validateCommonUpgrade(Item baseItem) {
        for (int i = 1; i < mergingItems.size(); i++) {
            Item currentItem = mergingItems.get(i);
            if (!baseItem.getName().equals(currentItem.getName())) {
                throw new IllegalArgumentException("Not matching item types.");
            }
            if (baseItem.getRarity() != currentItem.getRarity()) {
                throw new IllegalArgumentException("Not matching rarities.");
            }
        }
    }

    // validates the upgrade for epic items
    private void checkEpicUpgrade(Item baseItem) {
        int baseUpgradeCount = baseItem.getUpgradeCount();

        if (baseUpgradeCount < 2) {
            validateEpicToEpicUpgrade(baseItem);
        } else if (baseUpgradeCount == 2) {
            validateEpic2ToLegendaryUpgrade(baseItem);
        }
    }

    // validates upgrade from Epic to Epic
    private void validateEpicToEpicUpgrade(Item baseItem) {
        if (mergingItems.size() != 2) {
            throw new IllegalArgumentException("To upgrade, you need exactly 1 additional Epic item.");
        }
        if (mergingItems.get(1).getRarity() != Rarity.EPIC) {
            throw new IllegalArgumentException("To upgrade, you must combine it with another Epic item.");
        }
    }

    // validates upgrade from Epic 2 to Legendary
    private void validateEpic2ToLegendaryUpgrade(Item baseItem) {
        if (mergingItems.size() != 3) {
            throw new IllegalArgumentException("To create a Legendary item, you need 2 additional Epic 2 items of the same type.");
        }
        for (int i = 1; i < mergingItems.size(); i++) {
            Item currentItem = mergingItems.get(i);
            if (!baseItem.getName().equals(currentItem.getName()) || currentItem.getUpgradeCount() != 2) {
                throw new IllegalArgumentException("To create a Legendary item, all items must be Epic 2 of the same type.");
            }
        }
    }

    public void upgrade() {
        validateMergingItems();
        Item baseItem = mergingItems.getFirst();

        if (baseItem.getRarity() == Rarity.EPIC && baseItem.getUpgradeCount() < 2) {
            baseItem.incrementUpgradeCount();
        } else if (baseItem.getRarity() != Rarity.EPIC) {
            baseItem.upgradeRarity();
        } else {
            baseItem.upgradeRarity();
            baseItem.setUpgradeCount(0);
        }
        for (Item item : mergingItems) {
            inventory.removeItem(item);
        }
        inventory.addItem(baseItem);
        System.out.println("Successfully upgraded the item " + baseItem);
    }
}