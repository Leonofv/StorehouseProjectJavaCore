package ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.providers;

import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.ProductsController;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.ShippersController;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.WarehousesController;

public class ControllersProviderImpl implements AbstractControllersProvider {

    private final ProductsController productsController;
    private final ShippersController shippersController;
    private final WarehousesController warehousesController;

    public ControllersProviderImpl(ProductsController productsController, ShippersController shippersController, WarehousesController warehousesController) {
         this.shippersController = shippersController;
         this.productsController = productsController;
         this.warehousesController = warehousesController;
    }
    @Override
    public ProductsController getProductsController() {
        return productsController;
    }

    @Override
    public ShippersController getShippersController() {
        return shippersController;
    }

    @Override
    public WarehousesController getWarehousesController() {
        return warehousesController;
    }
}
