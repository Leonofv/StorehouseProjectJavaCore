package ru.vsu.cs.java.storehouse.daoRepositories.сontrollers;

import ru.vsu.cs.java.storehouse.models.Product;
import ru.vsu.cs.java.storehouse.daoRepositories.ProductsDao;
import ru.vsu.cs.java.storehouse.daoRepositories.WarehousesDao;

import java.util.List;

public class ProductsController implements ProductsInterfaceController<Product> {
    private ProductsDao productsRepository;
    private WarehousesDao warehousesRepository;

    public ProductsController(ProductsDao repository, WarehousesDao warehousesRepository) {
        productsRepository = repository;
        this.warehousesRepository = warehousesRepository;
    }

    @Override
    public Product getProductId(Integer id) {
        return productsRepository.get(id);
    }

    @Override
    public void addNewProduct(Product product) {
        productsRepository.create(product);
    }

    public void updateProduct(Product products) {
        productsRepository.update(products);
    }

    @Override
    public boolean removeProductById(Integer id) {
        Product products = productsRepository.get(id);
        if(products != null)
            productsRepository.delete(products);
        return  products != null;
    }

    @Override
    public List<Product> getAllProduct() {
        return productsRepository.getAllProducts();
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productsRepository.get(name);
    }

}

