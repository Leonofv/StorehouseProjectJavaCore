package ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.providers;

import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.ProductsController;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.ShippersController;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.WarehousesController;

public interface AbstractControllersProvider {
    ProductsController getProductsController();
    ShippersController getShippersController();
    WarehousesController getWarehousesController();
}
