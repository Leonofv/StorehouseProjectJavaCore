package ru.vsu.cs.java.storehouse.applications;

import lombok.Getter;
import ru.vsu.cs.java.storehouse.dbConnection.PostgresConnectionFactory;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.providers.ControllersProviderImpl;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.ProductsController;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.ShippersController;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.WarehousesController;
import ru.vsu.cs.java.storehouse.daoRepositories.DaoProvider;
import ru.vsu.cs.java.storehouse.dbConnection.ConnectorFactory;
import ru.vsu.cs.java.storehouse.dbConnection.config.FileConfiguration;
import ru.vsu.cs.java.storehouse.dbConnection.config.FileConfigurationImpl;
import ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesDbImple.DBRepositoriesProvider;

public class WebApplication {

    @Getter
    private static final FileConfiguration fileConfiguration = new FileConfigurationImpl();

    public static final ControllersProviderImpl controllers;

    static {
    String url = WebApplication.getFileConfiguration().getProperty("db.url");
    String username = WebApplication.getFileConfiguration().getProperty("db.username");
    String password = WebApplication.getFileConfiguration().getProperty("db.password");

        ConnectorFactory connectorFactory = new PostgresConnectionFactory(url, username, password);
        DaoProvider repositoriesProvider = new DBRepositoriesProvider(connectorFactory);
        ProductsController productsController = new ProductsController(repositoriesProvider.getProductRepository(), repositoriesProvider.getWarehousesRepository());
        WarehousesController warehousesController = new WarehousesController(repositoriesProvider.getWarehousesRepository());
        ShippersController shippersController = new ShippersController(repositoriesProvider.getShippersRepository());
        controllers = new ControllersProviderImpl(productsController, shippersController, warehousesController);
    }
}
