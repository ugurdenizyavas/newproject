package com.deniz.newstock.piece.persistence.impl;

import com.deniz.newstock.piece.persistence.TransactionDao;
import com.deniz.newstock.piece.persistence.entity.TransactionEntity;
import com.deniz.newstock.piece.persistence.entity.OwnerEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author: TRYavasU
 */
public class TransactionDaoImpl extends AbstractFrameworkDao<TransactionEntity> implements TransactionDao {
    @Override
    protected Class<?> getSupportedClass() {
        return TransactionEntity.class;
    }

}
