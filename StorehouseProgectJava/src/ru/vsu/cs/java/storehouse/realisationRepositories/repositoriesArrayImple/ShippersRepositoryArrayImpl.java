package ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesArrayImple;

import ru.vsu.cs.java.storehouse.models.Shipper;
import ru.vsu.cs.java.storehouse.daoRepositories.ShippersDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ShippersRepositoryArrayImpl implements ShippersDao {
    private List<Shipper> data = new ArrayList<>();

    private static Integer lastId = 0;

    @Override
    public boolean create(Shipper entity) {
        entity.shipperId = lastId++;
        data.add(entity);
        return false;
    }

    @Override
    public void update(Shipper entity) {
        data.set(entity.shipperId, entity);
    }

    @Override
    public Boolean delete(Shipper entity) {
        if (entity == null)
            return false;
        Shipper toDelete = null;
        for (Shipper shippers : data) {
            if (shippers.shipperId == entity.shipperId)
                toDelete = shippers;
        }
        if (toDelete == null) {
            return false;
        }
        return data.remove(toDelete);
    }

    @Override
    public List<Shipper> get(String name) {
        List<Shipper> result = new ArrayList<>();
        for (Shipper shippers : data) {
            if (shippers.shipperName.toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))) {
                result.add(shippers);
            }
        }
        return result;
    }

    @Override
    public List<Shipper> getAll() {
        return data;
    }

    @Override
    public Shipper get(Integer shipperId) {
        Shipper result = null;
        for (Shipper shipper : data) {
            if (Objects.equals(shipper.shipperId, shipperId)) {
                result = shipper;
            }
        }
        if (result != null) {
            return result;
        } else {
            System.out.println("Result is null");
            return null;
        }
    }

    @Override
    public List<Shipper> getAllShippers() {
        return data;
    }

    @Override
    public List<Shipper> find(String name) {
        List<Shipper> result = new ArrayList<>();
        for (Shipper shippers : data) {
                if (shippers.shipperName.toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))) {
                    Shipper tmp = new Shipper(shippers);
                    result.add(tmp);
                }

        }
        return result;
    }
}
