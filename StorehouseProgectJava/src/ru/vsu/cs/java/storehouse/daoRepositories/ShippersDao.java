package ru.vsu.cs.java.storehouse.daoRepositories;

import ru.vsu.cs.java.storehouse.models.Shipper;

import java.util.List;

public interface ShippersDao extends Dao<Shipper> {
    Shipper get(Integer shipperId);
    List<Shipper> getAllShippers();
    List<Shipper> find(String name);
}
