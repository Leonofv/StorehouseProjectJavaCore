package ru.vsu.cs.java.storehouse.webUiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.java.storehouse.applications.WebApplication;
import ru.vsu.cs.java.storehouse.models.Warehouse;

import java.io.IOException;
import java.util.List;

public class WarehousesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Warehouse> allWarehouses = WebApplication.controllers.getWarehousesController().getAllWarehouses();
        req.setAttribute("ALL_WAREHOUSES", allWarehouses);
        req.getRequestDispatcher("/warehouses/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");

        if ("remove".equals(action)) {
            String idS = req.getParameter("id");
            WebApplication.controllers.getWarehousesController().removeWarehousesById((int) Long.parseLong(idS));
        }
        if ("create".equals(action)) {
            String warehouseNumber = req.getParameter("warehouseNumber");
            String warehouseAddress = req.getParameter("warehouseAddress");
            String warehousePhone = req.getParameter("warehousePhone");
            String warehouseMail = req.getParameter("warehouseMail");

            WebApplication.
                    controllers.
                    getWarehousesController().
                    addNewWarehouse(new Warehouse(warehouseNumber,warehouseAddress,warehousePhone,warehouseMail));
        }
        resp.setStatus(301);
        resp.addHeader("Location", "/Web_app/warehouses");
    }
}
