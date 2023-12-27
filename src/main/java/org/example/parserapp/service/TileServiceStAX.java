package org.example.parserapp.service;

import org.example.parserapp.model.Tile;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileServiceStAX implements ITileService {

    public int numberOfTiles;

    public int startTile;
    public int endTile;

    @Override
    public List<Tile> getTiles(int currentPage, int numOfRecords) {

        startTile = (currentPage - 1) * numOfRecords + 1;
        endTile = (currentPage - 1) * numOfRecords + numOfRecords;

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        List<Tile> tiles;
        try {
            InputStream input = new FileInputStream("/Users/denisharitonenko/Downloads/parser-app/example.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            tiles = process(reader);

        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RuntimeException(e);
        }

        return tiles;
    }

    public Boolean isValid() {
        System.out.println("start tile " + startTile);
        System.out.println("end tile " + endTile);
        System.out.println("numb " + numberOfTiles + 1);

        return (numberOfTiles + 1 >= startTile) && (numberOfTiles + 1 <= endTile);
    }

    private List<Tile> process(XMLStreamReader reader) throws XMLStreamException {
        numberOfTiles = 0;
        List<Tile> tiles = new ArrayList<>();

        Tile tile = null;
        TileTagName elementName = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT: {
                    elementName = TileTagName.getElementTagName(reader.getLocalName());
                    if (Objects.requireNonNull(elementName) == TileTagName.TILE) {
                        if (isValid()) {
                            tile = new Tile();
                            tile.tileName = reader.getAttributeValue(null, "name");
                            System.out.println(tile.tileName);
                        }
                    }
                }
                break;
                case XMLStreamConstants.CHARACTERS: {
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    if (Objects.requireNonNull(elementName) == TileTagName.INFO) {
                        if (tile != null) {
                            if (isValid()) {
                                tile.tileInfo.add(text);
                                System.out.println(tile.tileInfo);
                            }
                        }
                    }
                }
                break;
                case XMLStreamConstants.END_ELEMENT: {
                    elementName = TileTagName.getElementTagName(reader.getLocalName());
                    if (Objects.requireNonNull(elementName) == TileTagName.TILE) {
                        if (isValid()) {
                            tiles.add(tile);
                        }
                        numberOfTiles++;
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
