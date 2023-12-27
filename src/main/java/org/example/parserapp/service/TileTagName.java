package org.example.service;

public enum TileTagName {
    TILE, INFO, TILES;

    public static TileTagName getElementTagName(String element) {
        return switch (element) {
            case "tile" -> TILE;
            case "info" -> INFO;
            case "tiles" -> TILES;
            default -> TILES;
        };
    }
}
