package com.deniz.newstock.piece.business;

import com.deniz.newstock.piece.persistence.entity.TransactionEntity;

import java.util.List;

/**
 * @author: TRYavasU
 */
public interface TransactionBusinessService extends IBusinessService<TransactionEntity> {

    void addTransaction(long sellerId, long buyerId, long pieceId, double value);

}
