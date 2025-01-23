import java.util.List;

/**
 * Class to handle upgrading.
 */
public class Upgrade {
    // upgrades the item if the user meets the requirements.
    public static boolean upgrade(List<Item> merge) {
        if (merge == null || (merge.size() != 2 && merge.size() != 3)) {
            return false;
        }
        Item item = merge.getFirst();
        if (merge.size() == 3 && item.getRarity() == merge.get(1).getRarity() && item.getRarity() == merge.get(2).getRarity()) {
            System.out.println("Current " + item.getRarity());
            item.setRarity(item.nextRarity());
            System.out.println("Updated " + item.getRarity());
        } else if (merge.size() == 2 && item.getRarity() == merge.get(1).getRarity()) {
            return upgradeEpic(item, merge.get(1));
        } else {
            return false;
        }
        return true;
    }

    // helper method for handling epic items.
    private static boolean upgradeEpic(Item item, Item merge) {
        if (merge.getRarity() != Item.Rarity.EPIC) {
            return false;
        }
        if (item.getUpgradeCount() <= 1 && merge.getUpgradeCount() <= 1) {
            item.setUpgradeCount(item.getUpgradeCount() + 1);
        } else {
            item.setUpgradeCount(0);
            item.setRarity(Item.Rarity.LEGENDARY);
        }
        return true;
    }
}