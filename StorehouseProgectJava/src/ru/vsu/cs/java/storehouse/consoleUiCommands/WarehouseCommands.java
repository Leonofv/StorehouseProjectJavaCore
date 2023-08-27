package ru.vsu.cs.java.storehouse.consoleUiCommands;

import ru.vsu.cs.java.storehouse.consoleUiCommands.IOAplication.IO;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.providers.ControllersProviderImpl;
import ru.vsu.cs.java.storehouse.models.Warehouse;

import java.util.Arrays;
import java.util.List;

public class WarehouseCommands extends CommandsGroup {
    private static final IO io  = IO.instance;

    private static final AbstractCommands addWarehouse = new AbstractCommands() {
        @Override
        public String getName() {
            return "Добавить склад";

        }

        @Override
        public void action(ControllersProviderImpl controller) {
            System.out.println("Введите номер склада (Склад№*):");
            String warehouseNumber = io.readNextNonEmptyLine();
            System.out.println("Введите адрес склада:");
            String warehouseAddress = io.readNextNonEmptyLine();
            System.out.println("Введите телефонный номер: ");
            String warehousePhone = io.readNextNonEmptyLine();
            System.out.println("Введите электронную почту:");
            String warehouseMail = io.readNextNonEmptyLine();
            Warehouse warehouses = new Warehouse(warehouseNumber, warehouseAddress, warehousePhone, warehouseMail);
            controller.getWarehousesController().addNewWarehouse(warehouses);
        }
    };

    private static final AbstractCommands deleteWarehouse = new AbstractCommands() {

        @Override
        public String getName() {
            return "Удалить склад";
        }

        @Override
        public void action(ControllersProviderImpl controller) {
            System.out.println("Введите полный номер удаляемого склада:");
            String name = io.readNextNonEmptyLine();
            List<Warehouse> toDelete = controller.getWarehousesController().findWarehousesByNumber(name);
            int i = 1;
            System.out.println("Выберите склад для удаления или 0 для отмены:");
            for (Warehouse warehouses : toDelete) {
                System.out.printf("%2d. %s\n", i, warehouses.warehouseNumber);
                i++;
            }
            int deleteChoice = io.readNextInt();
            if (deleteChoice == 0) {
                System.out.println("Отмена операции удаления.");
            } else if (deleteChoice > 0 && deleteChoice < i) {
                if (controller.getWarehousesController().removeWarehousesById(toDelete.get(deleteChoice - 1).warehouseId)) {
                    System.out.println("Успешно удалено.");
                } else {
                    System.out.println("Не получилось удалить.");
                }
            } else {
                System.out.println("Ошибка удаления. Неверный номер.");
            }
        }
    };

    private static AbstractCommands allWarehouses = new AbstractCommands(){

        @Override
        public String getName() {
            return "Список всех складов";
        }

        @Override
        public void action(ControllersProviderImpl controller) {
            List<Warehouse> warehouse = controller.getWarehousesController().getAllWarehouses();
            int i = 1;
            for (Warehouse warehouses : warehouse) {
                System.out.printf("%2d. %s\n", i, warehouses.warehouseNumber);
                i++;
            }
        }
    };

    public WarehouseCommands() {
        super(Arrays.asList(addWarehouse, deleteWarehouse, allWarehouses));
    }

    @Override
    public String getName() {
        return "Работа со складами";
    }

}
