package ru.vsu.cs.java.storehouse.models;

public class Shipper {

    public Integer shipperId;
    public String shipperName;
    public String shipperAddress;
    public String phone;
    public String mail;

    public Shipper(String shipperName, String shipperAddress, String phone, String mail) {
        this.shipperName = shipperName;
        this.shipperAddress = shipperAddress;
        this.phone = phone;
        this.mail = mail;
    }

    public Shipper(Integer shipperId, String shipperName, String shipperAddress, String phone, String mail) {
        this.shipperId = shipperId;
        this.shipperName = shipperName;
        this.shipperAddress = shipperAddress;
        this.phone = phone;
        this.mail = mail;
    }

    public Shipper(Shipper other) {
        this(other.shipperId,other.shipperName, other.shipperAddress, other.phone, other.mail);
    }

}
