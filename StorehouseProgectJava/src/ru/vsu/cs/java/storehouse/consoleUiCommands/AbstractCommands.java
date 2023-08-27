package ru.vsu.cs.java.storehouse.consoleUiCommands;

import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.providers.ControllersProviderImpl;

public interface AbstractCommands<T> {
    default String getName() {
        return null;
    }

    default void action(ControllersProviderImpl controller) {}
}
