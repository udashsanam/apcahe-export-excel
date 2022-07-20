package com.cvrs.backend.service.implementation.base;

import com.cvrs.backend.entity.baseEntity.BaseEntity;
import com.cvrs.backend.service.base.IBaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<T extends BaseEntity, ID> implements IBaseService<T, ID> {

    protected JpaRepository<T, ID> repository;

    public BaseServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T findById(ID id) {
        Optional<T> optionalT = repository.findById(id);
        return optionalT.orElse(null);
    }

    @Override
    public List<T> findAll(){
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existById(ID id) {
        return repository.existsById(id);
    }
}
