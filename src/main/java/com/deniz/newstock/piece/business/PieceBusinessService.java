package com.deniz.newstock.piece.business;

import com.deniz.newstock.piece.business.exception.*;
import com.deniz.newstock.piece.persistence.entity.OwnerEntity;
import com.deniz.newstock.piece.persistence.entity.PieceEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: TRYavasU
 */
public interface PieceBusinessService extends IBusinessService<PieceEntity> {

    @Transactional
    void sell(long sellerId, long pieceId, double value) throws DontBeCheapException, ItsNotYoursException;

    @Transactional
    void buy(long buyerId, long pieceId) throws ItsYourPieceException, YouArePoorException, NotForSaleException;

    @Transactional
    OwnerEntity getOwner(long pieceId);
}
