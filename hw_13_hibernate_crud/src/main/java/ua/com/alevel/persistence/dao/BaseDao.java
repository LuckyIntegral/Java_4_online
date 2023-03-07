package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseDao<ENTITY extends BaseEntity> {
    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(ENTITY entity);
    Optional<ENTITY> findById(Long id);
    List<ENTITY> findAll();
}
