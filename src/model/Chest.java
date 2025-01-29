package model;

import java.util.*;

public class Chest {
    private final int dropCount; // Number of items to drop
    private final int weight; // Weight of this chest for probability
    // the list of items chosen randomly
    private static List<Item> items = new ArrayList<>();
    private static final Random random = new Random();
    // fixed item names
    private static final List<String> itemNames = Arrays.asList("Shield", "Armor", "Relic", "Weapon");
    // maps rarity to weights (probability of an item rarity in the chest)
    private final Map<Rarity, Integer> rarityWeights = new HashMap<>();
    // list of all available chests
    private static final List<Chest> chests = new ArrayList<>();
    // weights for item rarities within the chest
    private final int[] itemWeights;

    // initialize chests once when the class is loaded
    static {
        initChest();
    }

    // constructor, that gets the count of items that the user can pick. Each time it initializes items, rarityWeights and itemList
    private Chest(int weight, int dropCount, int[] itemWeights) {
        this.itemWeights = itemWeights;
        initializeRarityWeights();
        this.weight = weight;
        this.dropCount = dropCount;
    }

    public List<Item> getItems() {
        return items;
    }

    // a method that initializes 5 types of chests, each with different properties.
    private static void initChest() {
        Chest chestCommon = new Chest(50, 5, new int[]{80, 20, 0, 0, 0});
        chests.add(chestCommon);

        Chest chestGreat = new Chest(25, 6, new int[]{60, 25, 15, 0, 0});
        chests.add(chestGreat);

        Chest chestRare = new Chest(15, 7, new int[]{0, 50, 35, 15, 0});
        chests.add(chestRare);

        Chest chestEpic = new Chest(8, 8, new int[]{0, 0, 50, 40, 10});
        chests.add(chestEpic);

        Chest chestLegendary = new Chest(2, 10, new int[]{0, 0, 50, 30, 20});
        chests.add(chestLegendary);
    }

    // initializing the weighs of items by rarity
    private void initializeRarityWeights() {
        rarityWeights.put(Rarity.COMMON, itemWeights[0]);
        rarityWeights.put(Rarity.GREAT, itemWeights[1]);
        rarityWeights.put(Rarity.RARE, itemWeights[2]);
        rarityWeights.put(Rarity.EPIC, itemWeights[3]);
        rarityWeights.put(Rarity.LEGENDARY, itemWeights[4]);
    }

    // method to get a random chest based on their weights.
    private static Chest getRandomChest() {
        int totalWeight = chests.stream().mapToInt(chest -> chest.weight).sum();
        int randomValue = random.nextInt(totalWeight + 1);

        int cumulativeWeight = 0;
        for (Chest chest : chests) {
            cumulativeWeight += chest.weight;
            if (randomValue < cumulativeWeight) {
                System.out.println("The probability of getting this chest was " + chest.weight + "%!");
                return chest;
            }
        }
        // default chest
        return chests.getFirst();
    }

    // gets a random rarity based on the weight of items initialized by a specific type of chest.
    private static Rarity getRandomRarity(Chest chest) {
        int totalWeight = Arrays.stream(chest.itemWeights).sum();
        int randomValue = random.nextInt(totalWeight + 1);

        int cumulativeWeight = 0;
        for (int i = 0; i < chest.itemWeights.length; i++) {
            cumulativeWeight += chest.itemWeights[i];
            if (randomValue < cumulativeWeight) {
                return Rarity.values()[i];
            }
        }
        // default rarity
        return Rarity.COMMON;
    }

    private static String getRandomName() {
        return itemNames.get(random.nextInt(itemNames.size()));
    }

    // Create an item based on the given name and rarity
    private static Item createItem(String name, Rarity rarity) {
        return rarity == Rarity.EPIC
                ? new Item(name, rarity, random.nextInt(3))
                : new Item(name, rarity);
    }

    private static void displayChestItems() {
        System.out.println("You opened a chest! Here are your items:");
        for (Item item : items) {
            System.out.println("\u25CF " + item);
        }
    }

    public static List<Item> openChest() {
        items.clear();

        Chest chest = getRandomChest();

        for (int i = 0; i < chest.dropCount; i++) {
            Rarity itemRarity = getRandomRarity(chest);
            String itemName = getRandomName();
            items.add(createItem(itemName, itemRarity));
        }

        displayChestItems();
        return items;
    }
}