package com.deniz.newstock.piece.persistence;

import com.deniz.newstock.piece.persistence.entity.TransactionEntity;
import com.deniz.newstock.piece.persistence.entity.OwnerEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: TRYavasU
 */
public interface TransactionDao extends IDao<TransactionEntity> {

}