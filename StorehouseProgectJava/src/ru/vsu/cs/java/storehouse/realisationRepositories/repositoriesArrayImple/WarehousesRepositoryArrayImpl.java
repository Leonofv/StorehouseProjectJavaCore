package ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesArrayImple;

import ru.vsu.cs.java.storehouse.models.Warehouse;
import ru.vsu.cs.java.storehouse.daoRepositories.WarehousesDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class WarehousesRepositoryArrayImpl implements WarehousesDao {

    private List<Warehouse> data = new ArrayList<>();
    private static Integer lastId = 0;

    @Override
    public List<Warehouse> find(String name) {
        List<Warehouse> result = new ArrayList<>();
        for (Warehouse warehouses : data) {
                if (warehouses.warehouseNumber.toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))) {
                    Warehouse tmp = new Warehouse(warehouses);
                    result.add(tmp);
                }
        }
        return result;
    }

    @Override
    public boolean create(Warehouse entity) {
        entity.warehouseId = ++lastId;
        data.add(entity);
        return false;
    }

    @Override
    public void update(Warehouse entity) {
        data.set(entity.warehouseId, entity);
    }

    @Override
    public Boolean delete(Warehouse entity) {
        if (entity == null)
            return false;
        Warehouse toDelete = null;
        for (Warehouse warehouses : data) {
            if (warehouses.warehouseId == entity.warehouseId)
                toDelete = warehouses;
        }
        if (toDelete == null) {
            return false;
        }
        return data.remove(toDelete);
    }

    @Override
    public List<Warehouse> get(String name) {
        List<Warehouse> result = new ArrayList<>();
        for (Warehouse warehouses : data) {
            if (warehouses.warehouseNumber.toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))) {
                result.add(warehouses);
            }
        }
        return result;
    }

    @Override
    public List<Warehouse> getAll() {
        return data;
    }


    @Override
    public List<Warehouse> getAllWarehouses() {
        return data;
    }


    @Override
    public Warehouse get(Integer warehouseId) {
        Warehouse result  = null;
        for (Warehouse  warehouse: data) {
            if (Objects.equals(warehouse.warehouseId, warehouseId)) {
                result = warehouse;
            }
        }
        if (result != null) {
            return result;
        } else {
            System.out.println("Result is null");
            return null;
        }
    }

}

