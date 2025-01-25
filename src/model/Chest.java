package model;

import view.Display;

import java.util.*;

public class Chest {
    // items available in the chest (total - 50)
    private List<Item> itemList;
    // the list of items chosen randomly
    private List<Item> items;
    // the list with the information of number of items of specific rarity => the percentage of it being picked.
    private Map<Item.Rarity, Integer> rarityWeights;
    private final int itemCount;
    private final Random random = new Random();

    // constructor, that gets the count of items that the user can pick. Each time it initializes items, rarityWeights and itemList
    public Chest(int itemCount) {
        initializeRarityWeights();
        initializeItemList();
        this.itemCount = itemCount > 0 && itemCount <= itemList.size() ? itemCount : -1;
        initializeItems();
    }

    public List<Item> getItems() {
        return items;
    }

    // initializing the weighs of items by rarity
    private void initializeRarityWeights() {
        rarityWeights = new HashMap<>();
        rarityWeights.put(Item.Rarity.COMMON, 20);
        rarityWeights.put(Item.Rarity.GREAT, 16);
        rarityWeights.put(Item.Rarity.RARE, 8);
        rarityWeights.put(Item.Rarity.EPIC, 4);
        rarityWeights.put(Item.Rarity.LEGENDARY, 2);
    }

    // Initializing the item list each time the object of class Chest is created, since it's modified after adding into inventory
    private void initializeItemList() {
        itemList = new ArrayList<>();
        fillListByDefaultItems("Shield", Item.Rarity.COMMON);
        fillListByDefaultItems("Weapon", Item.Rarity.COMMON);
        fillListByDefaultItems("Armor", Item.Rarity.COMMON);
        fillListByDefaultItems("Relic", Item.Rarity.COMMON);

        fillListByDefaultItems("Shield", Item.Rarity.GREAT);
        fillListByDefaultItems("Weapon", Item.Rarity.GREAT);
        fillListByDefaultItems("Armor", Item.Rarity.GREAT);
        fillListByDefaultItems("Relic", Item.Rarity.GREAT);

        fillListByDefaultItems("Shield", Item.Rarity.RARE);
        fillListByDefaultItems("Weapon", Item.Rarity.RARE);
        fillListByDefaultItems("Armor", Item.Rarity.RARE);
        fillListByDefaultItems("Relic", Item.Rarity.RARE);

        itemList.add(new Item("Shield", Item.Rarity.EPIC, random.nextInt(3)));
        itemList.add(new Item("Weapon", Item.Rarity.EPIC, random.nextInt(3)));
        itemList.add(new Item("Armor", Item.Rarity.EPIC, random.nextInt(3)));
        itemList.add(new Item("Relic", Item.Rarity.EPIC, random.nextInt(3)));

        itemList.add(new Item("Shield", Item.Rarity.LEGENDARY));
        itemList.add(new Item("Weapon", Item.Rarity.LEGENDARY));
    }

    // the number of items of each rarity can be divided to 4, since I've set 4 types of items - Shield, Weapon, Armor and Relic
    private void fillListByDefaultItems(String name, Item.Rarity rarity) {
        for (int i = 0; i < rarityWeights.get(rarity) / 4; i++) {
            itemList.add(new Item(name, rarity));
        }
    }

    private void initializeItems() {
        items = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            int index =  random.nextInt(itemList.size());
            Item chosen = itemList.get(index);
            itemList.remove(index);
            System.out.println("You got: " + chosen);
            System.out.println(Display.getRarityMessage(chosen.getRarity()));
            items.add(chosen);
        }
    }
}