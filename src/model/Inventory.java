package model;

import java.util.*;

import view.Display;

public class Inventory {
    // a queue of items the user owns
    private final HashMap<Rarity, List<Item>> inventory;
    private static final int CAPACITY = 30;

    public Inventory() {
        inventory = new HashMap<>();
    }

    public int getSize() {
        int size = 0;
        for (Rarity rarity : inventory.keySet()) {
            size += inventory.get(rarity).size();
        }
        return size;
    }

    public void addItem(Item item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item to the inventory.");
        }
        if (getSize() >= CAPACITY) {
            throw new IllegalArgumentException("Inventory is full.");
        }

        if (inventory.get(item.getRarity()) == null) {
            List<Item> temp = new ArrayList<>();
            temp.add(item);
            inventory.put(item.getRarity(), temp);
        } else {
            inventory.get(item.getRarity()).add(item);
        }
    }

    public void removeItem(Item item) {
        List<Item> items = inventory.get(item.getRarity());
        if (items == null || !items.remove(item)) {
            throw new IllegalArgumentException("Item not found in inventory.");
        }
    }

    public void display() {
        System.out.println(Display.BOLD + Display.BLUE + "Inventory" + Display.RESET);
        int ordering = 1;

        for (Rarity rarity : inventory.keySet()) {
            for (Item item : inventory.get(rarity)) {
                System.out.println(Display.BLUE + ordering + Display.RESET + ": " + item);
                ordering++;
            }
        }
    }

    public Item getItemByIndex(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IllegalArgumentException("Index out of bounds.");
        }

        int cumulativeCount = 0;

        for (Map.Entry<Rarity, List<Item>> entry : inventory.entrySet()) {
            List<Item> itemsInRarity = entry.getValue();
            int currentSize = itemsInRarity.size();

            if (index < cumulativeCount + currentSize) {
                int itemIndexInRarity = index - cumulativeCount;
                return itemsInRarity.get(itemIndexInRarity);
            }

            cumulativeCount += currentSize;
        }

        throw new IllegalArgumentException("Index out of bounds.");
    }

}