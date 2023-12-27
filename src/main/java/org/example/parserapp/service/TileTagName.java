package org.example.parserapp.service;

public enum TileTagName {
    TILE, INFO, TILES;

    public static TileTagName getElementTagName(String element) {
        switch (element) {
            case "tile":
                return TILE;
            case "info":
                return INFO;
            default:
                return TILES;
        }
    }
}
