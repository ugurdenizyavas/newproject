package com.deniz.newstock.piece.business.impl;

import com.deniz.framework.persistence.entity.AbstractEntity;
import com.deniz.newstock.piece.business.IBusinessService;
import com.deniz.newstock.piece.persistence.impl.AbstractFrameworkDao;

import java.util.List;

/**
 * @author: TRYavasU
 */
public abstract class AbstractBusinessService<E extends AbstractEntity> implements IBusinessService<E> {

    protected AbstractFrameworkDao abstractDao;

    public void setAbstractDao(AbstractFrameworkDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public void deleteAll() {
        abstractDao.removeAll();
    }

    @Override
    public void save(E piece) {
        abstractDao.save(piece);
    }

    @Override
    public int getCount() {
        return abstractDao.getCount();
    }

    @Override
    public void update(E entity) {
        abstractDao.update(entity);
    }

    public List<E> getAll() {
        return abstractDao.getAll();
    }

    public abstract E getById(long id);
}
