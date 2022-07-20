package com.cvrs.backend.service.base;

import java.util.List;

public interface IBaseService<T, ID> {
    T findById(ID id);

    List<T> findAll();

    T save(T entity);

    T update(T entity);

    void deleteById(ID id);

    void delete(T entity);

    boolean existById(ID id);
}
