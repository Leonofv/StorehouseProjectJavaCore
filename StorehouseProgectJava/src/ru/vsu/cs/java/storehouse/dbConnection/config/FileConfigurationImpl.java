package ru.vsu.cs.java.storehouse.dbConnection.config;

import ru.vsu.cs.java.storehouse.dbConnection.config.utils.ReadFileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileConfigurationImpl implements  FileConfiguration {

    private Map<String, String> config;

    @Override
    public Map<String, String> getConfig() {
        if (config == null) {
            config = new HashMap<>();
            List<String> lines = ReadFileUtil.getLines("resources/properties.conf");
            for (String line : lines) {
                if (line.startsWith("#")) {
                    continue;
                }
                String[] entry = line.split("=");
                config.put(entry[0].replaceAll("\\s+", ""), entry[1].replaceAll("\\s+", ""));
            }
        }
        return config;
    }

    @Override
    public String getProperty(String propertyName) {
        return getConfig().get(propertyName);
    }
}
