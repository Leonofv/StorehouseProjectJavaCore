package ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesDbImple;

import ru.vsu.cs.java.storehouse.daoRepositories.*;
import ru.vsu.cs.java.storehouse.dbConnection.ConnectorFactory;

public class DBRepositoriesProvider implements DaoProvider {
    private ProductsRepositoryDBImpl productsRepository;
    private ShippersRepositoryDBImpl shippersRepository;
    private WarehousesDao warehousesRepository;

    public DBRepositoriesProvider(ConnectorFactory connectorFactory) {
        productsRepository = new ProductsRepositoryDBImpl(connectorFactory);
        shippersRepository = new ShippersRepositoryDBImpl(connectorFactory);
        warehousesRepository = new WarehousesRepositoryDBImpl(connectorFactory);
    }

    @Override
    public ProductsDao getProductRepository() {
        return productsRepository;
    }

    @Override
    public ShippersDao getShippersRepository() {
        return shippersRepository;
    }

    @Override
    public WarehousesDao getWarehousesRepository() {
        return warehousesRepository;
    }

}
