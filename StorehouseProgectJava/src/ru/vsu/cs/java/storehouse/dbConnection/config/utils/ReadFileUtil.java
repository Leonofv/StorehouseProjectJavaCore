package ru.vsu.cs.java.storehouse.dbConnection.config.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFileUtil {
    public static BufferedReader readFile(String path) throws IOException {

        FileReader fileReader;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            File file = new File(path);
            if (file.createNewFile()) {
                fileReader = new FileReader(path);
            } else {
                return null;
            }
            throw new RuntimeException(e);
        }
        return new BufferedReader(fileReader);
    }

    public static List<String> getLines(String path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bf = readFile(path)) {
            if (bf == null) {
                throw new RuntimeException();
            }
            String line;
            while ((line = bf.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}