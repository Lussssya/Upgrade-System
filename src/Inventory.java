import java.util.ArrayList;
import java.util.List;

public class Inventory {
    // List of gained items
    private static List<Item> itemList;

    // no-arg constructor, that initializes the itemList
    public Inventory() {
        itemList = new ArrayList<>();
    }

    // getter
    public List<Item> getItemList() {
        return itemList;
    }

    public static int getInventorySize() {
        return itemList.size();
    }

    // adding items in an increasing order.
    public void addItem(Item item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item to the inventory.");
        }
        int index = 0;
        for (Item currentItem : itemList) {
            if (item.getRarity().compareTo(currentItem.getRarity()) <= 0) {
                break;
            }
            index++;
        }
        itemList.add(index, item);
    }

    // searching the item from start or end, depending on it's rarity.
    private static int findItemIndex(Item item) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // removes element if it exists and returns it, otherwise throws exception
    public static Item removeItem(Item item) throws IllegalArgumentException {
        int index = findItemIndex(item);
        if (index == -1) {
            throw new IllegalArgumentException("Item is absent from inventory.");
        }
        return itemList.remove(index);
    }

    // returning a set of the whole items of the same rarity. (Not used)
    public List<Item> getItemsByRarity(Item.Rarity rarity) {
        int startIndex = -1;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getRarity() == rarity) {
                startIndex = i;
                break;
            }
        }
        if (startIndex == -1) {
            return new ArrayList<>();
        }
        List<Item> answer = new ArrayList<>();
        for (int i = startIndex; i < itemList.size(); i++) {
            Item current = itemList.get(i);
            if (current.getRarity() != rarity) {
                break;
            }
            answer.add(current);
        }
        return answer;
    }

    // display items in inventory
    public static void display() {
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println(i + ": " + itemList.get(i));
        }
    }

    public static Item getItemByIndex(int i) {
        return itemList.get(i);
    }
}