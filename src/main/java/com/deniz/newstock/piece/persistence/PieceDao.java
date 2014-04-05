package com.deniz.newstock.piece.persistence;

import com.deniz.newstock.piece.persistence.entity.OwnerEntity;
import com.deniz.newstock.piece.persistence.entity.PieceEntity;

import java.util.List;

/**
 * @author: TRYavasU
 */
public interface PieceDao extends IDao<PieceEntity> {

    OwnerEntity getOwner(long pieceId);
}