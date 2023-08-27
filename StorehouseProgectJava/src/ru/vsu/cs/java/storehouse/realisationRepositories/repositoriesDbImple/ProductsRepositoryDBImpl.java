package ru.vsu.cs.java.storehouse.realisationRepositories.repositoriesDbImple;

import ru.vsu.cs.java.storehouse.models.Product;
import ru.vsu.cs.java.storehouse.daoRepositories.ProductsDao;
import ru.vsu.cs.java.storehouse.dbConnection.ConnectorFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepositoryDBImpl implements ProductsDao {

    private final ConnectorFactory factory;
    private static  final  String TABLE_NAME = "\"Products\"";
    private static  final  String PRODUCT_ID = "\"product_id\"";
    private static  final  String TITLE = "\"title\"";
    private static final   String WAREHOUSE_ID = "\"warehouse_id\"";
    private static  final  String PRICE = "\"price\"";
    private static  final  String QUANTITY = "\"quantity\"";
    private static  final  String DELIVERY = "\"dateOfDelivery\"";
    private static  final  String DESCRIPTION = "\"description\"";
    private static  final  String MANUFACTURER = "\"manufacturer\"";

    public ProductsRepositoryDBImpl(ConnectorFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean create(Product entity) {
        final String query = String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)",
                TABLE_NAME, TITLE, WAREHOUSE_ID, PRICE, QUANTITY, DELIVERY, DESCRIPTION, MANUFACTURER);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, entity.title);

            if (entity.warehouseId == null) {
                ps.setString(2, null);
            } else {
                ps.setInt(2, entity.warehouseId);
            }
            ps.setInt(3, entity.price);
            ps.setInt(4, entity.quantity);
            ps.setString(5, entity.dateOfDelivery);
            ps.setString(6, entity.description);
            ps.setString(7, entity.manufacturer);
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Product entity) {
        final String query = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                TABLE_NAME, TITLE, WAREHOUSE_ID, PRICE, QUANTITY, DELIVERY, DESCRIPTION, MANUFACTURER, PRODUCT_ID);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, entity.title);
            ps.setInt(2, entity.warehouseId);
            ps.setInt(3, entity.price);
            ps.setInt(4, entity.quantity);
            ps.setString(5, entity.dateOfDelivery);
            ps.setString(6, entity.description);
            ps.setString(7, entity.manufacturer);
            ps.setLong(8, entity.productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean delete(Product entity) {
        final String query = String.format("DELETE FROM %s WHERE %s = ?",
                TABLE_NAME, PRODUCT_ID);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, entity.productId);
            return ps.executeUpdate() > 1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }


    @Override
    public Product get(Integer productId) {
        List<Product> answer = new ArrayList<>();
        final String query =
                String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s FROM %s WHERE %s = ?",
                        PRODUCT_ID, TITLE, WAREHOUSE_ID, PRICE, QUANTITY, DELIVERY, DESCRIPTION, MANUFACTURER, TABLE_NAME, PRODUCT_ID);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productId);
            ResultSet r = ps.executeQuery();
                while (r.next()) {
                    Integer product_id = r.getInt(1);
                    String title = r.getString(2);
                    int warehouse_id = r.getInt(3);
                    int price = r.getInt(4);
                    int quantity = r.getInt(5);
                    String delivery = r.getString(6);
                    String description = r.getString(7);
                    String manufacturer = r.getString(8);
                    answer.add(new Product(product_id,title,warehouse_id,price,quantity,delivery,description,manufacturer));
                }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        if (answer.size() != 1)
            return null;
        return answer.get(0);
    }

    @Override
    public List<Product> get(String name) {
        List<Product> answer = new ArrayList<>();
        final String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s FROM %s WHERE %s LIKE ?",
                PRODUCT_ID, TITLE, WAREHOUSE_ID, PRICE, QUANTITY, DELIVERY, DESCRIPTION, MANUFACTURER, TABLE_NAME, TITLE);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ResultSet r = ps.executeQuery();
                while (r.next()) {
                    Integer product_id = r.getInt(1);
                    String title = r.getString(2);
                    int warehouse_id = r.getInt(3);
                    int price = r.getInt(4);
                    int quantity = r.getInt(5);
                    String delivery = r.getString(6);
                    String description = r.getString(7);
                    String manufacturer = r.getString(8);
                    answer.add(new Product(product_id,title,warehouse_id ,price,quantity,delivery,description,manufacturer));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Product> getAll() {
        List<Product> answer = new ArrayList<>();
        final String query =
                String.format("SELECT %s, %s, %s, %s, %s, %s, $s, $s FROM %s",
                PRODUCT_ID, TITLE, WAREHOUSE_ID, PRICE, QUANTITY, DELIVERY, DESCRIPTION, MANUFACTURER, TABLE_NAME);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet r = ps.executeQuery();
                while (r.next()) {
                    Integer product_id = r.getInt(1);
                    String title = r.getString(2);
                    int warehouse_id = r.getInt(3);
                    int price = r.getInt(4);
                    int quantity = r.getInt(5);
                    String delivery = r.getString(6);
                    String description = r.getString(7);
                    String manufacturer = r.getString(8);
                    answer.add(new Product(product_id,title, warehouse_id,price,quantity,delivery,description,manufacturer));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> answer = new ArrayList<>();
        final String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s FROM %s",
                PRODUCT_ID, TITLE, WAREHOUSE_ID, PRICE, QUANTITY, DELIVERY, DESCRIPTION, MANUFACTURER, TABLE_NAME);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet r = ps.executeQuery();
            if (!r.isAfterLast()) {
                while (r.next()) {
                    Integer product_id = r.getInt(1);
                    String title = r.getString(2);
                    int warehouse_id = r.getInt(3);
                    int price = r.getInt(4);
                    int quantity = r.getInt(5);
                    String delivery = r.getString(6);
                    String description = r.getString(7);
                    String manufacturer = r.getString(8);
                    answer.add(new Product(product_id,title,warehouse_id,price,quantity,delivery,description,manufacturer));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Product> find(String name) {
        List<Product> answer = new ArrayList<>();
        final String query = String.format("SELECT %s, %s, %s, %s, %s, %s, %s, %s FROM %s WHERE %s LIKE ?",
                             PRODUCT_ID, TITLE, WAREHOUSE_ID, PRICE, QUANTITY, DELIVERY, DESCRIPTION, MANUFACTURER, TABLE_NAME, TITLE);
        try (Connection connection = factory.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ResultSet r = ps.executeQuery();
                while (r.next()) {
                    Integer product_id = r.getInt(1);
                    String title = r.getString(2);
                    int warehouse_id = r.getInt(3);
                    int price = r.getInt(4);
                    int quantity = r.getInt(5);
                    String delivery = r.getString(6);
                    String description = r.getString(7);
                    String manufacturer = r.getString(8);
                    answer.add(new Product(product_id,title, warehouse_id,price,quantity,delivery,description,manufacturer));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return answer;
    }
}
