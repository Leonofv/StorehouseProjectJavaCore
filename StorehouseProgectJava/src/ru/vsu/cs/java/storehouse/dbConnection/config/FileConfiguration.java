package ru.vsu.cs.java.storehouse.dbConnection.config;

import java.util.Map;

public interface FileConfiguration {

    Map<String, String> getConfig();

    String getProperty(String propertyName);
}
