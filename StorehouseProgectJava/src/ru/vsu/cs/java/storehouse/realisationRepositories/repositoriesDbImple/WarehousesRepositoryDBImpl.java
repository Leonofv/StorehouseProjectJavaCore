package ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesDbImple;

import ru.vsu.cs.java.storehouse.models.Warehouse;
import ru.vsu.cs.java.storehouse.daoRepositories.WarehousesDao;
import ru.vsu.cs.java.storehouse.dbConnection.ConnectorFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehousesRepositoryDBImpl implements WarehousesDao {

    private final ConnectorFactory factory;
    private static  final  String TABLE_NAME = "\"Warehouses\"";
    private static  final  String WAREHOUSE_ID = "\"warehouse_id\"";
    private static  final  String WAREHOUSE_NUMBER = "\"warehouseNumber\"";
    private static  final  String ADDRESS = "\"address\"";
    private static  final  String PHONE = "\"phone\"";
    private static  final  String MAIL = "\"mail\"";

    public WarehousesRepositoryDBImpl(ConnectorFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean create(Warehouse entity) {
        final String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ( ?, ?, ?, ?)",
                TABLE_NAME, WAREHOUSE_NUMBER, ADDRESS, PHONE, MAIL);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, entity.warehouseNumber);
            ps.setString(2, entity.warehouseAddress);
            ps.setString(3, entity.warehousePhone);
            ps.setString(4, entity.warehouseMail);
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Warehouse entity) {
        final String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                TABLE_NAME, WAREHOUSE_NUMBER, WAREHOUSE_ID, ADDRESS, PHONE, MAIL,  WAREHOUSE_ID);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, entity.warehouseNumber);
            ps.setInt(2, entity.warehouseId);
            ps.setString(3, entity.warehouseAddress);
            ps.setString(4, entity.warehousePhone);
            ps.setString(5, entity.warehouseMail);
            ps.setLong(6, entity.warehouseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean delete(Warehouse entity) {
        final String query = String.format("DELETE FROM %s WHERE %s = ?",
                TABLE_NAME, WAREHOUSE_ID);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, entity.warehouseId);
            return ps.executeUpdate() > 0;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Warehouse> get(String name) {
        List<Warehouse> answer = new ArrayList<>();
        final String query =
                String.format("SELECT %s, %s, %s, %s, %s FROM %s WHERE %s LIKE ?",
                        WAREHOUSE_ID, WAREHOUSE_NUMBER, ADDRESS, PHONE, MAIL, TABLE_NAME, WAREHOUSE_NUMBER);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ResultSet r = ps.executeQuery();
            if (!r.isAfterLast()) {
                while (r.next()) {
                    Integer warehouse_Id = r.getInt(1);
                    String warehouseNumber = r.getString(2);
                    String warehouseAddress = r.getString(3);
                    String warehousePhone = r.getString(4);
                    String warehouseMail = r.getString(5);
                    answer.add(new Warehouse(warehouse_Id, warehouseNumber,warehouseAddress,warehousePhone,warehouseMail));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Warehouse> getAll() {
        List<Warehouse> answer = new ArrayList<>();
        final String query =
                String.format("SELECT %s, %s, %s, %s, %sFROM %s WHERE %s LIKE ?",
                        WAREHOUSE_ID, WAREHOUSE_NUMBER, ADDRESS, PHONE, MAIL, TABLE_NAME, WAREHOUSE_NUMBER);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet r = ps.executeQuery();
                while (r.next()) {
                    Integer warehouse_Id = r.getInt(1);
                    String warehouseNumber = r.getString(2);
                    String warehouseAddress = r.getString(3);
                    String warehousePhone = r.getString(4);
                    String warehouseMail = r.getString(5);
                    answer.add(new Warehouse(warehouse_Id, warehouseNumber,warehouseAddress,warehousePhone,warehouseMail));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return answer;
    }

    @Override
    public Warehouse get(Integer warehouseId) {
        List<Warehouse> answer = new ArrayList<>();
        final String query =
                String.format("SELECT %s, %s, %s, %s, %s FROM %s WHERE %s = ?",
                        WAREHOUSE_ID, WAREHOUSE_NUMBER, ADDRESS, PHONE, MAIL, TABLE_NAME, WAREHOUSE_ID);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, warehouseId);
            ResultSet r = ps.executeQuery();
                while (r.next()) {
                    Integer warehouse_id = r.getInt(1);
                    String warehouseNumber = r.getString(2);
                    String warehouseAddress = r.getString(3);
                    String warehousePhone = r.getString(4);
                    String warehouseMail = r.getString(5);
                    answer.add(new Warehouse(warehouse_id, warehouseNumber,warehouseAddress,warehousePhone,warehouseMail));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        if (answer.size() != 1)
            return null;
        return answer.get(0);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        List<Warehouse> answer = new ArrayList<>();
        final String query = String.format("SELECT %s, %s, %s, %s, %sFROM %s",
                WAREHOUSE_ID, WAREHOUSE_NUMBER, ADDRESS, PHONE, MAIL, TABLE_NAME);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet r = ps.executeQuery();
                while (r.next()) {
                    Integer warehouse_id = r.getInt(1);
                    String warehouseNumber = r.getString(2);
                    String warehouseAddress = r.getString(3);
                    String warehousePhone = r.getString(4);
                    String warehouseMail = r.getString(5);
                    answer.add(new Warehouse(warehouse_id, warehouseNumber, warehouseAddress, warehousePhone, warehouseMail));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Warehouse> find(String name) {
        List<Warehouse> answer = new ArrayList<>();
        final String query = String.format("SELECT %s, %s, %s, %s, %s FROM %s WHERE %s LIKE ?",
                            WAREHOUSE_ID, WAREHOUSE_NUMBER, ADDRESS, PHONE, MAIL, TABLE_NAME, WAREHOUSE_NUMBER);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ResultSet r = ps.executeQuery();
            if (!r.isAfterLast()) {
                while (r.next()) {
                    Integer warehouse_id = r.getInt(1);
                    String warehouseNumber = r.getString(2);
                    String warehouseAddress = r.getString(3);
                    String warehousePhone = r.getString(4);
                    String warehouseMail = r.getString(5);
                    answer.add(new Warehouse(warehouse_id, warehouseNumber,warehouseAddress,warehousePhone,warehouseMail));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return answer;
    }
}
