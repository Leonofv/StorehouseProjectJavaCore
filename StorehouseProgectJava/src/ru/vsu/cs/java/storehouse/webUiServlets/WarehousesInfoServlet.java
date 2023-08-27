package ru.vsu.cs.java.storehouse.webUiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.java.storehouse.applications.WebApplication;
import ru.vsu.cs.java.storehouse.models.Warehouse;

import java.io.IOException;

public class WarehousesInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Warehouse warehouses = WebApplication.
                controllers.
                getWarehousesController().
                getWarehouseId(Integer.valueOf(req.getParameter("id")));

        req.setAttribute("ALL_WAREHOUSES", warehouses);
        req.getRequestDispatcher("/warehouses/info/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");

        if ("update".equals(action)) {

            Warehouse upWarehouse = new Warehouse(Integer.valueOf(req.getParameter("id")),
                    req.getParameter("warehouseNumber"),
                    req.getParameter("warehouseAddress"),
                    req.getParameter("warehousePhone"),
                    req.getParameter("warehouseMail"));

                WebApplication.controllers.getWarehousesController().updateWarehouse(upWarehouse);
        }
        resp.setStatus(301);
        resp.addHeader("Location", "/Web_app/warehouses");
    }
}
