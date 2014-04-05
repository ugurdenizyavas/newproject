package com.deniz.newstock.piece.business.impl;

import com.deniz.newstock.piece.business.CountryBusinessService;
import com.deniz.newstock.piece.persistence.entity.CountryEntity;

import java.util.List;

/**
 * @author: TRYavasU
 */
public class CountryBusinessServiceImpl extends AbstractBusinessService<CountryEntity> implements CountryBusinessService {

    @Override
    public CountryEntity getById(long id) {
        return (CountryEntity) abstractDao.getById(id);
    }
}
