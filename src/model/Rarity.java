package model;

public enum Rarity {
    COMMON, GREAT, RARE, EPIC, LEGENDARY;

    public Rarity nextRarity() {
        return switch (this) {
            case COMMON -> GREAT;
            case GREAT -> RARE;
            case RARE -> EPIC;
            case EPIC -> LEGENDARY;
            default -> null;
        };
    }
}
