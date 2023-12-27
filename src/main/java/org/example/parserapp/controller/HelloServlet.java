package org.example.parserapp.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.parserapp.model.Tile;
import org.example.parserapp.service.ITileService;
import org.example.parserapp.service.TileServiceDOM;
import org.example.parserapp.service.TileServiceSAX;
import org.example.parserapp.service.TileServiceStAX;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");

        int recordsPerPage = 4;
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int parserType = Integer.parseInt(request.getParameter("parserType"));
        System.out.println("Parser = " + parserType);

        ITileService tileService = new TileServiceDOM();
        switch (parserType) {
            case 2: {
                tileService = new TileServiceSAX();
            }
            break;
            case 3: {
                tileService = new TileServiceStAX();
            }
            break;
        }

        List<Tile> tiles;
        try {
            tiles = tileService.getTiles(currentPage, recordsPerPage);
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("tiles", tiles);

        int rows = tileService.getNumberOfTiles();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("tilesPerPage", recordsPerPage);
        request.setAttribute("parserType", parserType);


        RequestDispatcher dispatcher = request.getRequestDispatcher("hello.jsp");
        dispatcher.forward(request, response);

    }

}