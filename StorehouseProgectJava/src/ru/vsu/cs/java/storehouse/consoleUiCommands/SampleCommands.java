package ru.vsu.cs.java.storehouse.consoleUiCommands;

import java.util.Arrays;

public class SampleCommands extends CommandsGroup {

    public SampleCommands() {
        super(
                Arrays.asList
                (
                        new ProductsCommands(),
                    new ShippersCommands(),
                    new WarehouseCommands()));
    }
}
