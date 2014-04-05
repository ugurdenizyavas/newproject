package com.deniz.newstock.piece.business.impl;

import com.deniz.newstock.piece.business.OwnerBusinessService;
import com.deniz.newstock.piece.business.PieceBusinessService;
import com.deniz.newstock.piece.business.TransactionBusinessService;
import com.deniz.newstock.piece.business.exception.*;
import com.deniz.newstock.piece.persistence.PieceDao;
import com.deniz.newstock.piece.persistence.entity.OwnerEntity;
import com.deniz.newstock.piece.persistence.entity.PieceEntity;

import java.util.List;

/**
 * @author: TRYavasU
 */
public class PieceBusinessServiceImpl extends AbstractBusinessService<PieceEntity> implements PieceBusinessService {

    private OwnerBusinessService ownerBusinessService;
    private double minValue = 1d;
    private TransactionBusinessService transactionBusinessService;
    private PieceDao pieceDao;

    public void setPieceDao(PieceDao pieceDao) {
        this.pieceDao = pieceDao;
    }

    public void setTransactionBusinessService(TransactionBusinessService transactionBusinessService) {
        this.transactionBusinessService = transactionBusinessService;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public void setOwnerBusinessService(OwnerBusinessService ownerBusinessService) {
        this.ownerBusinessService = ownerBusinessService;
    }


    @Override
    public void sell(long sellerId, long pieceId, double value) throws DontBeCheapException, ItsNotYoursException {
        if (value <= minValue) {
            throw new DontBeCheapException();
        } else {
            OwnerEntity seller = ownerBusinessService.getById(sellerId);
            PieceEntity piece = getById(pieceId);
            if (piece.getOwner().getId() != seller.getId()) {
                throw new ItsNotYoursException();
            } else {
                piece.setValue(value);
                update(piece);
            }
        }
    }

    @Override
    public void buy(long buyerId, long pieceId) throws ItsYourPieceException, YouArePoorException,
            NotForSaleException {
        OwnerEntity buyer = ownerBusinessService.getById(buyerId);
        PieceEntity piece = getById(pieceId);
        Long sellerId = piece.getOwner().getId();
        if (sellerId == buyer.getId()) {
            throw new ItsYourPieceException();
        } else {
            double value = piece.getValue();
            if (value > buyer.getMoney()) {
                throw new YouArePoorException();
            } else if (!piece.isOnSell()) {
                throw new NotForSaleException();
            } else {
                ownerBusinessService.changeMoney(buyerId, -value);
                ownerBusinessService.changeMoney(sellerId, value);
                changeOwner(pieceId, buyerId);
                transactionBusinessService.addTransaction(sellerId, buyerId, pieceId, value);
            }
        }
    }

    @Override
    public OwnerEntity getOwner(long pieceId) {
        return pieceDao.getOwner(pieceId);
    }

    private void changeOwner(long pieceId, long buyerId) {
        PieceEntity piece = getById(pieceId);
        piece.setOwner(ownerBusinessService.getById(buyerId));
        piece.setValue(0d);
        update(piece);
    }

    @Override
    public PieceEntity getById(long id) {
        return (PieceEntity) abstractDao.getById(id);
    }
}