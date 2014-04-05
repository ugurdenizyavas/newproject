package com.deniz.newstock.piece.persistence.impl;

import com.deniz.newstock.piece.persistence.OwnerDao;
import com.deniz.newstock.piece.persistence.entity.OwnerEntity;

/**
 * @author: TRYavasU
 */
public class OwnerDaoImpl extends AbstractFrameworkDao<OwnerEntity> implements OwnerDao {
    @Override
    protected Class<?> getSupportedClass() {
        return OwnerEntity.class;
    }
}
