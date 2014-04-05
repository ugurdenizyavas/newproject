package com.deniz.newstock.piece.persistence;

import com.deniz.framework.persistence.entity.AbstractEntity;
import com.deniz.newstock.piece.persistence.entity.OwnerEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IDao<E extends AbstractEntity> {

    /**
     * Gets entity by its name
     *
     * @param name (not nullable)
     * @return entity
     */
    E getByName(String name);

    /**
     * persists given abstract entity
     *
     * @param entity (not nullable)
     */
    void save(E entity);

    /**
     * Gets all
     *
     * @return all entities
     */
    List<E> getAll();

    /**
     * Removes all entities
     */
    void removeAll();

    /*
     * Updates given entity
     */
    void update(E entity);

    /*
     * Returns count of entity with distinct id
     */
    int getCount();
}
