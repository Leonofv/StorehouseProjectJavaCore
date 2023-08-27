package ru.vsu.cs.java.storehouse.daoRepositories.сontrollers;

import ru.vsu.cs.java.storehouse.models.Warehouse;
import ru.vsu.cs.java.storehouse.daoRepositories.WarehousesDao;

import java.util.List;

public class WarehousesController implements WarehousesInterfaceController<Warehouse> {

   private WarehousesDao warehousesRepository;

    public WarehousesController(WarehousesDao repository) {
        warehousesRepository = repository;
    }

    @Override
    public Warehouse getWarehouseId(Integer id) {
        return warehousesRepository.get(id);
    }

    @Override
    public void addNewWarehouse(Warehouse warehouses) {
        warehousesRepository.create(warehouses);
    }

    public void updateWarehouse(Warehouse warehouses) {warehousesRepository.update(warehouses);}

    @Override
    public boolean removeWarehousesById(Integer id) {
        Warehouse warehouses = warehousesRepository.get(id);
        if(warehouses != null)
            warehousesRepository.delete(warehouses);
        return  warehouses != null;
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehousesRepository.getAllWarehouses();
    }

    @Override
    public List<Warehouse> findWarehousesByNumber(String name) {
        return warehousesRepository.get(name);
    }

}

