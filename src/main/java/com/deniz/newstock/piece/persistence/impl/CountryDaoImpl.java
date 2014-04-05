package com.deniz.newstock.piece.persistence.impl;

import com.deniz.newstock.piece.persistence.CountryDao;
import com.deniz.newstock.piece.persistence.entity.CountryEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author: TRYavasU
 */
public class CountryDaoImpl extends AbstractFrameworkDao<CountryEntity> implements CountryDao {
    @Override
    protected Class<?> getSupportedClass() {
        return CountryEntity.class;
    }

}
