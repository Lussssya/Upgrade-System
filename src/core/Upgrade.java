package core;

import model.Item;

import java.util.List;

/**
 * Class to handle upgrading items.
 */
public class Upgrade {
    private final List<Item> mergingItems;

    public Upgrade(List<Item> mergingItems) {
        this.mergingItems = mergingItems;
    }

    private void validateMergingItems() {
        if (mergingItems == null || (mergingItems.size() != 2 && mergingItems.size() != 3)) {
            throw new IllegalArgumentException("Wrong number of items. You must provide 2 or 3 items.");
        }

        Item baseItem = mergingItems.getFirst();

        if (baseItem.getRarity() == Item.Rarity.EPIC) {
            checkEpicUpgrade(baseItem);
            return;
        }
        for (int i = 1; i < mergingItems.size(); i++) {
            Item currentItem = mergingItems.get(i);
            if (!baseItem.getName().equals(currentItem.getName())) {
                throw new IllegalArgumentException("Not matching item types.");
            }
            if (baseItem.getRarity() != currentItem.getRarity()) {
                throw new IllegalArgumentException("Not matching rarities.");
            }
        }
        if (baseItem.getRarity() == Item.Rarity.LEGENDARY) {
            throw new IllegalArgumentException("Legendary items cannot be upgraded!");
        }
    }

    // Validates the list with an EPIC item separately
    private void checkEpicUpgrade(Item baseItem) {
        int baseUpgradeCount = baseItem.getUpgradeCount();

        if (baseUpgradeCount < 2) {
            if (mergingItems.size() != 2) {
                throw new IllegalArgumentException("To upgrade, you need exactly 1 additional Epic item.");
            }
            if (mergingItems.get(1).getRarity() != Item.Rarity.EPIC) {
                throw new IllegalArgumentException("To upgrade, you must combine it with another Epic item.");
            }
        } else if (baseUpgradeCount == 2) {
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
    }

    public Item run() {
        validateMergingItems();
        Item baseItem = mergingItems.getFirst();

        if (baseItem.getRarity() == Item.Rarity.EPIC && baseItem.getUpgradeCount() < 2) {
            baseItem.incrementUpgradeCount();
        } else if (baseItem.getRarity() != Item.Rarity.EPIC) {
            baseItem.upgradeRarity();
        } else {
            baseItem.upgradeRarity();
            baseItem.setUpgradeCount(0);
        }
        return baseItem;
    }
}