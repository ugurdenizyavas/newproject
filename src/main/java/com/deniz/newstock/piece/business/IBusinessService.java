package com.deniz.newstock.piece.business;

import com.deniz.framework.persistence.entity.AbstractEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: TRYavasU
 */
public interface IBusinessService<E extends AbstractEntity> {

    @Transactional
    void deleteAll();

    @Transactional
    void save(E piece);

    @Transactional
    int getCount();

    @Transactional
    E getById(long id);

    void update(E entity);

    @Transactional
    List<E> getAll();
}
