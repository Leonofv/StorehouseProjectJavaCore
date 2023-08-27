package ru.vsu.cs.java.storehouse.daoRepositories.сontrollers;

import java.util.List;

public interface ShippersInterfaceController<T> {
    T getShippersId(Integer id);
    void addNewShipper(T m);
    boolean removeShippersById(Integer id);
    List<T> getAllShippers();
    List<T> findShippersByName(String name);
}
