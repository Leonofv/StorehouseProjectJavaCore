package ru.vsu.cs.java.storehouse.consoleUiCommands;

import ru.vsu.cs.java.storehouse.consoleUiCommands.IOAplication.IO;
import ru.vsu.cs.java.storehouse.daoRepositories.сontrollers.providers.ControllersProviderImpl;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandsGroup implements AbstractCommands {

    private final List<AbstractCommands> allCommands = new ArrayList<>();

    public CommandsGroup(List<AbstractCommands> commands){
        allCommands.addAll(commands);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void action(ControllersProviderImpl controller) {
        int choice;
        do {
            printMenu();
            choice = IO.instance.readNextInt();
            if (choice == 0)
                break;
            if (choice < 0 || choice > allCommands.size())
                System.out.println("Ошибочная команда! Попробуйте ещё раз.");
            else
                allCommands.get(choice - 1).action(controller);
        }
        while (true);
    }

    public void printMenu() {
        System.out.println("Выберите операцию:");
        System.out.println("0. Выход");
        for (int i = 0; i < allCommands.size(); i++){
            System.out.printf("%d. %s;", i+1, allCommands.get(i).getName());
            System.out.println();
        }
    }
}
