package ru.vsu.cs.java.storehouse.daoRepositories.сontrollers;

import java.util.List;

public interface WarehousesInterfaceController<T> {
    T getWarehouseId(Integer id);
    void addNewWarehouse(T m);
    boolean removeWarehousesById(Integer id);
    List<T> getAllWarehouses();
    List<T> findWarehousesByNumber(String name);
}
