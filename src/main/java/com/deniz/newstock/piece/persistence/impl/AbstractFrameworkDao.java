package com.deniz.newstock.piece.persistence.impl;

import com.deniz.framework.persistence.DaoTemplate;
import com.deniz.framework.persistence.entity.AbstractEntity;
import com.deniz.newstock.piece.persistence.entity.OwnerEntity;
import org.apache.commons.lang.Validate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractFrameworkDao<E extends AbstractEntity> extends DaoTemplate<E> {

    protected abstract Class<?> getSupportedClass();

    @SuppressWarnings("unchecked")
    public E getByName(String name) {
        Validate.notNull(name);
        Criteria criteria = getCurrentSession().createCriteria(getSupportedClass());
        criteria.add(Restrictions.eq("simpleName", name));
        return (E) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        Criteria criteria = getCurrentSession().createCriteria(getSupportedClass());
        return criteria.list();
    }

    public int getCount() {
        Criteria criteria = getCurrentSession().createCriteria(getSupportedClass());
        return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    public void removeAll() {
        List<E> all = getAll();
        for (E one : all) {
            getCurrentSession().delete(one);
        }
    }

    public E getById(long id) {
        Validate.notNull(id);
        Criteria criteria = getCurrentSession().createCriteria(getSupportedClass());
        criteria.add(Restrictions.eq("id", id));
        return (E) criteria.uniqueResult();
    }
}
