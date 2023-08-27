package ru.vsu.cs.java.storehouse.models;

public class Warehouse {
    public Integer warehouseId;
    public String warehouseNumber;
    public String warehouseAddress;
    public String warehousePhone;
    public String warehouseMail;

    public Warehouse(String warehouseNumber, String warehouseAddress, String warehousePhone, String warehouseMail) {
        this.warehouseNumber = warehouseNumber;
        this.warehouseAddress = warehouseAddress;
        this.warehousePhone = warehousePhone;
        this.warehouseMail = warehouseMail;
    }

    public Warehouse(Integer warehouseId, String warehouseNumber, String warehouseAddress, String warehousePhone, String warehouseMail){
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.warehouseAddress = warehouseAddress;
        this.warehousePhone = warehousePhone;
        this.warehouseMail = warehouseMail;
    }

    public Warehouse(Warehouse other) { this
            (other.warehouseId, other.warehouseNumber, other.warehouseAddress, other.warehousePhone, other.warehouseMail);

    }
}
