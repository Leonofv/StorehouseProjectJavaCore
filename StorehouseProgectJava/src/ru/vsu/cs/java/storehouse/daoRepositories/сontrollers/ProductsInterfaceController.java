package ru.vsu.cs.java.storehouse.daoRepositories.сontrollers;

import java.util.List;

public interface ProductsInterfaceController<T> {
    T getProductId(Integer id);
    void addNewProduct(T m);
    boolean removeProductById(Integer id);
    List<T> getAllProduct();
    List<T> findProductByName(String name);
}
