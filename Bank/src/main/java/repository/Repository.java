package repository;

import java.sql.SQLException;

public interface Repository<T> {
    int add(T t)throws SQLException;

    int find(String input) throws SQLException;

}
