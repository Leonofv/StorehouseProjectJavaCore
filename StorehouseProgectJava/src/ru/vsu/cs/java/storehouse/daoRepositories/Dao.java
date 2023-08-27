package ru.vsu.cs.java.storehouse.daoRepositories;

import java.util.List;

public interface Dao<T> {

    boolean create(T entity);

    void update(T entity);

    Boolean delete(T entity);

    List<T> get(String name);

    List<T> getAll();
}
