package ru.vsu.cs.java.storehouse.daoRepositories;

import ru.vsu.cs.java.storehouse.models.Product;

import java.util.List;

public interface ProductsDao extends Dao<Product> {
    List<Product> getAllProducts();
    Product get(Integer productId);
    List<Product> find(String name);

}
