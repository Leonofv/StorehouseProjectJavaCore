package ru.vsu.cs.java.storehouse.daoRepositories.сontrollers;

import ru.vsu.cs.java.storehouse.models.Shipper;
import ru.vsu.cs.java.storehouse.daoRepositories.ShippersDao;

import java.util.List;

public class ShippersController implements ShippersInterfaceController<Shipper> {

    private ShippersDao shippersRepository;

    public ShippersController(ShippersDao repository) {
        shippersRepository = repository;
    }

    @Override
    public Shipper getShippersId(Integer id) {
        return shippersRepository.get(id);
    }

    @Override
    public void  addNewShipper(Shipper shippers) {
        shippersRepository.create(shippers);
    }

    public void updateShipper(Shipper shippers) {
        shippersRepository.update(shippers);
    }

    @Override
    public boolean removeShippersById(Integer id) {
        Shipper shippers = shippersRepository.get(id);
        if(shippers != null)
             shippersRepository.delete(shippers);
        return  shippers != null;
    }

    @Override
    public List<Shipper> getAllShippers() {
       return shippersRepository.getAllShippers();
    }


    @Override
    public List<Shipper> findShippersByName(String name) {
        return shippersRepository.get(name);
    }
}


