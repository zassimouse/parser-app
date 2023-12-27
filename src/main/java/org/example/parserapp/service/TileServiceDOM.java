package org.example.parserapp.service;

import org.example.parserapp.model.Tile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileServiceDOM implements ITileService {

    NodeList nodeList;

    public void parseDocument() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("/Users/denisharitonenko/Downloads/parser-app/example.xml"));

        Element element = document.getDocumentElement();
        nodeList = element.getChildNodes();
    }

    @Override
    public List<Tile> getTiles(int currentPage, int numOfRecords) throws ParserConfigurationException, IOException, SAXException {

        parseDocument();

        int currentTileNumber = 1;
        int startTile = (currentPage - 1) * numOfRecords + 1;
        int tilesLeft = numOfRecords;

        List<Tile> result = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength() && tilesLeft > 0; i++) {
            if (nodeList.item(i) instanceof Element) {
                if (((Element) nodeList.item(i)).hasAttribute("name")) {
                    if (currentTileNumber >= startTile) {
                        Tile tmp = new Tile();
                        tmp.tileName = ((Element) nodeList.item(i)).getAttribute("name");
                        if (nodeList.item(i).hasChildNodes()) {
                            NodeList nodeList1 = nodeList.item(i).getChildNodes();
                            for (int j = 0; j < nodeList1.getLength(); j++) {
                                if (!nodeList1.item(j).getTextContent().trim().isEmpty() && !((Text) nodeList1.item(j).getFirstChild()).getData().trim().isEmpty() && !((Text) nodeList1.item(j).getFirstChild()).getData().trim().equals("\n")) {
                                    tmp.tileInfo.add(nodeList1.item(j).getTextContent());
                                }
                            }
                        }
                        result.add(tmp);
                        tilesLeft--;
                    }
                    currentTileNumber++;
                }
            }
        }
        return result;
    }

    @Override
    public int getNumberOfTiles() {
        int result = 0;
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                if (Objects.equals(((Element) nodeList.item(i)).getTagName(), "tile")) {
                    result++;
                }
            }
        }
        return result;
    }
}
