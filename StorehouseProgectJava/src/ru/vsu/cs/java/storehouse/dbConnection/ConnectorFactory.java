package ru.vsu.cs.java.storehouse.dbConnection;

import java.sql.Connection;

public interface ConnectorFactory {
    Connection create();
}
