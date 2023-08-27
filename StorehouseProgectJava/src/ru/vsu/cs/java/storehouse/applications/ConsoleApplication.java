package ru.vsu.cs.java.storehouse.applications;

import ru.vsu.cs.java.storehouse.consoleUiCommands.SampleCommands;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.providers.ControllersProviderImpl;

public class ConsoleApplication {

    private SampleCommands allApplication;
    ControllersProviderImpl controllersProvide;

    public ConsoleApplication(ControllersProviderImpl controllersProviderImpl) {
        this.controllersProvide = controllersProviderImpl;
        allApplication = new SampleCommands();
    }

    public void start() {
        allApplication.action(controllersProvide);
        System.out.println("Завершение работы программы. ");
    }
}
