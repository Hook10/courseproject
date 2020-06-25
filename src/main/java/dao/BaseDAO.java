package dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T extends Serializable> {
    //create
    void add(T entity) throws SQLException;

    //read
    List<T> getAll() throws SQLException;

    T getById(Long id) throws SQLException;

    //update
    void update(long id, T entity) throws SQLException;

    //delete
    void remove(T entity) throws SQLException;
}
