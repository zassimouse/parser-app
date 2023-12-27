package org.example.parserapp.service;

import org.example.parserapp.model.Tile;

import java.util.List;

public interface TileService {

    List<Tile> getTiles(int currentPage, int numOfRecords);
    Integer getNumberOfRows();
}
