package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.List;

public interface BaseService<E extends BaseEntity> {
    void create(E entity);
    E findById(Long id);
    List<E> findAll();
    void update(Long id, E entity);
    void delete(Long id);
}
