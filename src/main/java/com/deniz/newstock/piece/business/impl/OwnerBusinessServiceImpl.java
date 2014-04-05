package com.deniz.newstock.piece.business.impl;

import com.deniz.newstock.piece.business.OwnerBusinessService;
import com.deniz.newstock.piece.persistence.entity.OwnerEntity;

import java.util.List;

/**
 * @author: TRYavasU
 */
public class OwnerBusinessServiceImpl extends AbstractBusinessService<OwnerEntity> implements OwnerBusinessService {

    @Override
    public OwnerEntity getById(long id) {
        return (OwnerEntity) abstractDao.getById(id);
    }

    @Override
    public void changeMoney(long ownerId, double value) {
        OwnerEntity owner = getById(ownerId);
        owner.setMoney(owner.getMoney() + value);
        update(owner);
    }
}