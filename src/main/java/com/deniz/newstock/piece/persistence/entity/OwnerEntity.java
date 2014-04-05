package com.deniz.newstock.piece.persistence.entity;

import com.deniz.framework.persistence.entity.AbstractEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: TRYavasU
 */
@Entity
@Table(name = "OWNER_ENTITY", uniqueConstraints = {})
@SequenceGenerator(name = "ONE_SEQUENCE_PER_ENTITY_GENERATOR", sequenceName = "OWNER_SEQUENCE")
public class OwnerEntity extends AbstractEntity {

    private double money;

    public OwnerEntity() {
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Column
    public double getMoney() {
        return money;
    }

}
