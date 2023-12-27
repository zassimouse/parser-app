package org.example.parserapp.service;

import org.example.parserapp.model.Tile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface ITileService {
    List<Tile> getTiles(int currentPage, int numOfRecords) throws ParserConfigurationException, SAXException, IOException;
    int getNumberOfTiles();


}
