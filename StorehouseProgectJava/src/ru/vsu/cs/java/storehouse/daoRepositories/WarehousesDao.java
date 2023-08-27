package ru.vsu.cs.java.storehouse.daoRepositories;

import ru.vsu.cs.java.storehouse.models.Warehouse;

import java.util.List;

public interface WarehousesDao extends Dao<Warehouse> {
    List<Warehouse> getAllWarehouses();
    Warehouse get(Integer warehouseId);
    List<Warehouse> find(String name);
}
