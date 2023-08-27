package ru.vsu.cs.java.storehouse.webUiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.java.storehouse.applications.WebApplication;
import ru.vsu.cs.java.storehouse.models.Shipper;

import java.io.IOException;
import java.util.List;

public class ShippersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Shipper> allShippers = WebApplication.controllers.getShippersController().getAllShippers();
        req.setAttribute("ALL_SHIPPERS", allShippers);
        req.getRequestDispatcher("/shippers/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");

        if ("remove".equals(action)) {
            String idS = req.getParameter("id");
            WebApplication.controllers.getShippersController().removeShippersById((int) Long.parseLong(idS));
        }
        if ("create".equals(action)) {
            String shipperName = req.getParameter("shipperName");
            String shipperAddress = req.getParameter("shipperAddress");
            String phone = req.getParameter("phone");
            String mail = req.getParameter("mail");
            WebApplication.
                    controllers.
                    getShippersController().
                    addNewShipper(new Shipper(shipperName, shipperAddress, phone, mail));
        }

        resp.setStatus(301);
        resp.addHeader("Location", "/Web_app/shippers");
    }
}
