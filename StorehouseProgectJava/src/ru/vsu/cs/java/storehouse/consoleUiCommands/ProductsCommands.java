package ru.vsu.cs.java.storehouse.consoleUiCommands;

import ru.vsu.cs.java.storehouse.consoleUiCommands.IOAplication.IO;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.providers.ControllersProviderImpl;
import ru.vsu.cs.java.storehouse.models.Product;
import ru.vsu.cs.java.storehouse.models.Warehouse;

import java.util.Arrays;
import java.util.List;

public class ProductsCommands extends CommandsGroup {
    private static final IO io  = IO.instance;

    private static final AbstractCommands addProduct = new AbstractCommands() {
        @Override
        public String getName() {
            return "Добавить продукт";
        }

        @Override
        public void action(ControllersProviderImpl controller) {
            System.out.println("Введите название:");
            String name = io.readNextNonEmptyLine();
            System.out.println("Введите количество продукта:");
            int quantity = io.readNextInt();
            System.out.println("Введите цену за единицу товара:");
            int price = io.readNextInt();
            System.out.println("Введите дату доставки товара:");
            String dateOfDelivery = io.readNextNonEmptyLine();
            System.out.println("Введите описание товара: ");
            String description = io.readNextNonEmptyLine();
            System.out.println("Введите наименование производителя:");
            String manufacturerName = io.readNextNonEmptyLine();
            Product products = new Product(name, quantity, price, dateOfDelivery, description, manufacturerName);
            controller.getProductsController().addNewProduct(products);
        }
    };

    private static final AbstractCommands deleteProduct = new AbstractCommands() {

        @Override
        public String getName() {
            return "Удалить продукт";
        }

        @Override
        public void action(ControllersProviderImpl controller) {
            System.out.println("Введите часть названия удаляемого продукта:");
            String name = io.readNextNonEmptyLine();
            List<Product> toDelete = controller.getProductsController().findProductByName(name);
            int i = 1;
            System.out.println("Выберите продукт для удаления или 0 для отмены:");
            for (Product products : toDelete) {
                System.out.printf("%2d. %s\n", i, products.title);
                i++;
            }
            int deleteChoice = io.readNextInt();
            if (deleteChoice == 0) {
                System.out.println("Отмена операции удаления.");
            } else if (deleteChoice > 0 && deleteChoice < i) {
                if (controller.getProductsController().removeProductById(toDelete.get(deleteChoice - 1).productId)) {
                    System.out.println("Успешно удалено.");
                } else {
                    System.out.println("Не получилось удалить.");
                }
            } else {
                System.out.println("Ошибка удаления. Неверный номер.");
            }
        }
    };

    private static final AbstractCommands allProducts = new AbstractCommands(){

        @Override
        public String getName() {
            return "Список всех продуктов";
        }

        @Override
        public void action(ControllersProviderImpl controller) {
            List<Product> product = controller.getProductsController().getAllProduct();
            int i = 1;
            for (Product products : product) {
                System.out.printf("%2d. %s\n", i, products.title + ", Стоимость за единицу: " + products.price + ", id склада: " + products.warehouseId);
                i++;
            }
        }
    };

    private static final AbstractCommands updateProduct = new AbstractCommands() {
        @Override
        public String getName() {
            return "Обновить продукт";
        }

        @Override
        public void action(ControllersProviderImpl controllersProvider) {
            System.out.println("Введите часть названия продукта: ");
            String title = io.readNextNonEmptyLine();
            List<Product> toUpdate = controllersProvider.getProductsController().findProductByName(title);
            int i = 1;
            System.out.println("Выберите продукт для обновления или 0 для отмены:");
            for (Product product : toUpdate) {
                System.out.printf("%2d. %s\n", i, product.title);
                i++;
            }
            int updateChoice = io.readNextInt();
            if (updateChoice == 0) {
                System.out.println("Отмена операции обновления");
            } else if (updateChoice > 0 && updateChoice < i) {
                Product productUp = toUpdate.get(updateChoice - 1);
                boolean tempBool = true;
                while (tempBool) {
                    tempBool = false;
                    System.out.println("Наименование продукта: " + productUp.title);
                    System.out.println("Склад: " + controllersProvider.getWarehousesController().getWarehouseId(productUp.warehouseId));
                    System.out.println("Цена за единицу: " + productUp.price);

                    System.out.println("Выберите пункт для обновления или 0 для отмены:");
                    int k = 1;
                    System.out.printf("%2d. %s\n", k, "Наименование продукта");
                    k++;
                    System.out.printf("%2d. %s\n", k, "Склад");
                    k++;
                    System.out.printf("%2d. %s\n", k, "Цена за единицу");

                    System.out.println("> ");
                    int pointChoice = io.readNextInt();

                    switch (pointChoice) {
                        case 0:
                            break;
                        case 1: {
                            System.out.println("Введите название: ");
                            productUp.title = io.readNextNonEmptyLine();
                            controllersProvider.getProductsController().updateProduct(productUp);
                        }
                        break;
                        case 2: {
                            System.out.println("Введите номер склада: ");
                            List<Warehouse> temp = controllersProvider.getWarehousesController().findWarehousesByNumber(io.readNextNonEmptyLine());

                            int j = 1;
                            System.out.println("Выберите склад или 0 для отмены:");
                            for (Warehouse warehouses : temp) {
                                System.out.printf("%2d. %s\n", j, warehouses.warehouseNumber);
                                j++;
                            }
                            int upChoice = io.readNextInt();
                            if (upChoice == 0) {
                                System.out.println("Отмена операции обновления");
                            } else if (upChoice > 0 && upChoice < j) {
                                productUp.warehouseId = upChoice - 1;
                                controllersProvider.getProductsController().updateProduct(productUp);
                            }
                        } break;
                        case 3: {
                            System.out.println("Введите:");
                            productUp.price = io.readNextInt();
                            controllersProvider.getProductsController().updateProduct(productUp);
                        } break;
                        default: {
                            System.out.println("Ошибочная команда!");
                        } break;
                    }
                }
            }
        }
    };

    public ProductsCommands() {
        super(Arrays.asList(addProduct, deleteProduct, allProducts, updateProduct));
    }

    @Override
    public String getName() {return "Работа с продуктами";}

}
