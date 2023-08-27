package ru.vsu.cs.java.storehouse.models;

public class Product {
    public Integer productId;
    public String title;
    public Integer warehouseId;
    public Integer price;
    public Integer quantity;
    public String dateOfDelivery ;
    public String description;
    public String manufacturer;

    public Product(String productName, int quantity, int price, String dateOfDelivery , String description, String manufacturer) {
        this.title = productName;
        this.quantity = quantity;
        this.price = price;
        this.dateOfDelivery  = dateOfDelivery;
        this.manufacturer = manufacturer;
        this.description = description;
    }

    public Product(Integer productId, String title, Integer warehouseId, int price, int quantity, String dateOfDelivery, String description, String manufacturer) {
        this.productId = productId;
        this.title = title;
        this.warehouseId = warehouseId;
        this.price = price;
        this.quantity = quantity;
        this.dateOfDelivery  = dateOfDelivery;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Product(Product other) {
        this(other.productId,other.title, other.warehouseId, other.price, other.quantity, other.dateOfDelivery, other.description, other.manufacturer);
    }
}
