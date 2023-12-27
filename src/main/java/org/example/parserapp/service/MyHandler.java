package org.example.service;

import org.example.model.Tile;
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

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        currentElement = qName;

        switch (currentElement) {
            case TILES_TAG: {
                tileList = new ArrayList<>();
                currentTile = new Tile();
            }
            case TILE_TAG: {
                currentTile.tileName = attributes.getValue("name");
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (Objects.equals(qName, TILE_TAG)) {
            tileList.add(currentTile);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (Objects.equals(currentElement, INFO_TAG)) {
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
