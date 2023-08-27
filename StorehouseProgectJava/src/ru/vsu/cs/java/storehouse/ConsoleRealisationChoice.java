package ru.vsu.cs.java.storehouse;

import lombok.Getter;
import ru.vsu.cs.java.storehouse.applications.ConsoleApplication;
import ru.vsu.cs.java.storehouse.daoRepositories.ProductsDao;
import ru.vsu.cs.java.storehouse.daoRepositories.ShippersDao;
import ru.vsu.cs.java.storehouse.daoRepositories.WarehousesDao;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.ProductsController;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.ShippersController;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.WarehousesController;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.providers.ControllersProviderImpl;
import ru.vsu.cs.java.storehouse.dbConnection.ConnectorFactory;
import ru.vsu.cs.java.storehouse.dbConnection.PostgresConnectionFactory;
import ru.vsu.cs.java.storehouse.dbConnection.config.FileConfiguration;
import ru.vsu.cs.java.storehouse.dbConnection.config.FileConfigurationImpl;
import ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesArrayImple.ProductsRepositoryArrayImpl;
import ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesArrayImple.ShippersRepositoryArrayImpl;
import ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesArrayImple.WarehousesRepositoryArrayImpl;
import ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesDbImple.ProductsRepositoryDBImpl;
import ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesDbImple.ShippersRepositoryDBImpl;
import ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesDbImple.WarehousesRepositoryDBImpl;

import java.util.Scanner;

public class ConsoleRealisationChoice {

    public static void realisationStartChoice() {
        System.out.println("Для выбора версии приложения введите число:");
        System.out.println("1 - Array версия - 'legacy'  ");
        System.out.println("2 - DB версия - 'legacy' ");
        System.out.println("3 - Актуальная DB версия ");
        Scanner in = new Scanner(System.in);
        int versionChoice = in.nextInt();
        if (versionChoice == 1) {
            System.out.println("(legacy) Array версия запускается...");
            legacyArrayRealisationStart();
        } else if (versionChoice == 2) {
            System.out.println("(legacy) DB версия запускается...");
            legacyDbRealisationStart();
        } else {
            System.out.println("Ошибка ввода");
        }
    }

    private static void legacyArrayRealisationStart() {
        WarehousesDao warehousesRepository = new WarehousesRepositoryArrayImpl();
        WarehousesController warehousesController = new WarehousesController(warehousesRepository);

        ProductsDao productsRepository = new ProductsRepositoryArrayImpl();
        ProductsController productsController = new ProductsController(productsRepository, warehousesRepository);

        ShippersDao shippersRepository = new ShippersRepositoryArrayImpl();
        ShippersController shippersController = new ShippersController(shippersRepository);

        ControllersProviderImpl controllersProviderImpl = new ControllersProviderImpl(productsController, shippersController, warehousesController);
        ConsoleApplication consoleApplication = new ConsoleApplication(controllersProviderImpl);
        consoleApplication.start();
    }

    @Getter
    private static final FileConfiguration fileConfiguration = new FileConfigurationImpl();

    private static void legacyDbRealisationStart() {
        String url = ConsoleRealisationChoice.getFileConfiguration().getProperty("db.url");
        String username = ConsoleRealisationChoice.getFileConfiguration().getProperty("db.username");
        String password = ConsoleRealisationChoice.getFileConfiguration().getProperty("db.password");

        ConnectorFactory connectorFactory = new PostgresConnectionFactory(url, username, password);
        connectorFactory.create();

        WarehousesDao warehousesRepository = new WarehousesRepositoryDBImpl(connectorFactory);
        WarehousesController warehousesController = new WarehousesController(warehousesRepository);

        ProductsDao productsRepository = new ProductsRepositoryDBImpl(connectorFactory);
        ProductsController productsController = new ProductsController(productsRepository, warehousesRepository);

        ShippersDao shippersRepository = new ShippersRepositoryDBImpl(connectorFactory);
        ShippersController shippersController = new ShippersController(shippersRepository);

        ControllersProviderImpl controllersProviderImpl = new ControllersProviderImpl(productsController, shippersController, warehousesController);
        ConsoleApplication consoleApplication = new ConsoleApplication(controllersProviderImpl);
        consoleApplication.start();
    }
}
