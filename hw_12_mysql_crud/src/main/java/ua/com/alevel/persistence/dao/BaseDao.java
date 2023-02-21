package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseDao<E extends BaseEntity> {
    void create(E entity);
    Optional<E> findById(Long id);
    List<E> findAll();
    void update(Long id, E entity);
    void delete(Long id);
}
