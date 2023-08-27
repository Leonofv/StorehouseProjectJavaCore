package ru.vsu.cs.java.storehouse.webUiServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.java.storehouse.applications.WebApplication;
import ru.vsu.cs.java.storehouse.models.Product;

import java.io.IOException;

public class ProductsInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Product products = WebApplication.
                controllers.
                getProductsController().
                getProductId(Integer.valueOf(req.getParameter("id")));

        req.setAttribute("ALL_PRODUCTS", products);
        req.getRequestDispatcher("/products/info/index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");

        if ("update".equals(action)) {

            Product upProduct = new Product(Integer.valueOf(req.getParameter("id")),
                    req.getParameter("title"),
                    Integer.parseInt(req.getParameter("warehouse_id")),
                    Integer.parseInt(req.getParameter("price")),
                    Integer.parseInt(req.getParameter("quantity")),
                    req.getParameter("dateOfDelivery"),
                    req.getParameter("description"),
                    req.getParameter("manufacturer"));


                WebApplication.controllers.getProductsController().updateProduct(upProduct);
        }
        resp.setStatus(301);
        resp.addHeader("Location", "/Web_app/products");
    }

}
