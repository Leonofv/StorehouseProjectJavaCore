package ru.vsu.cs.java.storehouse.webUiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.java.storehouse.applications.WebApplication;
import ru.vsu.cs.java.storehouse.models.Shipper;

import java.io.IOException;

public class ShippersInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Shipper shippers = WebApplication.
                controllers.
                getShippersController().
                getShippersId(Integer.valueOf(req.getParameter("id")));

        req.setAttribute("ALL_SHIPPERS", shippers);
        req.getRequestDispatcher("/shippers/info/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");

        if ("update".equals(action)) {

            Shipper upShippers = new Shipper(Integer.valueOf(req.getParameter("id")),
                    req.getParameter("shipperName"),
                    req.getParameter("shipperAddress"),
                    req.getParameter("phone"),
                    req.getParameter("mail"));

                WebApplication.controllers.getShippersController().updateShipper(upShippers);
        }
        resp.setStatus(301);
        resp.addHeader("Location", "/Web_app/shippers");
    }

}
