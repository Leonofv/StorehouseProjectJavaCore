package ru.vsu.cs.java.storehouse.consoleUiCommands;

import ru.vsu.cs.java.storehouse.consoleUiCommands.IOAplication.IO;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.providers.ControllersProviderImpl;
import ru.vsu.cs.java.storehouse.models.Shipper;

import java.util.Arrays;
import java.util.List;


public class ShippersCommands extends CommandsGroup {
    private static final IO io  = IO.instance;

    private static final AbstractCommands addShipper = new AbstractCommands() {
        @Override
        public String getName() {
            return "Добавить поставщика";

        }

        @Override
        public void action(ControllersProviderImpl controller) {
            System.out.println("Введите наименование поставщика:");
            String shipperName = io.readNextNonEmptyLine();
            System.out.println("Введите адрес поставщика:");
            String shipperAddress = io.readNextNonEmptyLine();
            System.out.println("Введите телефонный номер поставщика:");
            String phone = io.readNextNonEmptyLine();
            System.out.println("Введите электронную почту поставщика:");
            String mail = io.readNextNonEmptyLine();
            Shipper shippers = new Shipper(shipperName, shipperAddress, phone, mail);
            controller.getShippersController().addNewShipper(shippers);
        }
    };

    private static final AbstractCommands deleteShipper = new AbstractCommands() {

        @Override
        public String getName() {
            return "Удалить поставщика";
        }

        @Override
        public void action(ControllersProviderImpl controller) {
            System.out.println("Введите часть названия удаляемого поставщика:");
            String name = io.readNextNonEmptyLine();
            List<Shipper> toDelete = controller.getShippersController().findShippersByName(name);
            int i = 1;
            System.out.println("Выберите продукт для удаления или 0 для отмены:");
            for (Shipper shippers : toDelete) {
                System.out.printf("%2d. %s\n", i, shippers.shipperName);
                i++;
            }
            int deleteChoice = io.readNextInt();
            if (deleteChoice == 0) {
                System.out.println("Отмена операции удаления.");
            } else if (deleteChoice > 0 && deleteChoice < i) {
                if (controller.getShippersController().removeShippersById(toDelete.get(deleteChoice - 1).shipperId)) {
                    System.out.println("Успешно удалено.");
                } else {
                    System.out.println("Не получилось удалить.");
                }
            } else {
                System.out.println("Ошибка удаления. Неверный номер.");
            }
        }
    };

    private static final AbstractCommands updateShipper = new AbstractCommands(){
        @Override
        public String getName() {
            return "Обновить постовщика";
        }

        @Override
        public void action(ControllersProviderImpl controllersProvider) {
            System.out.println("Введите часть наименования поставщика: ");
            String title = io.readNextNonEmptyLine();
            List<Shipper> toUpdate = controllersProvider.getShippersController().findShippersByName(title);
            int i = 1;
            System.out.println("Выберите постовщика для обновления или 0 для отмены:");
            for (Shipper shippers : toUpdate) {
                System.out.printf("%2d. %s\n", i, shippers.shipperName);
                i++;
            }
            int updateChoice = io.readNextInt();
            if (updateChoice == 0) {
                System.out.println("Отмена операции обновления");
            } else if (updateChoice > 0 && updateChoice < i) {
                Shipper shippersUp = toUpdate.get(updateChoice - 1);
                boolean tempBool = true;
                while (tempBool) {
                    tempBool = false;
                    System.out.println("Наименование постовщика: " + shippersUp.shipperName);
                    System.out.println("Телефонный номер поставщика: " + shippersUp.phone);
                    System.out.println("Адрес поставщика: " + shippersUp.shipperAddress);

                    System.out.println("Выберите пункт для обновления или 0 для отмены:");
                    int k = 1;
                    System.out.printf("%2d. %s\n", k, "Наименование постовщика");
                    k++;
                    System.out.printf("%2d. %s\n", k, "Телефонный номер");
                    k++;
                    System.out.printf("%2d. %s\n", k, "Адрес");

                    System.out.println("> ");
                    int pointChoice = io.readNextInt();

                    switch (pointChoice) {
                        case 0:
                            break;
                        case 1: {
                            System.out.println("Введите название: ");
                            String newTitle = io.readNextNonEmptyLine();
                            controllersProvider.getShippersController().updateShipper(shippersUp);
                        }
                        break;
                        case 2: {
                            String newPhone = io.readNextNonEmptyLine();
                            controllersProvider.getShippersController().updateShipper(shippersUp);
                            break;
                        }
                        case 3: {
                            System.out.println("Введите: ");
                            String newAddress = io.readNextNonEmptyLine();
                            controllersProvider.getShippersController().updateShipper(shippersUp);
                        }
                        break;
                        default: {
                            System.out.println("Ошибочная команда!");
                        } break;
                    }
                }
            }
        }

    };

    private static final AbstractCommands allShipper = new AbstractCommands(){

        @Override
        public String getName() {
            return "Список всех поставщиков";
        }

        @Override
        public void action(ControllersProviderImpl controller) {
            List<Shipper> shipper = controller.getShippersController().getAllShippers();
            int i = 1;
            for (Shipper shippers : shipper) {
                System.out.printf("%2d. %s\n", i, shippers.shipperName);
                i++;
            }
        }
    };

    public ShippersCommands() {
        super(Arrays.asList(addShipper,deleteShipper,allShipper,updateShipper));

    }
    @Override
    public String getName() {
        return "Работа с поставщиками";
    }
}
