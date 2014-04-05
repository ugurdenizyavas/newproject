package com.deniz.newstock.piece.business.impl;

import com.deniz.newstock.piece.business.OwnerBusinessService;
import com.deniz.newstock.piece.business.PieceBusinessService;
import com.deniz.newstock.piece.business.TransactionBusinessService;
import com.deniz.newstock.piece.persistence.entity.TransactionEntity;

import java.util.List;

/**
 * @author: TRYavasU
 */
public class TransactionBusinessServiceImpl extends AbstractBusinessService<TransactionEntity> implements
        TransactionBusinessService {

    private OwnerBusinessService ownerBusinessService;
    private PieceBusinessService pieceBusinessService;

    public void setOwnerBusinessService(OwnerBusinessService ownerBusinessService) {
        this.ownerBusinessService = ownerBusinessService;
    }

    public void setPieceBusinessService(PieceBusinessService pieceBusinessService) {
        this.pieceBusinessService = pieceBusinessService;
    }

    @Override
    public TransactionEntity getById(long id) {
        return (TransactionEntity) abstractDao.getById(id);
    }

    @Override
    public void addTransaction(long sellerId, long buyerId, long pieceId, double value) {
        TransactionEntity transaction = new TransactionEntity(ownerBusinessService.getById(sellerId),
                ownerBusinessService.getById(buyerId), pieceBusinessService.getById(pieceId), value);
        abstractDao.save(transaction);
    }
}
