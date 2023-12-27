package org.example.service;

import org.example.model.Tile;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class TileServiceStAX implements ITileService {

    public int numberOfTiles;

    @Override
    public void getTiles(int currentPage, int numOfRecords) throws XMLStreamException {

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            InputStream input = new FileInputStream("example.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            ArrayList<Tile> tiles = process(reader);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }




    }

    private ArrayList<Tile> process(XMLStreamReader reader) throws XMLStreamException {
        numberOfTiles = 0;
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        Tile tile = null;
        TileTagName elementName = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT: {
                    elementName = TileTagName.getElementTagName(reader.getLocalName());
                    if (Objects.requireNonNull(elementName) == TileTagName.TILE) {
                        tile = new Tile();
                        tile.tileName = reader.getAttributeValue(null, "name");
                        System.out.println(tile.tileName);
                        numberOfTiles++;
                    }
                }
                break;
                case XMLStreamConstants.CHARACTERS: {
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    if (Objects.requireNonNull(elementName) == TileTagName.INFO) {
                        tile.tileInfo.add(text);
                        System.out.println(tile.tileInfo);
                    }
                }
                break;
                case XMLStreamConstants.END_ELEMENT: {
                    elementName = TileTagName.getElementTagName(reader.getLocalName());
                    if (Objects.requireNonNull(elementName) == TileTagName.TILE) {
                        tiles.add(tile);
                    }
                }
            }
        }
        return tiles;
    }

    @Override
    public int getNumberOfTiles() {
        return numberOfTiles;
    }
}
