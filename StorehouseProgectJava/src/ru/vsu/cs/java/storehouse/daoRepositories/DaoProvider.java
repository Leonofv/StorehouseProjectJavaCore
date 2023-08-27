package ru.vsu.cs.java.storehouse.daoRepositories;

public interface DaoProvider {
    ProductsDao getProductRepository();
    ShippersDao getShippersRepository();
    WarehousesDao getWarehousesRepository();
}
