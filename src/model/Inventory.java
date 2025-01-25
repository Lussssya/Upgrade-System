package model;

import java.util.ArrayList;
import java.util.List;

import view.Display;

public class Inventory {
    // List of gained items
    private static List<Item> itemList;
    // the maximum number of items fitting in the inventory
    private static final int CAPACITY = 10;

    // no-arg constructor, that initializes the itemList
    public Inventory(List<Item> items) {
        itemList = new ArrayList<>();
        for (Item item : items) {
            addItem(item);
        }
    }

    public int getSize() {
        return itemList.size();
    }

    // Adds an item in an increasing order, grouping by name and rarity.
    // Returns the index where the item is added.
    public int addItem(Item item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item to the inventory.");
        }

        int index = 0;

        for (Item currentItem : itemList) {
            // Stop at the right position: same rarity, then same name, or smaller rarity.
            if (item.getRarity().compareTo(currentItem.getRarity()) < 0 ||
                    (item.getRarity().equals(currentItem.getRarity()) && item.getName().compareTo(currentItem.getName()) <= 0)) {
                break;
            }
            index++;
        }

        // Insert the item at the correct index.
        itemList.add(index, item);

        // Return the index where the item was added.
        return index;
    }


    // searching the item from start or end, depending on it's rarity.
    private int findItemIndex(Item item) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).equals(item)) {
                return i;
            }
        }
        return -1;
    }


    // removes element if it exists and returns it, otherwise throws exception
    public void removeItem(Item item) throws IllegalArgumentException {
        int index = findItemIndex(item);
        if (index == -1) {
            throw new IllegalArgumentException("model.Item is absent from inventory.");
        }
        itemList.remove(index);
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
    public void display() {
        System.out.println(Display.BOLD + Display.BLUE + "model.Inventory" + Display.RESET);
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println(i + 1 + ": " + itemList.get(i));
        }
    }

    public Item getItemByIndex(int i) {
        return itemList.get(i);
    }
}