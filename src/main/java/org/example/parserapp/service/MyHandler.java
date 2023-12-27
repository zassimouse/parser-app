package org.example.parserapp.service;

import org.example.parserapp.model.Tile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyHandler extends DefaultHandler {

    public final String TILE_TAG = "tile";
    public final String TILES_TAG = "tiles";
    public final String INFO_TAG = "info";

    public String currentElement;
    public Tile currentTile;
    public List<Tile> tileList;

    public int numberOfTiles;

    public int startTile;
    public int endTile;

    @Override
    public void startDocument() throws SAXException {
        numberOfTiles = 0;
    }

    public Boolean isValid() {
        System.out.println("start tile " + startTile);
        System.out.println("end tile " + endTile);
        System.out.println("numb " + numberOfTiles + 1);

        return (numberOfTiles + 1 >= startTile) && (numberOfTiles + 1 <= endTile);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        currentElement = qName;

        switch (currentElement) {
            case TILES_TAG: {
                tileList = new ArrayList<>();
            }
            case TILE_TAG: {
                if (isValid()) {
                    currentTile = new Tile();
                    currentTile.tileName = attributes.getValue("name");
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (Objects.equals(qName, TILE_TAG)) {
            if (isValid()) {
                tileList.add(currentTile);
            }
            numberOfTiles++;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (Objects.equals(currentElement, INFO_TAG)) {
            if (isValid()) {
                String str = "";
                for (int i = 0; i < length; i++) {
                    str += ch[start + i];
                }
                if (!(str.trim().isEmpty()) && !(str.trim().equals("/n"))) {
                    System.out.println("{" + str);
                    currentTile.tileInfo.add(str);
                }
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        for (int i = 0; i < tileList.size(); i++) {
            Tile tile = tileList.get(i);
            System.out.println("name = " + tile.tileName + ", info = " + tileList);
        }
    }
}
