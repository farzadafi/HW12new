package service;

import java.util.List;

public interface Service<T> {
    int add(T t);

    List<T> findAll();

    int update(T t);

    int delete(int id);
}
