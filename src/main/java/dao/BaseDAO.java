package dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {

    void add(T entity);

    List<T> getAll();


    T getById(Long id);


    void update(long id, T entity);


    void remove(T entity);
}
