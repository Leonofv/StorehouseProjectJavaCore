package ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesArrayImple;

import ru.vsu.cs.java.storehouse.models.Product;
import ru.vsu.cs.java.storehouse.daoRepositories.ProductsDao;

import java.util.*;

public class ProductsRepositoryArrayImpl  implements ProductsDao {

    private List<Product> data = new ArrayList<>();
    private static Integer lastId = 0;

    @Override
    public boolean create(Product entity) {
        entity.productId = ++lastId;
        data.add(entity);
        return false;
    }

    @Override
    public void update(Product entity) {
        data.set(entity.productId, entity);
    }

    @Override
    public Boolean delete(Product entity) {
        if (entity == null)
            return false;
        Product toDelete = null;
        for (Product products : data) {
            if (Objects.equals(products.productId, entity.productId))
                toDelete = products;
        }
        if (toDelete == null) {
            return false;
        }
        return data.remove(toDelete);
    }

    @Override
    public List<Product> get(String name) {
        List<Product> result = new ArrayList<>();
        for (Product products : data) {
            if (products.title.toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))) {
                result.add(products);
            }
        }
        return result;
    }

    @Override
    public List<Product> getAll() {
        return data;
    }



    @Override
    public List<Product> getAllProducts() {
        return data;
    }


    @Override
    public Product get(Integer productId) {
        Product result = null;
        for (Product product : data) {
            if (Objects.equals(product.productId, productId)) {
                result = product;
            }
        }
        if (result != null) {
            return result;
    } else {
            System.out.println("Result is null");
            return null;
        }
    }

    @Override
    public List<Product> find(String name) {
        List<Product> result = new ArrayList<>();
        for (Product products : data) {
                if (products.title.toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))) {
                    Product tmp = new Product(products);
                    result.add(tmp);
                }
        }
        return result;
    }


}
