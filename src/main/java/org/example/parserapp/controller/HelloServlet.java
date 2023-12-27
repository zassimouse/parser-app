package org.example.parserapp;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");

        // current page

        // records per page

        // service object

        // find

        // request set attribute



        getServletContext().getRequestDispatcher("/hello.jsp").forward(request, response);
    }

}