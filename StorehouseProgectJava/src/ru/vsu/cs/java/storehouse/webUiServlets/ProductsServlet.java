package ru.vsu.cs.java.storehouse.webUiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.java.storehouse.applications.WebApplication;
import ru.vsu.cs.java.storehouse.models.Product;

import java.io.IOException;
import java.util.List;

public class ProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> allProduct = WebApplication.
                controllers.
                getProductsController().
                getAllProduct();

        req.setAttribute("ALL_PRODUCTS", allProduct);
        req.getRequestDispatcher("/products/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");

        if ("remove".equals(action)) {
            String idS = req.getParameter("id");
            WebApplication.controllers.getProductsController().removeProductById((int) Long.parseLong(idS));
        }

        if ("create".equals(action)) {
            String title = req.getParameter("title");
            int price = Integer.parseInt(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            String dateOfDelivery = req.getParameter("dateOfDelivery");
            String descriptions = req.getParameter("description");
            String manufacturer = req.getParameter("manufacturer");
            WebApplication.
                    controllers.
                    getProductsController().
                    addNewProduct(new Product(title,price,quantity, dateOfDelivery,descriptions,manufacturer));
        }

        resp.setStatus(301);
        resp.addHeader("Location", "/Web_app/products");
    }

}