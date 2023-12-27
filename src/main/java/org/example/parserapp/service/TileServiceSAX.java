package org.example.parserapp.service;

import org.example.parserapp.model.Tile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TileServiceSAX implements ITileService {

    int numberOfTiles;

    @Override
    public List<Tile> getTiles(int currentPage, int numOfRecords) throws ParserConfigurationException, SAXException, IOException {
        MyHandler handler = new MyHandler();



        handler.startTile = (currentPage - 1) * numOfRecords + 1;
        handler.endTile = (currentPage - 1) * numOfRecords + numOfRecords;


        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("/Users/denisharitonenko/Downloads/parser-app/example.xml"), handler);
        numberOfTiles = handler.numberOfTiles;
        return handler.tileList;
    }

    @Override
    public int getNumberOfTiles() {
        return numberOfTiles;
    }


}
