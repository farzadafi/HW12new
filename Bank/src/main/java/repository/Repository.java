package repository;

import java.sql.SQLException;

public interface Repository<T> {
    void add(T t)throws SQLException;

    int find(String input) throws SQLException;

}
