package ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesDbImple;

import ru.vsu.cs.java.storehouse.models.Shipper;
import ru.vsu.cs.java.storehouse.daoRepositories.ShippersDao;
import ru.vsu.cs.java.storehouse.dbConnection.ConnectorFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShippersRepositoryDBImpl implements ShippersDao {

    private final ConnectorFactory factory;
    private static  final  String TABLE_NAME = "\"Shippers\"";
    private static  final  String SHIPPER_ID = "\"id\"";
    private static  final  String TITLE = "\"shipperName\"";
    private static  final  String ADDRESS = "\"shipperAddress\"";
    private static  final  String PHONE = "\"phone\"";
    private static  final  String MAIL = "\"mail\"";

    public ShippersRepositoryDBImpl(ConnectorFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean create(Shipper entity) {
        final String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                TABLE_NAME, TITLE, ADDRESS, PHONE, MAIL);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, entity.shipperName);
            ps.setString(2, entity.shipperAddress);
            ps.setString(3, entity.phone);
            ps.setString(4, entity.mail);
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Shipper entity) {
        final String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                TABLE_NAME, TITLE, ADDRESS, PHONE, MAIL, SHIPPER_ID);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, entity.shipperName);
            ps.setString(2, entity.shipperAddress);
            ps.setString(3, entity.phone);
            ps.setString(4, entity.mail);
            ps.setLong(5, entity.shipperId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean delete(Shipper entity) {
            final String query = String.format("DELETE FROM %s WHERE %s = ?",
                    TABLE_NAME, SHIPPER_ID);
            try (Connection connection = factory.create()) {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setLong(1, entity.shipperId);
                return ps.executeUpdate() > 1;
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            return  false;
        }

    @Override
    public Shipper get(Integer shipperId) {
        List<Shipper> answer = new ArrayList<>();
        final String query =
                String.format("SELECT %s, %s, %s, %s, %s FROM %s WHERE %s = ?",
                        SHIPPER_ID, TITLE, ADDRESS, PHONE, MAIL, TABLE_NAME, SHIPPER_ID);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, shipperId);
            ResultSet r = ps.executeQuery();
                while (r.next()) {
                    Integer id = r.getInt(1);
                    String shipperName = r.getString(2);
                    String shipperAddress = r.getString(3);
                    String phone = r.getString(4);
                    String mail = r.getString(5);
                    answer.add(new Shipper(id, shipperName, shipperAddress, phone, mail));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        if (answer.size() != 1)
            return null;
        return answer.get(0);
    }

    @Override
    public List<Shipper> get(String name) {
        List<Shipper> answer = new ArrayList<>();
        final String query = String.format("SELECT %s, %s, %s, %s, %s FROM %s WHERE %s LIKE ?",
                SHIPPER_ID, TITLE, ADDRESS, PHONE, MAIL, TABLE_NAME, TITLE);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ResultSet r = ps.executeQuery();
            if (!r.isAfterLast()) {
                while (r.next()) {
                    Integer id = r.getInt(1);
                    String shipperName = r.getString(2);
                    String shipperAddress = r.getString(3);
                    String phone = r.getString(4);
                    String mail = r.getString(5);
                    answer.add(new Shipper(id, shipperName, shipperAddress, phone, mail));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Shipper> getAll() {
        List<Shipper> answer = new ArrayList<>();
        final String query = String.format("SELECT %s, %s, %s, %s, %s FROM %s",
                SHIPPER_ID, TITLE, ADDRESS, PHONE, MAIL, TABLE_NAME);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet r = ps.executeQuery();
                while (r.next()) {
                    Integer id = r.getInt(1);
                    String shipperName = r.getString(2);
                    String shipperAddress = r.getString(3);
                    String phone = r.getString(4);
                    String mail = r.getString(5);
                    answer.add(new Shipper(id, shipperName, shipperAddress, phone, mail));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Shipper> getAllShippers() {
        List<Shipper> answer = new ArrayList<>();
        final String query = String.format("SELECT %s, %s, %s, %s, %s FROM %s",
                SHIPPER_ID, TITLE, ADDRESS, PHONE, MAIL, TABLE_NAME);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet r = ps.executeQuery();
                while (r.next()) {
                    Integer id = r.getInt(1);
                    String shipperName = r.getString(2);
                    String shipperAddress = r.getString(3);
                    String phone = r.getString(4);
                    String mail = r.getString(5);
                    answer.add(new Shipper(id, shipperName, shipperAddress, phone, mail));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Shipper> find(String name) {
        List<Shipper> answer = new ArrayList<>();
        final String query = String.format("SELECT %s, %s, %s, %s, %s FROM %s WHERE %s LIKE ?",
                            SHIPPER_ID, TITLE, ADDRESS, PHONE, MAIL, TABLE_NAME, TITLE);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ResultSet r = ps.executeQuery();
            if (!r.isAfterLast()) {
                while (r.next()) {
                    Integer id = r.getInt(1);
                    String shipperName = r.getString(2);
                    String shipperAddress = r.getString(3);
                    String phone = r.getString(4);
                    String mail = r.getString(5);
                    answer.add(new Shipper(id, shipperName, shipperAddress, phone, mail));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return answer;
    }
}