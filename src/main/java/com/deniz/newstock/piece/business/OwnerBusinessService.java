package com.deniz.newstock.piece.business;

import com.deniz.newstock.piece.persistence.entity.OwnerEntity;

/**
 * @author: TRYavasU
 */
public interface OwnerBusinessService extends IBusinessService<OwnerEntity> {

    void changeMoney(long ownerId, double value);
}
