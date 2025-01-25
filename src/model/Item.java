package model;

import java.util.Objects;

public class Item {
    private String name;

    public enum Rarity {
        COMMON, GREAT, RARE, EPIC, LEGENDARY
    }

    private Rarity rarity;
    // Upgrade counts. Changes only in the case of EPIC item.
    private int upgradeCount;

    // constructor for items from common to epic.
    public Item(String name, Rarity rarity) {
        this.name = name;
        this.rarity = rarity;
        this.upgradeCount = 0;
    }

    // constructor for epic item.
    public Item(String name, Rarity rarity, int upgradeCount) throws IllegalArgumentException {
        this(name, rarity);
        if (rarity != Rarity.EPIC) {
            throw new IllegalArgumentException("Only epic items can have an upgrade count.");
        }
        if (upgradeCount < 0 || upgradeCount > 2) {
            throw new IllegalArgumentException("Invalid upgrade count for epic item.");
        }
        this.upgradeCount = upgradeCount;
    }

    // getters
    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public int getUpgradeCount() {
        return upgradeCount;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public void setUpgradeCount(int upgradeCount) throws IllegalArgumentException {
        if (getRarity() != Rarity.EPIC && upgradeCount != 0) {
            throw new IllegalArgumentException("This type of item does not have an upgrade count.");
        }
        if (upgradeCount < 0 || upgradeCount > 2) {
            throw new IllegalArgumentException("Invalid upgrade count for epic item.");
        }
        this.upgradeCount = upgradeCount;
    }

    public void incrementUpgradeCount() {
        setUpgradeCount(getUpgradeCount() + 1);
    }

    public void upgradeRarity() {
        setRarity(nextRarity());
    }

    public Rarity nextRarity() {
        return switch (getRarity()) {
            case Rarity.COMMON -> Rarity.GREAT;
            case Rarity.GREAT -> Rarity.RARE;
            case Rarity.RARE -> Rarity.EPIC;
            case Rarity.EPIC -> Rarity.LEGENDARY;
            default -> null;
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item objItem = (Item) obj;
        return this.getName().equals(objItem.getName()) && this.getRarity() == objItem.getRarity() && this.getUpgradeCount() == objItem.getUpgradeCount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rarity, upgradeCount);
    }

    @Override
    public String toString() {
        return "\u001B[33;1mName: \u001B[0m" + getName() + " \u001B[33;1mRarity: \u001B[0m" + getRarity() + " \u001B[33;1mUpgrade lvl: \u001B[0m" + getUpgradeCount();
    }
}