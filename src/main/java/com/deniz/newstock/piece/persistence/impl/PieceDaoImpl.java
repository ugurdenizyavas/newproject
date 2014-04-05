package com.deniz.newstock.piece.persistence.impl;

import com.deniz.newstock.piece.persistence.PieceDao;
import com.deniz.newstock.piece.persistence.entity.OwnerEntity;
import com.deniz.newstock.piece.persistence.entity.PieceEntity;
import org.apache.commons.lang.Validate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author: TRYavasU
 */
public class PieceDaoImpl extends AbstractFrameworkDao<PieceEntity> implements PieceDao {

    @Override
    protected Class<?> getSupportedClass() {
        return PieceEntity.class;
    }

    @Override
    public OwnerEntity getOwner(long pieceId) {
        Validate.notNull(pieceId);
        Criteria criteria = getCurrentSession().createCriteria(getSupportedClass());
        criteria.add(Restrictions.eq("id", pieceId));
        criteria.setProjection(Projections.property("owner"));
        return (OwnerEntity) criteria.uniqueResult();
    }
}