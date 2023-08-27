package ru.vsu.cs.java.storehouse.consoleUiCommands.IOAplication;

import java.util.Scanner;

public class IO {

    public static final IO instance = new IO();


    private Scanner scanner;

    private IO() {
        scanner = new Scanner(System.in);
    }

    public String readNextNonEmptyLine() {
        String s = "";
        while (true) {
            s = scanner.nextLine();
            if (!"".equals(s))
                break;
        }
        return s;
    }

    public int readNextInt() {
        return scanner.nextInt();
    }

}
