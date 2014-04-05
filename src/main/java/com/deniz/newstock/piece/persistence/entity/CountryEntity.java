package com.deniz.newstock.piece.persistence.entity;

import com.deniz.framework.persistence.entity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

/**
 * @author: TRYavasU
 */
@Entity
@Table(name = "COUNTRY_ENTITY", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@SequenceGenerator(name = "ONE_SEQUENCE_PER_ENTITY_GENERATOR", sequenceName = "COUNTRY_SEQUENCE")
public class CountryEntity extends AbstractEntity {
    private String name;

    public CountryEntity() {
    }

    public CountryEntity(String name) {
        this.name = name;
    }

    @Column(length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}