package org.example.parserapp.model;

import java.util.ArrayList;

public class Tile {

    public String tileName;
    public ArrayList<String> tileInfo;

    public Tile() {
        tileInfo = new ArrayList<>();
    }

    public String getTileName() {
        return tileName;
    }

    public ArrayList<String> getTileInfo() {
        return tileInfo;
    }

}
